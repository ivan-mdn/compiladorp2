package leandrowilson.compiladorp2;

public class Erro {
	TipoErro tipo;
	Token token;


	public Erro(TipoErro tipoErro, Token tokenAtual) {
		this.tipo = tipoErro;
		this.token = tokenAtual;
	}

}
