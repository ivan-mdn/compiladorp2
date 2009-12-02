package leandrowilson.compilador;

public class Sintatico {
	List listaDeTokens;
	List erros = new List();
	Integer ponteiroTokem = -1;
	
	//Maquinas De Analise Sintatica/Semantica
	MaquinaPrograma maquinaPrograma = new MaquinaPrograma();
	MaquinaFuncao maquinaFuncao = new MaquinaFuncao();
	MaquinaProcedimento maquinaProcedimento = new MaquinaProcedimento();
	MaquinaDeclaracao maquinaDeclaracao = new MaquinaDeclaracao();
	MaquinaComando maquinaComando= new MaquinaComando();
	MaquinaExpressao maquinaExpressao= new MaquinaExpressao();
	MaquinaExpBooleana maquinaExpBooleana = new MaquinaExpBooleana();
	MaquinaExpRelacional maquinaExpRelacional = new MaquinaExpRelacional();
	MaquinaExpAritmetica maquinaExpAritmetica = new MaquinaExpAritmetica();
	MaquinaExpString maquinaExpString = new MaquinaExpString();
	
	
	public Sintatico(List ListaDeTokens) {
		this.listaDeTokens = ListaDeTokens;
	}
	public List getErros(){
		return erros;
	}
	public Boolean executa(){
		Escopo escopoAtual = new Escopo();
		ElementoSemantico elSemantico = new ElementoSemantico();
		Integer transicaoSemantica = null;
		PilhaSemantico pilhaSemantico = new PilhaSemantico();
		Semantico semantico = new Semantico();
		PilhaSintatico pilhaSintatico = new PilhaSintatico();
		Integer estadoAtual=0;
		Integer proximoEstado =0;
		TipoMaquina maquinaAtual = TipoMaquina.PROGRAMA;
		TipoMaquina proximaMaquina= TipoMaquina.PROGRAMA;
		Boolean novaMaquina = false;
		Boolean retornaMaquina = false;
		Token tokemAtual = new Token();
		ElementoSintatico desempilha=new ElementoSintatico();		
		try {
			tokemAtual= proximoTokem();
		} catch (Exception e) {
			return false;
		}
		pilhaSintatico.push(0, TipoMaquina.PROGRAMA);
		
		
		while(!pilhaSintatico.isEmpty()){
			
			switch (maquinaAtual){
				case PROGRAMA:
					proximoEstado 	= maquinaPrograma.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaPrograma.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaPrograma.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaPrograma.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaPrograma.transSemantica(estadoAtual,tokemAtual);
					break;
				case FUNCAO:
					proximoEstado 	= maquinaFuncao.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaFuncao.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaFuncao.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaFuncao.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaFuncao.transSemantica(estadoAtual,tokemAtual);
					break;
				case PROCEDIMENTO:
					proximoEstado 	= maquinaProcedimento.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaProcedimento.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaProcedimento.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaProcedimento.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaProcedimento.transSemantica(estadoAtual,tokemAtual);
					break;
				case DECLARACAO:
					proximoEstado 	= maquinaDeclaracao.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaDeclaracao.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaDeclaracao.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaDeclaracao.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaDeclaracao.transSemantica(estadoAtual,tokemAtual);
					break;
				case COMANDO:
					proximoEstado 	= maquinaComando.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaComando.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaComando.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaComando.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaComando.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPRESSAO:
					proximoEstado 	= maquinaExpressao.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaExpressao.proximaMaquina(estadoAtual, tokemAtual, (Token) listaDeTokens.get(ponteiroTokem+1));
					novaMaquina 	= maquinaExpressao.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaExpressao.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaExpressao.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPBOOLEANA:
					proximoEstado 	= maquinaExpBooleana.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaExpBooleana.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaExpBooleana.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaExpBooleana.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaExpBooleana.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPRELACIONAL:
					proximoEstado 	= maquinaExpRelacional.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaExpRelacional.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaExpRelacional.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaExpRelacional.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaExpRelacional.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPARITMETICA:
					proximoEstado 	= maquinaExpAritmetica.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaExpAritmetica.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaExpAritmetica.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaExpAritmetica.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaExpAritmetica.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPSTRING:
					proximoEstado 	= maquinaExpString.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaExpString.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaExpString.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaExpString.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaExpString.transSemantica(estadoAtual,tokemAtual);
					break;
			}
			Util.Log("Maquina:"+maquinaAtual.toString()+"  Estado:"+estadoAtual.toString()+" Token:<"+tokemAtual.tipo.toString()+","+tokemAtual.valor+">");
			elSemantico = new ElementoSemantico(escopoAtual, maquinaAtual, transicaoSemantica, pilhaSemantico,tokemAtual);
			elSemantico = semantico.analisa(elSemantico);
			escopoAtual = elSemantico.escopo;
			pilhaSemantico = elSemantico.pilhaSemantico;
			if (novaMaquina){
				pilhaSintatico.push(proximoEstado,maquinaAtual);
				maquinaAtual = proximaMaquina;
				estadoAtual = 0;
			}
			else if(retornaMaquina){
				desempilha = pilhaSintatico.pop();
				estadoAtual = desempilha.estado;
				maquinaAtual = desempilha.maquina;
			}
			else{
				estadoAtual = proximoEstado;
				maquinaAtual = proximaMaquina;
				try {
					tokemAtual= proximoTokem();
				} catch (Exception e) {
					return false;
				}
			}
			
		}
		semantico.geraArquivoMVN();
		if (!fimDosTokens()){
			erros.add(new Erro(TipoErro.SINTATICO_FIMDEPILHA_ANTES_DO_FIM_DOS_TOKENS,tokemAtual));
			return false;
		}
		else{
			return true;
		}
		
	}
private boolean fimDosTokens() {
		return ponteiroTokem==listaDeTokens.tamanho-1;
	}

private Token proximoTokem() throws Exception {
		ponteiroTokem++;
		if (ponteiroTokem.equals(listaDeTokens.tamanho)){
			erros.add(new Erro(TipoErro.SINTATICO_FIMDETOKENS_ANTES_DO_FIM_DA_PILHA,(Token) listaDeTokens.get(ponteiroTokem-1)));
			return new Token(TipoToken.LAST,-1);
		}
		if (ponteiroTokem>=(listaDeTokens.tamanho)){
			erros.add(new Erro(TipoErro.SINTATICO,(Token) listaDeTokens.get(listaDeTokens.tamanho-1)));
			throw new Exception();
		}
		else{
			return (Token)listaDeTokens.get(ponteiroTokem);
		}
		
	}
}
