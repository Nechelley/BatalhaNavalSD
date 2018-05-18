import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Nechelley Alves
 */
public class Cliente{
	protected Socket clientSocket;

	public static void main(String[] args) throws IOException{
		Scanner ler = new Scanner(System.in);

		System.out.print("Digite o ip do servidor: ");
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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
		String vez = in.readLine();//recebe qual jogador vc e
		if(vez.equals("0")){
			System.out.println("Você é o jogador 1. \nEsperando pelo jogador 2!");
		}
		else{
			System.out.println("Você é o jogador 2. \nEspere sua vez!");
		}
<<<<<<< HEAD

		// System.out.println ("Jogo Iniciado!\nDigite (\"SAIR\" para sair do jogo)");
		// (userInput = stdIn.readLine()) != null
		while (true){
			String leuDoServidor = in.readLine();//espera o servidor avisar q é sua vez

			if(leuDoServidor.equals("1")){
				System.out.println("Voce perdeu!!!\nO jogo acabou!");
				break;//FINALIZA JOGO PARA PERDEDOR
			}

			//chego a vez deste cliente
			System.out.println("Sua vez!\n");

			//exibe tabelas
			String tabelas[] = leuDoServidor.split("tab");//separa as duas tabelas
			System.out.println("Tiros do adversario");
			for (String linha : tabelas[0].split("lin")){
				System.out.println(linha);
			}

=======
=======
		// System.out.println ("Type Message (\"Bye.\" to quit)");

		String vez = in.readLine();
		if (vez.equals("0")) {
			System.out.println("Você é o jogador 1. \nEsperando pelo jogador 2!");
			in.readLine();
		} else {
			System.out.println("Você é o jogador 2. \nEspere sua vez!");
		}

		System.out.println ("Jogo Iniciado!\nDigite (\"SAIR\" para sair do jogo)");
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			if (userInput.equals("SAIR"))
				break;
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16

		// System.out.println ("Jogo Iniciado!\nDigite (\"SAIR\" para sair do jogo)");
		// (userInput = stdIn.readLine()) != null
		while (true){
			String leuDoServidor = in.readLine();//espera o servidor avisar q é sua vez

			if(leuDoServidor.equals("1")){
				System.out.println("Voce perdeu!!!\nO jogo acabou!");
				break;//FINALIZA JOGO PARA PERDEDOR
			}

			//chego a vez deste cliente
			System.out.println("Sua vez!\n");

			//exibe tabelas
			String tabelas[] = leuDoServidor.split("tab");//separa as duas tabelas
			System.out.println("Tiros do adversario");
			for (String linha : tabelas[0].split("lin")){
				System.out.println(linha);
			}

>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
			System.out.println("\nMeus tiros");
			for (String linha : tabelas[1].split("lin")){
				System.out.println(linha);
			}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
			if(leuDoServidor.equals("1")){//vez do jogador
				System.out.println("Sua vez!\n");
				
				//jogar
					
				out.println("1");
				String tabelas[] = in.readLine().split("tab");//separa as duas tabelas
				System.out.println("Tiros do adversario");
				for (String linha : tabelas[0].split("lin")) {
					System.out.println(linha);
				}
				System.out.println("\nMeus tiros");
				for (String linha : tabelas[1].split("lin")) {
					System.out.println(linha);
				}
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc

			//pergunta a jogada a ser feita
			Boolean flgJogada = true;//serve para perguntar a jogada novamente ao usuario, caso esteja falsa
			Boolean flgFimDoJogo = false;//serve para encerrar o programa se o jogo terminou
			while(flgJogada){
				System.out.println("\nFaça sua jodada");
				System.out.print("Coordenada X: ");
				String x = stdIn.readLine();
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
				// while(!x.equals("") && x != null) {
				// 	System.out.print("Coordenada X: ");
				// 	x = stdIn.readLine();
				// } 
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
				System.out.print("Coordenada Y: ");
				String y = stdIn.readLine();
				out.println(x+" "+y);

				//verifica o resultado do tiro
				int resultado = Integer.parseInt(in.readLine());
				if(resultado == 1){
					System.out.println("Errou :(");
					System.out.println("Vez do adversario");
					flgJogada = false;
				}
				else if(resultado == 2){
					System.out.println("Acertou :)");
					System.out.println("Vez do adversario");
					flgJogada = false;
				}
				else if(resultado == 0){
					System.out.println("Você já atirou aqui! :/\nPressione ENTER para atirar novamente!");
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
					stdIn.readLine();//ignora o enter
				}
				else if(resultado == 4){
					System.out.println("Coordenada invalida!!!\nPressione ENTER para atirar novamente!");
					stdIn.readLine();//ignora o enter
				}
				else if(resultado == 3){
<<<<<<< HEAD
=======
=======
				} else if (resultado == 4) {
					System.out.println("Coordenada inválida!!!\nPressione ENTER para atirar novamente!");
				} else if (resultado == 3) {
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
					System.out.println("Voce venceu!!!\nO jogo acabou!");
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

}
