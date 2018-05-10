import java.io.*;
import java.net.*;

/**
 *
 * @author Nechelley Alves
 */
public class Cliente{
    protected Socket clientSocket;
    
    public static void ini() throws IOException{
        String serverHostname = new String ("127.0.0.1");

        // System.out.println ("Attemping to connect to host " + serverHostname + " on port 10008.");

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
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        }
        catch(IOException e){
            System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        // System.out.println ("Type Message (\"Bye.\" to quit)");
        System.out.println ("Conectado!\nDigite (\"SAIR\" para sair do jogo\nPressione ENTER para começar");
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
                System.out.println("Sua vez!");
                System.out.println("0 - jogar | 1 - exibirMapas");
                String leuDoCliente = stdIn.readLine();
                while(leuDoCliente.equals("0") && leuDoCliente.equals("1")){
                    leuDoCliente = stdIn.readLine();
                }
                
                if(leuDoCliente.equals("0")){//jogar
                    System.out.print("Coordenada X: ");
                    String x = stdIn.readLine();
                    System.out.print("Coordenada Y: ");
                    String y = stdIn.readLine();
                    out.println("0 "+x+" "+y);
                    System.out.println(in.readLine());
                    System.out.println("\nVez do adversário");
                }
                else if(leuDoCliente.equals("1")){//exibir tabela
                    out.println("1");
                    String tabelas[] = in.readLine().split("tab");//separa as duas tabelas
                    for (String linha : tabelas[0].split("lin")) {
                        System.out.println(linha);
                    }
                    System.out.println("\n\n\n");
                    for (String linha : tabelas[1].split("lin")) {
                        System.out.println(linha);
                    }
                    System.out.println("Escreva ok para continuar.");
                }
                continue;
            }
            if(leuDoServidor.equals("2")){//nao vez do jogador
                System.out.println("Nao e sua vez!");
            }
            
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }

}
