import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Nechelley Alves
 */
public class Cliente{
	protected Socket clientSocket;
	private static Boolean saiu = false;

	public static void main(String[] args) throws IOException{
		Scanner ler = new Scanner(System.in);

		// System.out.print("Digite o ip do servidor: ");
		// String serverHostname = ler.nextLine();

		String serverHostname = new String ("127.0.0.1");

		System.out.println ("Conectando-se ao servidor " + serverHostname + " na porta 10008.");

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try{//tentando conexao
			echoSocket = new Socket(serverHostname, 10008);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
			echoSocket.getInputStream()));
		}
		catch(UnknownHostException e){
			System.err.println("Servido com o ip " + serverHostname + " não foi encontrado!");
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("Não foi possivel se conectar!");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		String vez = in.readLine();//recebe qual jogador vc e
		if(vez.equals("0")){
			System.out.println("Você é o jogador 1. \nEsperando pelo jogador 2!\nPara abandonar a partida digite 'Sair'");
		}
		else{
			System.out.println("Você é o jogador 2. \nEspere sua vez!\nPara abandonar a partida digite 'Sair'");
		}

		// System.out.println ("Jogo Iniciado!\nDigite (\"SAIR\" para sair do jogo)");
		// (userInput = stdIn.readLine()) != null
		while (true){

			String leuDoServidor = in.readLine();//espera o servidor avisar q é sua vez
			if (leuDoServidor.equals("5")) {
				System.out.println("\nVocê abandou a partida");
				break;
			}

			if (leuDoServidor.equals("3")) {
				System.out.println("\n****** Você Venceu | Seu adversário desistiu *****");
				break;
			}

			if(leuDoServidor.equals("1")){
				System.out.println("\n----- Voce perdeu -----\nO jogo acabou!");
				break;//FINALIZA JOGO PARA PERDEDOR
			}

			//chego a vez deste cliente
			System.out.println("**************************************************");
			System.out.println("*                 SUA VEZ!                       *");
			System.out.println("**************************************************");

			//exibe tabelas
			String tabelas[] = leuDoServidor.split("tab");//separa as duas tabelas
			System.out.println("Tiros do adversario");
			for (String linha : tabelas[0].split("lin")){
				System.out.println(linha);
			}

			System.out.println("\nMeus tiros");
			for (String linha : tabelas[1].split("lin")){
				System.out.println(linha);
			}

			//pergunta a jogada a ser feita
			Boolean flgJogada = true;//serve para perguntar a jogada novamente ao usuario, caso esteja falsa
			Boolean flgFimDoJogo = false;//serve para encerrar o programa se o jogo terminou
			while(flgJogada){
				System.out.println("\nFaça sua jodada");
				System.out.print("Coordenada X: ");
				String x = stdIn.readLine();
				if (Cliente.verificaSair(x)) {
					Cliente.saiu = true;
					out.println("Sair");
					break;
				}

				System.out.print("Coordenada Y: ");
				String y = stdIn.readLine();
				if (Cliente.verificaSair(y)) {
					Cliente.saiu = true;
					out.println("Sair");
					break;
				}
				
				out.println(x+" "+y);

				//verifica o resultado do tiro
				int resultado = Integer.parseInt(in.readLine());
				if(resultado == 1){
					System.out.println("Errou :(");
					System.out.println("\n                Vez do adversario");
					System.out.println("--------------------------X------------------------\n");
					flgJogada = false;
				}
				else if(resultado == 2){
					System.out.println("Acertou :)");
					System.out.println("\n                Vez do adversario");
					System.out.println("--------------------------X------------------------\n");
					flgJogada = false;
				}
				else if(resultado == 0){
					System.out.println("Você já atirou aqui! :/\nPressione ENTER para atirar novamente!");
					stdIn.readLine();//ignora o enter
				}
				else if(resultado == 4){
					System.out.println("Coordenada invalida!!!\nPressione ENTER para atirar novamente!");
					stdIn.readLine();//ignora o enter
				}
				else if(resultado == 3){
					System.out.println("\n***** Voce venceu *****\nO jogo acabou!");
					flgJogada = false;
					flgFimDoJogo = true;//avisa que acabou o jogo
					break;//FINALIZA JOGO PARA VENCEDOR
				}
			}

			//encerra o processo se acabou o jogoAcabou
			if(flgFimDoJogo){
				break;
			}
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

	private static Boolean verificaSair(String entradaUsuario) {
		if (entradaUsuario.equals("Sair")){
			return true;
		}
		return false;
	}

}
