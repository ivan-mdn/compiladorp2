package leandrowilson.compilador;


public class Token {
	public TipoToken tipo;
	public String valor;
	public Integer linha;
	
	public Token(String strValor, TipoToken tipoToken,Integer linha) {
		valor = strValor;
		tipo = tipoToken;
		linha = linha;
	}
	
//	public Token(String tokenBuffer, String tipoToken) {
//		valor = tokenBuffer;
//		tipo = new TipoToken(tipoToken);
//	}
	
	public Token(String token,Integer linha) {
		tipo = TipoToken.QUEBRADO;
		valor = token;
		linha = linha;
	}
	
	public Token(){

	}

	public Token(TipoToken tipoToken,Integer linha) {
		tipo = tipoToken;
		valor ="";
		linha = linha;
	}
}
