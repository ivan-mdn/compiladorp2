package leandrowilson.compilador;

public class MaquinaExpressao extends Maquina {
	MaquinaExpressao(){
		tipo=TipoMaquina.EXPRESSAO;
		String strTransicoes = "initial:0%"+
		"final:1%"+
		"(0,expbooleana)->1%"+
		"(0,exparitmetica)->1%"+
		"(0,expstring)->1%"+
		"(0,exprelacional)->1%"+
		"(0,\"callfunc\")->2%"+
		"(2,\"identificador\")->3%"+
		"(3,\"(\")->4%"+
		"(4,\"identificador\")->5%"+
		"(4,\"numero\")->6%"+
		"(4,\"string\")->6%"+
		"(4,\")\")->7%"+
		"(5,\"[\")->8%"+
		"(5,\",\")->9%"+
		"(5,\")\")->7%"+
		"(6,\",\")->9%"+
		"(6,\")\")->7%"+
		"(7,\";\")->1%"+
		"(8,\"identificador\")->10%"+
		"(8,\"inteiro\")->10%"+
		"(9,\"identificador\")->5%"+
		"(9,\"numero\")->6%"+
		"(9,\"string\")->6%"+
		"(10,\"]\")->5%";
		inicializarMaquina(11,strTransicoes,this.tipo);
	}

	public TipoMaquina proximaMaquina(Integer estadoAtual, Token tokemAtual,Token tokemAhead) {
		if(estadoAtual.equals(0)){
			switch(tokemAtual.tipo){
			case ID:
				if (TipoToken.ehOpRelacional(tokemAhead.tipo)){
					return TipoMaquina.EXPRELACIONAL;
				}
				else if (TipoToken.ehOpLogico(tokemAhead.tipo)){
					return TipoMaquina.EXPBOOLEANA;
				}
				else if (TipoToken.ehOpAritmetico(tokemAhead.tipo)){
					return TipoMaquina.EXPARITMETICA;
				}
				else{
					return TipoMaquina.EXPARITMETICA;
				}
			case NUMERO:
				if (TipoToken.ehOpRelacional(tokemAhead.tipo)){
					return TipoMaquina.EXPRELACIONAL;
				}
				else{
					return TipoMaquina.EXPARITMETICA;
				}
			default:
				return super.proximaMaquina(estadoAtual, tokemAtual);
			}
		}
		else{
			return super.proximaMaquina(estadoAtual, tokemAtual);
		}
		
	}
	
}
