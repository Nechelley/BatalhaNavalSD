import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nechelley Alves
 */
public class Servidor extends Thread{
	protected Socket clientSocket;
	private static Jogo batalhaNaval = null;
	private static int jogadores = 0;
	private static Boolean acabou = false;
	private static Boolean jogar = false;
	private static Boolean jogadorSaiu = false;

	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;

		try{
			serverSocket = new ServerSocket(10008);//cria o socket para o servidor, porta 10008
			System.out.println ("Servidor Iniciado!");
			try{
				while (Servidor.jogadores != 2){//fica esperando os cliente entrarem e cria a conexao
					System.out.println ("Esperando por jogadores ...");
					new Servidor (serverSocket.accept());
				}
			}
			catch (IOException e){
				System.err.println("Accept failed.");
				System.exit(1);
			}
		}
		catch (IOException e){
			System.err.println("Could not listen on port: 10008.");
			System.exit(1);
		}
		finally{
			try{
				serverSocket.close();
			}
			catch (IOException e){
				System.err.println("Could not close port: 10008.");
				System.exit(1);
			}
		}
	}

	/**
	 * Construtor
	 * @param clientSoc Socket do cliente
	 */
	private Servidor (Socket clientSoc){
		if(Servidor.batalhaNaval == null){
			Servidor.batalhaNaval = new Jogo(5,5);
		}
		Servidor.jogadores++;

		this.clientSocket = clientSoc;
		start();
	}

	public void run(){
		// System.out.println ("New Communication Thread Started");
		System.out.println ("Jogador " + Servidor.jogadores + " entrou na partida!");

		try{
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

			String inputLine;
			int vez = Servidor.jogadores - 1;//jogador 0 ou 1

			out.println(vez);//envia vez po cliente

			while(true){
				
				if (Servidor.jogadorSaiu) {
					out.println("3");
					System.out.println("O jogo foi encerrado, alguem deixou o jogo !!!");
					break;
				}

				//manda a thread espera se n for a vez do usuario ou se n tiver jogadores suficientes na sala
				if(vez != Servidor.batalhaNaval.getVez() || (vez == 0 && Servidor.jogadores != 2)){
					try{
						Thread.sleep(1000);//espera 1seg
						continue;
					}
					catch (InterruptedException ex){
						System.err.println("Erro na thread");
					}
				}
				
				//verificar se o jogador perdeu
				if(Servidor.batalhaNaval.jogoAcabou()){
					out.println("1");//codigo que diz ao cliente que ele perdeu
					break;
				}
				
				//caso o jogo n tenha acabado, mandar as tabelas ja
				if(vez == 0){
					out.println(Servidor.batalhaNaval.tabelaToString(1, 0) + "tab" + Servidor.batalhaNaval.tabelaToString(2, 1));
				}
				else{
					out.println(Servidor.batalhaNaval.tabelaToString(2, 0) + "tab" + Servidor.batalhaNaval.tabelaToString(1, 1));
				}
				
				//agora e recebida a jogada no formato "x y"
				Boolean flgJogada = true;//serve para perguntar a jogada novamente ao usuario, caso esteja falsa
				while(flgJogada){
					
					String coordenadas[] = in.readLine().split(" ");
					if (coordenadas[0].equals("Sair")) {
						if (vez == 0) {
							vez = 1;
						} else {
							vez = 0;
						}
						Servidor.jogadorSaiu = true;
						out.println("5");//Jogador saiu da partida
						flgJogada = false;
					} else {

						int resultadoAcao = Servidor.batalhaNaval.fazerAcao(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));
				
						//envia o resultado da acao para o cliente tratar
						out.println(resultadoAcao);

						//verifica se vai ter que ler novamente as jogadas
						// flgJogada = !(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3);
						if(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3){
							flgJogada = false;
						}
					}
				}
			}

			System.out.println("A partida terminou!");
			out.close();
			in.close();
			clientSocket.close();
		}
		catch (IOException e){
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}
}
