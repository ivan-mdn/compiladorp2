package leandrowilson.compilador;

public class ElementoSemantico {
	Escopo escopo;
	TipoMaquina tipoMaquina;
	Integer transicaoSemantica;
	PilhaSemantico pilhaSemantico;
	Token token;
	
	public ElementoSemantico() {

	}

	public ElementoSemantico(Escopo escopo, TipoMaquina tipoMaquina,Integer transicaoSemantica, PilhaSemantico pilhaSemnatico,Token token) {
		this.escopo = escopo;
		this.tipoMaquina = tipoMaquina;
		this.transicaoSemantica = transicaoSemantica;
		this.pilhaSemantico = pilhaSemnatico;
		this.token = token;
	}
	
}
