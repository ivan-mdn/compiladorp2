package leandrowilson.compilador;

public class MaquinaExpRelacional extends Maquina {
	MaquinaExpRelacional(){
		tipo=TipoMaquina.EXPRELACIONAL;
		String strTransicoes = "initial:0%"+
		"final:1,5,6%"+
		"(0,\"identificador\")->1%"+
		"(0,\"numero\")->2%"+
		"(1,\"[\")->3%"+
		"(1,\"<\")->4%"+
		"(1,\">\")->4%"+
		"(1,\"<=\")->4%"+
		"(1,\">=\")->4%"+
		"(1,\"==\")->4%"+
		"(1,\"!=\")->4%"+
		"(2,\"<\")->4%"+
		"(2,\">\")->4%"+
		"(2,\"<=\")->4%"+
		"(2,\">=\")->4%"+
		"(2,\"==\")->4%"+
		"(2,\"!=\")->4%"+
		"(3,\"identificador\")->7%"+
		"(3,\"inteiro\")->7%"+
		"(4,\"identificador\")->5%"+
		"(4,\"numero\")->6%"+
		"(5,\"[\")->8%"+
		"(7,\"]\")->1%"+
		"(8,\"identificador\")->9%"+
		"(8,\"inteiro\")->9%"+
		"(9,\"]\")->5%";
		inicializarMaquina(10,strTransicoes,this.tipo);
	}

}
