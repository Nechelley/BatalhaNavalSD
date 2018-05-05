package batalhanavalsd;

/**
 *
 * @author Nechelley Alves
 */
public class Jogo {
    private Posicao tabelaJogadorUm[][];//tabela do primeiro jogador, onde estao suas pecas
    private Posicao tabelaJogadorDois[][];//tabela do segundo jogador, onde estao suas pecas
    private int pontosTabelaUm;//quantos pontos podem ser feitos na tabela um
    private int pontosTabelaDois;//quantos pontos podem ser feitos na tabela dois
    private int vez;//0 = vez do jogador 1. 1 = vez do jogador dois
    
    /**
     * Construtor
     * @param alturaTabela altura da tabela de ambos os jogadores
     * @param larguraTabela largura da tabela de ambos os jogadores
     */
    public Jogo(int alturaTabela, int larguraTabela){
        this.tabelaJogadorUm = new Posicao[alturaTabela][larguraTabela];
        geraTabela(this.tabelaJogadorUm);
        this.pontosTabelaUm = calculaPontuacao(this.tabelaJogadorUm);
        
        this.tabelaJogadorDois = new Posicao[alturaTabela][larguraTabela];
        geraTabela(this.tabelaJogadorDois);
        this.pontosTabelaDois = calculaPontuacao(this.tabelaJogadorDois);
        
        this.vez = 0;
    }
    
    /**
     * Gera a tabela estatica do jogador
     * @param alturaTabela altura da tabela
     * @param larguraTabela largura da tabela
     * @param tabela tabela a ser gerada
     */
    private void geraTabela(Posicao tabela[][]){
        for(int i = 0; i < tabela.length; i++){
            for(int j = 0; j < tabela[i].length; j++){
                tabela[i][j] = new Posicao(0,"-");
            }
        }
        
        tabela[1][1].setValorLogico(1);
        tabela[1][2].setValorLogico(1);
        tabela[1][3].setValorLogico(1);
        
        tabela[6][5].setValorLogico(1);
        tabela[6][6].setValorLogico(1);
        tabela[6][7].setValorLogico(1);
        
        tabela[4][8].setValorLogico(1);
        tabela[5][8].setValorLogico(1);
        tabela[6][8].setValorLogico(1);
    }
    
    /**
     * Calcula a pontuacao da tabela
     * @param tabela tabela sobre a qual a pontuacao sera gerada
     */
    private int calculaPontuacao(Posicao tabela[][]){
        int valor = 0;
        for(int i = 0; i < tabela.length; i++){
            for(int j = 0; j < tabela[i].length; j++){
                if(tabela[i][j].getValorLogico() == 1){
                    valor++;
                }
            }
        }
        return valor;
    }
    
    
    
    
    /**
     * Executa a jogada passada, o tabuleiro selecionado e baseado no atributo vez.
     * Codigos de resposta e seus significados:
     * 0 = alvo ja foi acertado anteriormente, selecione outro.
     * 1 = errou o alvo.
     * 2 = acertou um alvo.
     * 3 = jogo acabou.
     * @param x linha da tabela
     * @param y coluna da tabela
     */
    public int fazerAcao(int x, int y){
        Posicao tabelaAlvo[][] = this.tabelaJogadorUm;
        if(vez == 0){//jogador um esta acertando a tabela do jogador dois
            tabelaAlvo = this.tabelaJogadorDois;
        }
        Posicao alvo = tabelaAlvo[x][y];
        
        //verifica se o lugar ja foi acertado
        if(alvo.getValorExibido().equals("X") || alvo.getValorExibido().equals("O")){
            return 0;
        }
        
        if(vez == 0){
            vez = 1;
        }
        else{
            vez = 0;
        }
        
        //atira
        if(alvo.getValorLogico() == 0){//errou o tiro
            alvo.setValorExibido("X");
            return 1;
        }
        else{
            alvo.setValorExibido("O");
            
            //testar se o jogo acabou
            if(vez == 0){//jogador um esta acertando a tabela do jogador dois
                this.pontosTabelaDois--;
                if(this.pontosTabelaDois == 0){//acabou o jogo
                    return 3;
                }
            }
            else{
                this.pontosTabelaUm--;
                if(this.pontosTabelaUm == 0){//acabou o jogo
                    return 3;
                }
            }
            return 2;
        }
    }
    
    
    /**
     * Tabela do jogador um em formato de string para exibicao
     * @param tabela 1 ou 2;
     * @param tipo 0 = retorna as duas informacoes de posicao. 1 = retorna so o valor exibido. 2 = retorna so o valor logico.
     * @return 
     */
    public String tabelaToString(int tabela, int tipo){
        Posicao tabelaEscolhida[][] = this.tabelaJogadorUm;
        if(tabela == 2){
            tabelaEscolhida = this.tabelaJogadorDois;
        }
        
        String retorno = "";
        for(int i = 0; i < tabelaEscolhida.length; i++){
            retorno += "[";
            for(int j = 0; j < tabelaEscolhida[i].length; j++){
                switch (tipo) {
                    case 0:
                        retorno += "("+tabelaEscolhida[i][j].getValorExibido()+","+tabelaEscolhida[i][j].getValorLogico()+") ";
                        break;
                    case 1:
                        retorno += tabelaEscolhida[i][j].getValorExibido()+" ";
                        break;
                    case 2:
                        retorno += tabelaEscolhida[i][j].getValorLogico()+" ";
                        break;
                    default:
                        break;
                }
            }
             retorno += "]\n";
        }
        return retorno;
    }
            
}
