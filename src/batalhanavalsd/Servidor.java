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
    
    public static void ini() throws IOException{
        ServerSocket serverSocket = null; 

        try{
            serverSocket = new ServerSocket(10008);//cria o socket para o servidor, porta 10008
//            System.out.println ("Connection Socket Created");
            System.out.println ("Servidor Iniciado!");
            try{ 
                while (Servidor.jogadores != 2){//fica esperando os cliente entrarem e cria a conexao
//                    System.out.println ("Waiting for Connection");
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
            Servidor.batalhaNaval = new Jogo(10,10);
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
            
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("SAIR")) 
                    break;
                
                //codigo de vdd
                if(Servidor.jogadores != 2){
                    out.println("0");
                    continue;
                }
                
                if(vez == Servidor.batalhaNaval.getVez()){//verifica se e a vez do jogador
                    out.println("1");
                    String respostaCliente[] = in.readLine().split(" ");
                    if(respostaCliente[0].equals("0")){//jogou
                        int resultadoAcao = Servidor.batalhaNaval.fazerAcao(Integer.parseInt(respostaCliente[1]), Integer.parseInt(respostaCliente[2]));
                        if (resultadoAcao == 1) {
                            out.println("Errou :(");
                        } else if (resultadoAcao == 2) {
                            out.println("Acertou :)");
                        } else {
                            out.println("Você já atirou aqui! :/");
                        }
                    }
                    else if(respostaCliente[0].equals("1")){//exibir tabelas
                        out.println(Servidor.batalhaNaval.tabelaToString(1, 0) + "tab" + Servidor.batalhaNaval.tabelaToString(2, 0));
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
