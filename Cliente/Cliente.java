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
		String serverHostname = ler.nextLine();

		// String serverHostname = new String ("127.0.0.1");

		System.out.println ("Conectando-se ao servidor " + serverHostname + " na porta 10008.");

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try{
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

		// System.out.println ("Type Message (\"Bye.\" to quit)");
		System.out.println ("Conectado!\nDigite (\"SAIR\" para sair do jogo)\nPressione ENTER para começar");
		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			if (userInput.equals("SAIR"))
				break;

			//codigo de vdd
			String leuDoServidor = in.readLine();
			if(leuDoServidor.equals("0")){//ainda existe apenas um jogador no server
				System.out.println("Esperando jogador 2");
				continue;
			}
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

				System.out.println("\nFaça sua jodada");
				System.out.print("Coordenada X: ");
				String x = stdIn.readLine();
				// while(!x.equals("") && x != null) {
				// 	System.out.print("Coordenada X: ");
				// 	x = stdIn.readLine();
				// } 
				System.out.print("Coordenada Y: ");
				String y = stdIn.readLine();
				out.println("0 "+x+" "+y);

				int resultado = Integer.parseInt(in.readLine());
				System.out.println(resultado);

				if (resultado == 1) {
					System.out.println("Errou :(");
					System.out.println("Vez do adversário");
				} else if (resultado == 2) {
					System.out.println("Acertou :)");
					System.out.println("Vez do adversário");
				} else if (resultado == 0) {
					System.out.println("Você já atirou aqui! :/\nPressione ENTER para atirar novamente!");
				} else if (resultado == 4) {
					System.out.println("Coordenada inválida!!!\nPressione ENTER para atirar novamente!");
				} else if (resultado == 3) {
					System.out.println("Voce venceu!!!\nO jogo acabou!");
					break;
				}

				continue;
			}
			if(leuDoServidor.equals("2")){//nao vez do jogador
				System.out.println("Nao e sua vez!");
			}
			if(leuDoServidor.equals("3")){//avisa o jogador que perdeu
				System.out.println("Voce perdeu!!!\nO jogo acabou!");
				out.println("3 ");
				break;
			}

		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

}
