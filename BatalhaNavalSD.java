import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Nechelley Alves
 */
public class BatalhaNavalSD {
    public static void main(String[] args) throws IOException {
        Scanner ler = new Scanner(System.in);
        
        System.out.println("0-Servidor ou 1-Cliente");
        int escolha = ler.nextInt();
        if(escolha == 0){//servidor
            Servidor.ini();
        }
        else{//cliente
            Cliente.ini();
        }
//        Scanner ler = new Scanner(System.in);
//        Jogo batalhaNaval = new Jogo(10,10);
//        int vez = 1;
//        
//        System.out.println(batalhaNaval.tabelaToString(1,0));
//        System.out.println();
//        System.out.println(batalhaNaval.tabelaToString(2,0));
//        System.out.println("-----------------------------------------------------------"+vez);
//        System.out.println("x:");
//        int x = ler.nextInt();
//        System.out.println("y:");
//        int y = ler.nextInt();
//        
//        
//        int jogada = batalhaNaval.fazerAcao(x, y);
//        while(true){
//            if(jogada == 0){//alvo invalido
//                System.out.println("x:");
//                x = ler.nextInt();
//                System.out.println("y:");
//                y = ler.nextInt();
//                jogada = batalhaNaval.fazerAcao(x, y);
//                while(jogada == 0){
//                    System.out.println("x:");
//                    x = ler.nextInt();
//                    System.out.println("y:");
//                    y = ler.nextInt();
//                    jogada = batalhaNaval.fazerAcao(x, y);
//                }
//            }
//            else if(jogada == 3){
//                System.out.println("ACABOU!!!!!");
//                break;
//            }
//            else{
//                if(vez == 1){
//                    vez = 2;
//                }
//                else{
//                    vez = 1;
//                }
//                System.out.println(batalhaNaval.tabelaToString(1,0));
//                System.out.println();
//                System.out.println(batalhaNaval.tabelaToString(2,0));
//                System.out.println("-----------------------------------------------------------"+vez);;
//                System.out.println("x:");
//                x = ler.nextInt();
//                System.out.println("y:");
//                y = ler.nextInt();
//                jogada = batalhaNaval.fazerAcao(x, y);
//            }
//        }
    }
    
}
