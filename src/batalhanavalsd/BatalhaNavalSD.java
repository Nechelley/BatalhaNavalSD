package batalhanavalsd;

/**
 *
 * @author Nechelley Alves
 */
public class BatalhaNavalSD {
    public static void main(String[] args) {
        Jogo batalhaNaval = new Jogo(10,10);
        
        System.out.println(batalhaNaval.tabelaDoisToString(0));
        System.out.println();
        System.out.println(batalhaNaval.tabelaDoisToString(0));
    }
    
}
