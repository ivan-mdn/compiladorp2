package leandrowilson.compilador;

public abstract class Maquina {
	public TipoMaquina tipo =null;
	public  Integer estadoInicial;
	public Integer[] estadosFinais;
	Integer[][] tabelaTransicao ; //Tabela de Transicao de Estados
	TipoMaquina[][] tabelaTransicaoMaquinas;
	Boolean[][] tabelaNovaMaquina;
	Integer[][] tabelaTransicaoSemantica;
	List erros = new List();
	
	public Integer  QUANTIDADE_DE_TIPOTOKENS = TipoToken.tamanho();
	public final Integer ESTADO_ERRO_SINTATICO = 999999;
	
	
	public Integer proximoEstado(Integer estadoAtual, Token tokemAtual) {
		if (estadoAtual == ESTADO_ERRO_SINTATICO){
			geraErroSintatico(estadoAtual,tokemAtual);
			return estadoInicial;
		}
		return tabelaTransicao[estadoAtual][tokemAtual.tipo.valor()];
	}
	public void geraErroSintatico(Integer estadoAtual, Token tokemAtual) {
		erros.add(new Erro(TipoErro.SINTATICO_ESTADO_SINTATICO_INESPERADO,tokemAtual));
	}
	public TipoMaquina proximaMaquina(Integer estadoAtual, Token tokemAtual) {
		if (estadoAtual == ESTADO_ERRO_SINTATICO){
			geraErroSintatico(estadoAtual,tokemAtual);
			return tipo;
		}
		return tabelaTransicaoMaquinas[estadoAtual][tokemAtual.tipo.valor()];
	}
	public Boolean novaMaquina(Integer estadoAtual, Token tokemAtual) {
		if (estadoAtual == ESTADO_ERRO_SINTATICO){
			geraErroSintatico(estadoAtual,tokemAtual);
			return false;
		}
		return tabelaNovaMaquina[estadoAtual][tokemAtual.tipo.valor()];
	}
//	public abstract void carregarTabelaDeTransicao();
	public Boolean retornaMaquina(Integer estadoAtual,Token tokemAtual) {
		if (estadoFinal(estadoAtual)){//Se o estado atual não é final, não é retorno de máquina
			if (proximoEstado(estadoAtual, tokemAtual)==ESTADO_ERRO_SINTATICO){//se o proximo estado do estado atual é estado de erro, é pq nao há nova transicao e a maquina deve voltar
				return true;
			}
			else {
				return false;
			}
		}
		else{
			return false;
		}
	}

	public void inicializarMaquina(int quantidadeDeEstados,String strTransicoes, TipoMaquina tipoMaquina) {
		estadoInicial = Util.obterEstadoInicial(strTransicoes);
		estadosFinais = Util.obterEstadosFinais(strTransicoes);
		tabelaTransicao = new Integer[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		tabelaTransicaoMaquinas = new TipoMaquina[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		tabelaNovaMaquina = new Boolean[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		tabelaTransicaoSemantica = new Integer[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		
		for (int k =0;k<quantidadeDeEstados;k++){
			for (int j=0;j<QUANTIDADE_DE_TIPOTOKENS;j++){
				tabelaTransicao[k][j] = ESTADO_ERRO_SINTATICO;
				tabelaTransicaoMaquinas[k][j] = tipoMaquina;
				tabelaNovaMaquina[k][j] = false;
				tabelaTransicaoSemantica[k][j] =-1;
			}
		}
		List transicoes = Util.obterTransicoes(strTransicoes);
		for(int i=0;i<transicoes.tamanho;i++){
			TransicaoSintatica t = (TransicaoSintatica) transicoes.get(i);
			if (t.transicaoParaMaquina){
				carregarEntradasDeSubmaquina(t.estadoInicial, t.maquina, t.proximoEstado,t.id);
			}
			else{
				carregarEntradaNaTabelaDeTransicao(t.estadoInicial, t.token, t.proximoEstado, tipoMaquina, false,t.id);
			}
		}
	}
	
//	public void inicializarMaquina(int quantidadeDeEstados, int estadoInicial,int estadoFinal,String strTransicoes) {
//		Integer[] estadosFinais = {estadoFinal};
//		inicializarMaquina(quantidadeDeEstados, estadoInicial, estadosFinais,strTransicoes);
//		
//	}
	public Integer transSemantica(Integer estadoAtual, Token token){
		if (estadoAtual == ESTADO_ERRO_SINTATICO){
			geraErroSintatico(estadoAtual,token);
			return -1;
		}
		return tabelaTransicaoSemantica[estadoAtual][token.tipo.valor()];
	}
	
	public Boolean estadoErro(Integer estado){
		return estado==ESTADO_ERRO_SINTATICO;
		
	}
	public void carregarEntradaNaTabelaDeTransicao(Integer estadoAtual, TipoToken token,Integer proximoEstado, TipoMaquina proximaMaquina, boolean chamaNovaMaquina,Integer transicaoSemantica) {
		tabelaTransicao[estadoAtual][token.valor()]= proximoEstado;
		tabelaTransicaoMaquinas[estadoAtual][token.valor()]= proximaMaquina;
		tabelaNovaMaquina[estadoAtual][token.valor()]=chamaNovaMaquina;
		tabelaTransicaoSemantica[estadoAtual][token.valor()]=transicaoSemantica;
		
	}
	public void carregarEntradasDeSubmaquina(Integer estadoAtual, TipoMaquina maquina,Integer proximoEstado, Integer id) {
		TipoToken[] first = maquina.first();
		for (int i = 0;i<first.length;i++){
			carregarEntradaNaTabelaDeTransicao(estadoAtual, first[i], proximoEstado,maquina,true,id);
		}
		
	}
	public Boolean estadoFinal(Integer estado){
		for (int i = 0;i<estadosFinais.length;i++){
			if (estado.equals(estadosFinais[i])) {
				return true;
			}
		}
		return false;
		
	}
//	public void carregarTabelaDeTransicao(String strTransicoes){
//		
//	}
	
}
