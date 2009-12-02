package leandrowilson.compilador;

public enum TipoToken {
	//TODO Completar os tipos
	ID(0),
	NUMERO(1),
	STRING(3),
	PR_TRUE(6),
	PR_FALSE(7),
	PR_INT(8),
	PR_FLOAT(9),
	PR_STRING(10),
	PR_BOOLEAN(11),
	PR_PROCEDURE(12),
	PR_CALLPROC(13),
	PR_FUNCTION(14),
	PR_CALLFUNC(15),
	PR_IF(16),
	PR_ELSE(17),
	PR_WHILE(18),
	PR_INPUT(19),
	PR_OUTPUT(20),
	PR_RETURN(21),
	PARENTESE_ABRE(22), 
	PARENTESE_FECHA(23), 
	NOT(24), 
	COLCHETE_ABRE(25), 
	COLCHETE_FECHA(26), 
	CHAVE_ABRE(27), 
	CHAVE_FECHA(28), 
	OR(29), 
	AND(30), 
	DIFFERENT(31), 
	EQUAL_COMPARISON(32), 
	DIVIDE(33), 
	MULTIPLY(34), 
	PONTOEVIRGULA(35), 
	VIRGULA(36), 
	ATRIB(37), 
	MAIOR_IGUAL(38), 
	MENOR_IGUAL(39), 
	MAIOR(40), 
	MENOR(41),
	MINUS(42),
	PLUS(43),
	QUEBRADO(44),
	PR_MAIN(45),
	PR_STRUCT(46),
	PR_DECLARE(47),
	LAST(48);
	
	private final Integer valor;
	public Integer valor(){
		return this.valor;
	}
	public static Integer tamanho(){
		return 49;
	}
	
	TipoToken(Integer valor){
		this.valor = valor;
	}

	public static TipoToken tipoToken(String token) {
		if (token.equals("main"))
			return TipoToken.PR_MAIN;
		if (token.equals("declare"))
			return TipoToken.PR_DECLARE;
		if (token.equals("struct"))
			return TipoToken.PR_STRUCT;
		if (token.equals("true"))
			return TipoToken.PR_TRUE;
		if (token.equals("false"))
			return TipoToken.PR_FALSE;
		if (token.equals("int"))
			return TipoToken.PR_INT;
		if (token.equals("float"))
			return TipoToken.PR_FLOAT;
		if (token.equals("string"))
			return TipoToken.PR_STRING;
		if (token.equals("boolean"))
			return TipoToken.PR_BOOLEAN;
		if (token.equals("procedure"))
			return TipoToken.PR_PROCEDURE;
		if (token.equals("callproc"))
			return TipoToken.PR_CALLPROC;
		if (token.equals("procedure"))
			return TipoToken.PR_PROCEDURE;
		if (token.equals("function"))
			return TipoToken.PR_FUNCTION;
		if (token.equals("callfunc"))
			return TipoToken.PR_CALLFUNC;
		if (token.equals("if"))
			return TipoToken.PR_IF;
		if (token.equals("else"))
			return TipoToken.PR_ELSE;
		if (token.equals("while"))
			return TipoToken.PR_WHILE;
		if (token.equals("input"))
			return TipoToken.PR_INPUT;
		if (token.equals("output"))
			return TipoToken.PR_OUTPUT;
		if (token.equals("return"))
			return TipoToken.PR_RETURN;
		if (token.equals("("))
			return TipoToken.PARENTESE_ABRE ;
		if (token.equals(")"))
				return TipoToken.PARENTESE_FECHA ;
		if (token.equals("!"))
				return TipoToken.NOT ;
		if (token.equals("["))
				return TipoToken.COLCHETE_ABRE ;
		if (token.equals("]"))
				return TipoToken.COLCHETE_FECHA ;
		if (token.equals("{"))
				return TipoToken.CHAVE_ABRE ;
		if (token.equals("}"))
				return TipoToken.CHAVE_FECHA ;
		if (token.equals("|"))
				return TipoToken.OR ;
		if (token.equals("&"))
				return TipoToken.AND ;
		if (token.equals("!="))
				return TipoToken.DIFFERENT ;
		if (token.equals("=="))
				return TipoToken.EQUAL_COMPARISON ;
		if (token.equals("-"))
				return TipoToken.MINUS ;
		if (token.equals("/"))
				return TipoToken.DIVIDE ;
		if (token.equals("*"))
				return TipoToken.MULTIPLY ;
		if (token.equals("+"))
				return TipoToken.PLUS ;
		if (token.equals("<"))
				return TipoToken.MENOR ;
		if (token.equals(">"))
				return TipoToken.MAIOR ;
		if (token.equals("<="))
				return TipoToken.MENOR_IGUAL ;
		if (token.equals(">="))
				return TipoToken.MAIOR_IGUAL ;
		if (token.equals("="))
				return TipoToken.ATRIB ;
		if (token.equals(","))
				return TipoToken.VIRGULA ;
		if (token.equals(";"))
				return TipoToken.PONTOEVIRGULA ;
		if (token.equals("identificador"))
			return TipoToken.ID ;
		if (token.equals("tipo_string"))
			return TipoToken.STRING ;
		if (token.equals("inteiro"))
			return TipoToken.NUMERO ;
		if (token.equals("numero"))
			return TipoToken.NUMERO ;
		return TipoToken.QUEBRADO;
	}
	public static boolean isPalavraReservada(String token) {
		if (token.equals("main"))
			return true;
		if (token.equals("declare"))
			return true;
		if (token.equals("struct"))
			return true;
		if (token.equals("true"))
			return true;
		if (token.equals("false"))
			return true;
		if (token.equals("int"))
			return true;
		if (token.equals("float"))
			return true;
		if (token.equals("string"))
			return true;
		if (token.equals("boolean"))
			return true;
		if (token.equals("procedure"))
			return true;
		if (token.equals("callproc"))
			return true;
		if (token.equals("procedure"))
			return true;
		if (token.equals("function"))
			return true;
		if (token.equals("callfunc"))
			return true;
		if (token.equals("if"))
			return true;
		if (token.equals("else"))
			return true;
		if (token.equals("while"))
			return true;
		if (token.equals("input"))
			return true;
		if (token.equals("output"))
			return true;
		if (token.equals("return"))
			return true;
		return false;
	}
	public static boolean ehOpRelacional(TipoToken tipo) {
		return (tipo.equals(TipoToken.EQUAL_COMPARISON )||
				tipo.equals(TipoToken.MAIOR) ||
				tipo.equals(TipoToken.MENOR)||
				tipo.equals(TipoToken.MAIOR_IGUAL )||
				tipo.equals(TipoToken.MENOR_IGUAL)||
				tipo.equals(TipoToken.DIFFERENT));
	}
	public static boolean ehOpLogico(TipoToken tipo) {
		return (tipo.equals(TipoToken.EQUAL_COMPARISON )||
				tipo.equals(TipoToken.DIFFERENT) ||
				tipo.equals(TipoToken.AND)||
				tipo.equals(TipoToken.OR ));
	}
	public static boolean ehOpAritmetico(TipoToken tipo) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean ehTipoPrimitivo() {
		return (this.equals(TipoToken.PR_INT)||
				this.equals(TipoToken.PR_STRING) ||
				this.equals(TipoToken.PR_BOOLEAN)||
				this.equals(TipoToken.PR_FLOAT ));
	}

}
