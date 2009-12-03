package leandrowilson.compilador;

public enum TipoMaquina {
	PROGRAM(new TipoToken[] {TipoToken.I_MINUSCULO,TipoToken.I,TipoToken.K,TipoToken.S ,TipoToken.ZERO,TipoToken.UM ,TipoToken.ASPA , TipoToken.ASTERISCO,TipoToken.I_MINUSCULO,TipoToken.ABRE_PAR}),
	EXPR(new TipoToken[] {TipoToken.I_MINUSCULO,TipoToken.I,TipoToken.K,TipoToken.S ,TipoToken.ZERO,TipoToken.UM ,TipoToken.ASPA , TipoToken.ASTERISCO,TipoToken.I_MINUSCULO,TipoToken.ABRE_PAR}),
	IOTAEXPR(new TipoToken[] {TipoToken.I_MINUSCULO,TipoToken.I,TipoToken.K,TipoToken.S ,TipoToken.ZERO,TipoToken.UM ,TipoToken.ASPA , TipoToken.ASTERISCO,TipoToken.I_MINUSCULO,TipoToken.ABRE_PAR}),
	QUOTEEXPR(new TipoToken[] {TipoToken.ASPA}),
	ASTIOTA(new TipoToken[] {TipoToken.ASTERISCO}),
	EXPR2(new TipoToken[] {TipoToken.I,TipoToken.K,TipoToken.S ,TipoToken.ZERO,TipoToken.UM ,TipoToken.ASPA , TipoToken.ASTERISCO,TipoToken.I_MINUSCULO,TipoToken.ABRE_PAR}),
	NONEMPTYJOTEXPR(new TipoToken[] {TipoToken.ZERO,TipoToken.UM }),;

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
	
	public static TipoMaquina tipoMaquina(String maquina) {
		if (maquina.equals("Program"))
			return TipoMaquina.PROGRAM;
		if (maquina.equals("Expr"))
			return TipoMaquina.EXPR;
		if (maquina.equals("IotaExpr "))
			return TipoMaquina.IOTAEXPR;
		if (maquina.equals("quoteExp"))
			return TipoMaquina.QUOTEEXPR;
		if (maquina.equals("astIota"))
			return TipoMaquina.ASTIOTA;
		if (maquina.equals("Expr2"))
			return TipoMaquina.EXPR2;
		if (maquina.equals("NonemptyJotExpr"))
			return TipoMaquina.NONEMPTYJOTEXPR;
		return null;
	}
	public static boolean ehMaquina(String maquina) {
		if (maquina.equals("Program"))
			return true;
		if (maquina.equals("Expr"))
			return true;
		if (maquina.equals("IotaExpr "))
			return true;
		if (maquina.equals("quoteExp"))
			return true;
		if (maquina.equals("astIota"))
			return true;
		if (maquina.equals("Expr2"))
			return true;
		if (maquina.equals("NonemptyJotExpr"))
			return true;
		return false;
	}
}
