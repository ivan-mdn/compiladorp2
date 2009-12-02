package leandrowilson.compilador;

public enum TipoMaquina {
	PROGRAMA(new TipoToken[] {TipoToken.PR_MAIN}),
	FUNCAO(new TipoToken[] {TipoToken.PR_FUNCTION}),
	PROCEDIMENTO(new TipoToken[] {TipoToken.PR_PROCEDURE}),
	DECLARACAO(new TipoToken[] {TipoToken.PR_INT,TipoToken.PR_STRING,TipoToken.PR_BOOLEAN,TipoToken.PR_DECLARE , TipoToken.PR_STRUCT}),
	COMANDO(new TipoToken[] {TipoToken.ID,TipoToken.PR_IF,TipoToken.PR_WHILE,TipoToken.PR_INPUT,TipoToken.PR_OUTPUT,TipoToken.PR_CALLPROC}),
	EXPRESSAO(new TipoToken[] {TipoToken.PR_CALLPROC,TipoToken.ID,TipoToken.PR_TRUE ,TipoToken.PR_FALSE , TipoToken.PARENTESE_ABRE,TipoToken.NOT,TipoToken.ID,TipoToken.NUMERO,TipoToken.MINUS,TipoToken.PLUS,TipoToken.STRING}),
	EXPBOOLEANA(new TipoToken[] {TipoToken.ID,TipoToken.PR_TRUE ,TipoToken.PR_FALSE , TipoToken.PARENTESE_ABRE,TipoToken.NOT }),
	EXPRELACIONAL(new TipoToken[] {TipoToken.ID,TipoToken.NUMERO}),
	EXPARITMETICA(new TipoToken[] {TipoToken.MINUS,TipoToken.PLUS}),
	EXPSTRING(new TipoToken[] {TipoToken.STRING});

	private final TipoToken[] first;
	public TipoToken[] first() {
		// TODO Auto-generated method stub
		return this.first;
	}
	TipoMaquina(TipoToken[] _first){
		this.first = _first;
		
	}
	public Boolean pertenceAoFirt(TipoMaquina tipoMaquina,TipoToken tipoToken){
		TipoToken[] first = tipoMaquina.first();
		for(int i=0;i<first.length;i++){
			if (tipoToken == first[i]){
				return true;
			}
		}
		return false;
	
	}
	public Boolean pertenceAoFirt(TipoToken tipoToken){
		TipoToken[] first = this.first();
		for(int i=0;i<first.length;i++){
			if (tipoToken == first[i]){
				return true;
			}
		}
		return false;
	
	}
	
	public static Boolean ehMaquina(String maquina){
		if (maquina.equals("programa"))
			return true;
		if (maquina.equals("funcao"))
			return true;
		if (maquina.equals("procedimento"))
			return true;
		if (maquina.equals("declaracao"))
			return true;
		if (maquina.equals("comando"))
			return true;
		if (maquina.equals("expressao"))
			return true;
		if (maquina.equals("expbooleana"))
			return true;
		if (maquina.equals("exprelacional"))
			return true;
		if (maquina.equals("exparitmetica"))
			return true;
		if (maquina.equals("expstring"))
			return true;
		return false;
	}
	public static TipoMaquina tipoMaquina(String maquina) {
		if (maquina.equals("programa"))
			return TipoMaquina.PROGRAMA;
		if (maquina.equals("funcao"))
			return TipoMaquina.FUNCAO;
		if (maquina.equals("procedimento"))
			return TipoMaquina.PROCEDIMENTO;
		if (maquina.equals("declaracao"))
			return TipoMaquina.DECLARACAO;
		if (maquina.equals("comando"))
			return TipoMaquina.COMANDO;
		if (maquina.equals("expressao"))
			return TipoMaquina.EXPRESSAO;
		if (maquina.equals("expbooleana"))
			return TipoMaquina.EXPBOOLEANA;
		if (maquina.equals("exprelacional"))
			return TipoMaquina.EXPRELACIONAL;
		if (maquina.equals("exparitmetica"))
			return TipoMaquina.EXPARITMETICA;
		if (maquina.equals("expstring"))
			return TipoMaquina.EXPSTRING;
		return null;
	}
}
