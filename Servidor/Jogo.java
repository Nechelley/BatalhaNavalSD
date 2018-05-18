import java.util.Random;

/**
 *
 * @author Nechelley Alves
 */
public class Jogo{
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

		int numeroPontos = 10;
		int coordX, coordY;

		Random rand = new Random();

		while(numeroPontos > 0) {

			// gera posição aleatória
			coordX = rand.nextInt(tabela.length);
			coordY = rand.nextInt(tabela.length);

			if (tabela[coordX][coordY].getValorLogico() == 0) {
				tabela[coordX][coordY].setValorLogico(1);;
				numeroPontos--;
			}

		}

		// tabela[1][1].setValorLogico(1);
		// tabela[1][2].setValorLogico(1);
		// tabela[1][3].setValorLogico(1);

		// tabela[6][5].setValorLogico(1);
		// tabela[6][6].setValorLogico(1);
		// tabela[6][7].setValorLogico(1);

		// tabela[4][8].setValorLogico(1);
		// tabela[5][8].setValorLogico(1);
		// tabela[6][8].setValorLogico(1);
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
	 * 4 = posicao invalida, por ser fora da tabela.
	 * @param x linha da tabela
	 * @param y coluna da tabela
	 * @return codigo dizendo o que ocorreu
	 */
	public int fazerAcao(int x, int y){
		Posicao tabelaAlvo[][] = this.getTabelaJogadorUm();
		if(getVez() == 0){//jogador um esta acertando a tabela do jogador dois
			tabelaAlvo = this.getTabelaJogadorDois();
		}
		if(x >= tabelaJogadorUm.length || y >= tabelaJogadorUm[0].length || x < 0 || y < 0){
			return 4;
		}
		Posicao alvo = tabelaAlvo[x][y];

		//verifica se o lugar ja foi acertado
		if(alvo.getValorExibido().equals("X") || alvo.getValorExibido().equals("O")){
			return 0;
		}

		if(getVez() == 0){
			setVez(1);
		}
		else{
			setVez(0);
		}

		//atira
		if(alvo.getValorLogico() == 0){//errou o tiro
			alvo.setValorExibido("X");
			return 1;
		}
		else{//acertou o tiro
			alvo.setValorExibido("O");

			//testar se o jogo acabou
			if(getVez() == 0){//jogador um esta acertando a tabela do jogador dois
				this.setPontosTabelaDois(this.getPontosTabelaDois() - 1);
				if(this.getPontosTabelaDois() == 0){//acabou o jogo
					return 3;
				}
			}
			else{
				this.setPontosTabelaUm(this.getPontosTabelaUm() - 1);
				if(this.getPontosTabelaUm() == 0){//acabou o jogo
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
		Posicao tabelaEscolhida[][] = this.getTabelaJogadorUm();
		if(tabela == 2){
			tabelaEscolhida = this.getTabelaJogadorDois();
		}

		String retorno = "";
		for(int i = 0; i < tabelaEscolhida.length; i++){
			retorno += "[";
			for(int j = 0; j < tabelaEscolhida[i].length; j++){
				switch (tipo){
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
			retorno += "]lin";
		}
		return retorno;
	}


	/**
	 * Retorna true se a partda ja acabou e false caso ainda esteja ocorrendo
	*/
	public Boolean jogoAcabou(){
		if(pontosTabelaUm == 0 || pontosTabelaDois == 0){
			return true;
		}
		return false;
	}

	//GETS && SETS


	/**
	 * @return the tabelaJogadorUm
	 */
	public Posicao[][] getTabelaJogadorUm(){
		return tabelaJogadorUm;
	}

	/**
	 * @param tabelaJogadorUm the tabelaJogadorUm to set
	 */
	public void setTabelaJogadorUm(Posicao[][] tabelaJogadorUm){
		this.tabelaJogadorUm = tabelaJogadorUm;
	}

	/**
	 * @return the tabelaJogadorDois
	 */
	public Posicao[][] getTabelaJogadorDois(){
		return tabelaJogadorDois;
	}

	/**
	 * @param tabelaJogadorDois the tabelaJogadorDois to set
	 */
	public void setTabelaJogadorDois(Posicao[][] tabelaJogadorDois){
		this.tabelaJogadorDois = tabelaJogadorDois;
	}

	/**
	 * @return the pontosTabelaUm
	 */
	public int getPontosTabelaUm(){
		return pontosTabelaUm;
	}

	/**
	 * @param pontosTabelaUm the pontosTabelaUm to set
	 */
	public void setPontosTabelaUm(int pontosTabelaUm){
		this.pontosTabelaUm = pontosTabelaUm;
	}

	/**
	 * @return the pontosTabelaDois
	 */
	public int getPontosTabelaDois(){
		return pontosTabelaDois;
	}

	/**
	 * @param pontosTabelaDois the pontosTabelaDois to set
	 */
	public void setPontosTabelaDois(int pontosTabelaDois){
		this.pontosTabelaDois = pontosTabelaDois;
	}

	/**
	 * @return the vez
	 */
	public int getVez(){
		return vez;
	}

	/**
	 * @param vez the vez to set
	 */
	public void setVez(int vez){
		this.vez = vez;
	}

}
