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
<<<<<<< HEAD
=======

<<<<<<< HEAD
			String inputLine;
			int vez = Servidor.jogadores - 1;//jogador 0 ou 1

			out.println(vez);//envia vez po cliente
=======
			// out.println("Você é o jogador " + Servidor.jogadores);
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16

			String inputLine;
			int vez = Servidor.jogadores - 1;//jogador 0 ou 1

<<<<<<< HEAD
			out.println(vez);//envia vez po cliente
=======
			out.println(vez);
			if (vez == 0) {
				while(Servidor.jogadores != 2) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						System.err.println("Erro");
					}
				}
				out.println("1");
			} else {
				while(Servidor.jogadores != 2) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						System.err.println("Erro");
					}
				}
				out.println("0");
			}

			while ((inputLine = in.readLine()) != null) {
				// System.out.println("teste");
				if (inputLine.equals("SAIR"))
					break;
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc

			while(true){
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
<<<<<<< HEAD

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
					int resultadoAcao = Servidor.batalhaNaval.fazerAcao(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));
=======
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16

			while(true){
				//manda a thread espera se n for a vez do usuario ou se n tiver jogadores suficientes na sala
				if(vez != Servidor.batalhaNaval.getVez() || (vez == 0 && Servidor.jogadores != 2)){
					try{
						Thread.sleep(1000);//espera 1seg
						continue;
					}
<<<<<<< HEAD
					catch (InterruptedException ex){
						System.err.println("Erro na thread");
=======
					
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
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc

					//envia o resultado da acao para o cliente tratar
					out.println(resultadoAcao);

					//verifica se vai ter que ler novamente as jogadas
					// flgJogada = !(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3);
					if(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3){
						flgJogada = false;
					}
<<<<<<< HEAD
=======
					else if (respostaCliente[0].equals("3")){
						// o jogo acabou
						System.out.println("O jogo acabou!");
						inputLine = null;
>>>>>>> 7aab6cea39d6a9872e6cc33d56949347a1da6d16
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
					int resultadoAcao = Servidor.batalhaNaval.fazerAcao(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]));

					//envia o resultado da acao para o cliente tratar
					out.println(resultadoAcao);

					//verifica se vai ter que ler novamente as jogadas
					// flgJogada = !(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3);
					if(resultadoAcao == 1 || resultadoAcao == 2 || resultadoAcao == 3){
						flgJogada = false;
					}
>>>>>>> 21b0ce6bda5903f79db53abc4fe8a9f732c9dbfc
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
