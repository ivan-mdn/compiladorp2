package leandrowilson.compilador;

public enum TipoToken {
	//TODO Completar os tipos
	QUEBRADO(0),LAST(1), I(2), S(3), K(4), 
	ASPA(5), ASTERISCO(6), ZERO(7),UM(8), 
	ABRE_PAR(9), FECHA_PAR(10), I_MINUSCULO(11);
	
	private final Integer valor;
	public Integer valor(){
		return this.valor;
	}
	public static Integer tamanho(){
		return 12;
	}
	
	TipoToken(Integer valor){
		this.valor = valor;
	}

	public static TipoToken tipoToken(String token) {
		if (token.equals("i"))
			return TipoToken.I_MINUSCULO;
		if (token.equals("I"))
			return TipoToken.I;
		if (token.equals("s"))
			return TipoToken.S;
		if (token.equals("S"))
			return TipoToken.S;
		if (token.equals("k"))
			return TipoToken.K;
		if (token.equals("K"))
			return TipoToken.K;
		if (token.equals("`"))
			return TipoToken.ASPA;
		if (token.equals("*"))
			return TipoToken.ASTERISCO;
		if (token.equals("0"))
			return TipoToken.ZERO;
		if (token.equals("1"))
			return TipoToken.UM;
		if (token.equals("("))
			return TipoToken.ABRE_PAR;
		if (token.equals(")"))
			return TipoToken.FECHA_PAR;
		return TipoToken.QUEBRADO;
	}
}
