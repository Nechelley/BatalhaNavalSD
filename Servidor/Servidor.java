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
	
			// out.println("Você é o jogador " + Servidor.jogadores);

			String inputLine;
			int vez = Servidor.jogadores - 1;//jogador 0 ou 1

			while ((inputLine = in.readLine()) != null) {
				// System.out.println("teste");
				if (inputLine.equals("SAIR"))
					break;

				//codigo de vdd
				if(Servidor.jogadores != 2){
					out.println("0");
					continue;
				}

				if(vez == Servidor.batalhaNaval.getVez()){//verifica se e a vez do jogador
					
					if ( acabou ) {
						out.println("3");
					} else if (!Servidor.jogar){
						out.println("1");
					}
					
					System.out.println("aqui");
					String respostaCliente[] = in.readLine().split(" ");
					System.out.println(respostaCliente[0]);
					if(respostaCliente[0].equals("1")){//exibir tabelas
						Servidor.jogar = true;

						if (vez == 0) {

							out.println(Servidor.batalhaNaval.tabelaToString(1, 0) + "tab" + Servidor.batalhaNaval.tabelaToString(2, 1));
						} else {
							out.println(Servidor.batalhaNaval.tabelaToString(2, 0) + "tab" + Servidor.batalhaNaval.tabelaToString(1, 1));
						}
						respostaCliente = in.readLine().split(" ");
						System.out.println(respostaCliente[0]);
					} 
					if(respostaCliente[0].equals("0")){//jogou
						
						int resultadoAcao = Servidor.batalhaNaval.fazerAcao(Integer.parseInt(respostaCliente[1]), Integer.parseInt(respostaCliente[2]));

						if (resultadoAcao == 3) {
							Servidor.acabou = true;
						}

						Servidor.jogar = false;
						out.println(resultadoAcao);
					}
					else if (respostaCliente[0].equals("3")){
						// o jogo acabou
						System.out.println("O jogo acabou!");
						inputLine = null;
					}
				}
				else{
					out.println("2");
				}
			}

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
