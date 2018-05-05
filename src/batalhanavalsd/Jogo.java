package batalhanavalsd;

/**
 *
 * @author Nechelley Alves
 */
public class Jogo {
    private Posicao tabelaJogadorUm[][];//tabela do primeiro jogador, onde estao suas pecas
    private Posicao tabelaJogadorDois[][];//tabela do segundo jogador, onde estao suas pecas
    private int vez;//0 = vez do jogador 1. 1 = vez do jogador dois
    
    /**
     * Construtor
     * @param alturaTabela altura da tabela de ambos os jogadores
     * @param larguraTabela largura da tabela de ambos os jogadores
     */
    public Jogo(int alturaTabela, int larguraTabela){
        geraTabelaUm(alturaTabela, larguraTabela);
        geraTabelaDois(alturaTabela, larguraTabela);
        this.vez = 0;
    }
    
    /**
     * Gera a tabela estatica do jogador um
     * @param alturaTabela altura da tabela
     * @param larguraTabela largura da tabela
     */
    private void geraTabelaUm(int alturaTabela, int larguraTabela){
        this.tabelaJogadorUm = new Posicao[alturaTabela][larguraTabela];
        for(int i = 0; i < alturaTabela; i++){
            for(int j = 0; j < larguraTabela; j++){
                this.tabelaJogadorUm[i][j] = new Posicao(0,"-");
            }
        }
    }
    
    /**
     * Gera a tabela estatica do jogador dois
     * @param alturaTabela altura da tabela
     * @param larguraTabela largura da tabela
     */
    private void geraTabelaDois(int alturaTabela, int larguraTabela){
        this.tabelaJogadorDois = new Posicao[alturaTabela][larguraTabela];
        for(int i = 0; i < alturaTabela; i++){
            for(int j = 0; j < larguraTabela; j++){
                this.tabelaJogadorDois[i][j] = new Posicao(0,"-");
            }
        }
    }
    
    /**
     * Tabela do jogador um em formato de string para exibicao
     * @param tipo 0 = retorna as duas informacoes de posicao. 1 = retorna so o valor exibido. 2 = retorna so o valor logico.
     * @return 
     */
    public String tabelaUmToString(int tipo){
        String retorno = "";
        for(int i = 0; i < this.tabelaJogadorUm.length; i++){
            retorno += "[";
            for(int j = 0; j < this.tabelaJogadorUm[i].length; j++){
                if(tipo == 0){
                    retorno += "("+this.tabelaJogadorUm[i][j].getValorExibido()+","+this.tabelaJogadorUm[i][j].getValorLogico()+") ";
                }
                else if(tipo == 1){
                    retorno += this.tabelaJogadorUm[i][j].getValorExibido()+" ";
                }
                else if(tipo == 2){
                    retorno += this.tabelaJogadorUm[i][j].getValorLogico()+" ";
                }
            }
             retorno += "]\n";
        }
        return retorno;
    }
    
    /**
     * Tabela do jogador dois em formato de string para exibicao
     * @param tipo 0 = retorna as duas informacoes de posicao. 1 = retorna so o valor exibido. 2 = retorna so o valor logico.
     * @return 
     */
    public String tabelaDoisToString(int tipo){
        String retorno = "";
        for(int i = 0; i < this.tabelaJogadorDois.length; i++){
            retorno += "[";
            for(int j = 0; j < this.tabelaJogadorDois[i].length; j++){
                if(tipo == 0){
                    retorno += "("+this.tabelaJogadorDois[i][j].getValorExibido()+","+this.tabelaJogadorDois[i][j].getValorLogico()+") ";
                }
                else if(tipo == 1){
                    retorno += this.tabelaJogadorDois[i][j].getValorExibido()+" ";
                }
                else if(tipo == 2){
                    retorno += this.tabelaJogadorDois[i][j].getValorLogico()+" ";
                }
            }
             retorno += "]\n";
        }
        return retorno;
    }
            
}
