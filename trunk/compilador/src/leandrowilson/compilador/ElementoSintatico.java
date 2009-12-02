package leandrowilson.compilador;

public class ElementoSintatico {
	Integer estado = 0;
	TipoMaquina maquina;
	public ElementoSintatico(Integer estadoAtual, TipoMaquina maquinaAtual) {
		estado = estadoAtual;
		maquina =maquinaAtual;
	}
	public ElementoSintatico() {
		// TODO Auto-generated constructor stub
	}
}
