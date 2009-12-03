package leandrowilson.compilador;

public class Sintatico {
	List listaDeTokens;
	List erros = new List();
	Integer ponteiroTokem = -1;
	
	//Maquinas De Analise Sintatica/Semantica
	String strProgram = "initial: 0%"+
						"final: 1%"+
						"(0, Expr) -> 1%"+
						"(1, Expr) -> 1%"; 
	Maquina MaquinaProgram = new Maquina(TipoMaquina.PROGRAM,strProgram, 2);
	
	String strExpr = "initial: 0%"+
	"final: 1%"+
	"(0, \"i\") -> 1%"+
	"(0, Expr_) -> 1%";
	Maquina MaquinaExpr = new Maquina(TipoMaquina.EXPR,strExpr,1);
	
	String strIotaExpr = "initial: 0%"+
	"final: 1%"+
	"(0, \"i\") -> 1%"+
	"(0, Expr_) -> 1%";
	
	Maquina maquinaIotaExpr = new Maquina(TipoMaquina.IOTAEXPR,strIotaExpr,1);
	
	String strQuoteExpr = "initial: 0%"+
	"final: 3%"+
	"(0, \"`\") -> 1%"+
	"(1, Expr) -> 2%"+
	"(2, Expr) -> 3%";
	
	Maquina MaquinaQuoteExpr = new Maquina(TipoMaquina.QUOTEEXPR,strQuoteExpr,3);
	
	String strAstIota = "initial: 0%"+
	"final: 3%"+
	"(0, \"*\") -> 1%"+
	"(1, IotaExpr) -> 2%"+
	"(2, IotaExpr) -> 3%";
	
	Maquina MaquinaAstIota= new Maquina(TipoMaquina.ASTIOTA,strAstIota,3);
	
	String strExpr2 = "initial: 0%"+
	"final: 1%"+
	"(0, \"I\") -> 1%"+
	"(0, \"K\") -> 1%"+
	"(0, \"k\") -> 1%"+
	"(0, \"S\") -> 1%"+
	"(0, \"s\") -> 1%"+
	"(0, NonemptyJotExpr) -> 1%"+
	"(0, quoteExp) -> 1%"+
	"(0, IotaExpr) -> 1%"+
	"(0, \"(\") -> 2%"+
	"(2, CCExpr) -> 3%"+
	"(3, \")\") -> 1%";
	
	Maquina MaquinaExpr2= new Maquina(TipoMaquina.EXPR2,strExpr2,11);
	
	String strNonemptyJotExpr = "initial: 0%"+
	"final: 1%"+
	"(0, \"0\") -> 1%"+
	"(0, \"1\") -> 1%"+
	"(1, \"0\") -> 1%"+
	"(1, \"1\") -> 1%";
	Maquina  MaquinaNonemptyJotExpr = new Maquina(TipoMaquina.NONEMPTYJOTEXPR,strNonemptyJotExpr,4);
	
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
		TipoMaquina maquinaAtual = TipoMaquina.PROGRAM;
		TipoMaquina proximaMaquina= TipoMaquina.PROGRAM;
		Boolean novaMaquina = false;
		Boolean retornaMaquina = false;
		Token tokemAtual = new Token();
		ElementoSintatico desempilha=new ElementoSintatico();		
		try {
			tokemAtual= proximoTokem();
		} catch (Exception e) {
			return false;
		}
		pilhaSintatico.push(0, TipoMaquina.PROGRAM);
		
		
		while(!pilhaSintatico.isEmpty()){
			
			switch (maquinaAtual){
				case ASTIOTA:
					proximoEstado 	= MaquinaAstIota.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaAstIota.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaAstIota.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaAstIota.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaAstIota.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPR:
					proximoEstado 	= MaquinaExpr.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaExpr.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaExpr.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaExpr.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaExpr.transSemantica(estadoAtual,tokemAtual);
					break;
				case EXPR2:
					proximoEstado 	= MaquinaExpr2.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaExpr2.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaExpr2.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaExpr2.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaExpr2.transSemantica(estadoAtual,tokemAtual);
					break;
				case IOTAEXPR:
					proximoEstado 	= maquinaIotaExpr.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaIotaExpr.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaIotaExpr.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaIotaExpr.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= maquinaIotaExpr.transSemantica(estadoAtual,tokemAtual);
					break;
				case NONEMPTYJOTEXPR:
					proximoEstado 	= MaquinaNonemptyJotExpr.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaNonemptyJotExpr.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaNonemptyJotExpr.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaNonemptyJotExpr.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaNonemptyJotExpr.transSemantica(estadoAtual,tokemAtual);
					break;
				case PROGRAM:
					proximoEstado 	= MaquinaProgram.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaProgram.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaProgram.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaProgram.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaProgram.transSemantica(estadoAtual,tokemAtual);
					break;
				case QUOTEEXPR:
					proximoEstado 	= MaquinaQuoteExpr.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= MaquinaQuoteExpr.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= MaquinaQuoteExpr.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= MaquinaQuoteExpr.retornaMaquina(estadoAtual,tokemAtual);
					transicaoSemantica= MaquinaQuoteExpr.transSemantica(estadoAtual,tokemAtual);
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
