package leandrowilson.compilador;

public class MaquinaPrograma extends Maquina {
	
	MaquinaPrograma(){
		tipo=TipoMaquina.PROGRAMA;
		String strTransicoes = "initial:0%"+
		"final:3%"+
		"(0,\"main\")->1%"+
		"(1,\"{\")->2%"+
		"(2,declaracao)->2%"+
		"(2,comando)->2%"+
		"(2,\"}\")->3%"+
		"(3,funcao)->3%"+
		"(3,procedimento)->3";
		inicializarMaquina(4,strTransicoes,this.tipo);
		
	}
}
