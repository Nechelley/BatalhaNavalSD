package batalhanavalsd;

/**
 *
 * @author Nechelley Alves
 */
public class Posicao {
    private int valorLogico;
    private String valorExibido;
    
    public Posicao(int valorLogico, String valorExibido){
        this.valorLogico = valorLogico;
        this.valorExibido = valorExibido;
    }
    
    
    //GETS AND SETS

    /**
     * @return the valorLogico
     */
    public int getValorLogico() {
        return valorLogico;
    }

    /**
     * @param valorLogico the valorLogico to set
     */
    public void setValorLogico(int valorLogico) {
        this.valorLogico = valorLogico;
    }

    /**
     * @return the valorExibido
     */
    public String getValorExibido() {
        return valorExibido;
    }

    /**
     * @param valorExibido the valorExibido to set
     */
    public void setValorExibido(String valorExibido) {
        this.valorExibido = valorExibido;
    }
}
