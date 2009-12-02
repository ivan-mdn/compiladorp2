package leandrowilson.compilador;

public class TransicaoSintatica {
	Boolean transicaoParaMaquina;
	Integer estadoInicial;
	TipoMaquina maquina;
	TipoToken token;
	Integer proximoEstado;
	Integer id;
	
	public TransicaoSintatica(boolean transicaoParaMaquina, String estadoInicial, String entrada,String proximoEstado,Integer id) {
		this.transicaoParaMaquina = transicaoParaMaquina;
		this.estadoInicial = new Integer(estadoInicial);
		this.proximoEstado = new Integer(proximoEstado);
		this.id = id;
		if (transicaoParaMaquina){
			maquina = TipoMaquina.tipoMaquina(entrada);
		}
		else{
			token = TipoToken.tipoToken(entrada);
		}
	}

}
