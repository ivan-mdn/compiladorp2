package leandrowilson.compilador;

public class MaquinaExpBooleana extends Maquina {
	MaquinaExpBooleana(){
		tipo=TipoMaquina.EXPBOOLEANA;
		String strTransicoes = "initial:0%"+
		"final:1,2%"+
		"(0,\"identificador\")->1%"+
		"(0,\"true\")->2%"+
		"(0,\"false\")->2%"+
		"(0,\"(\")->3%"+
		"(0,\"!\")->4%"+
		"(1,\"[\")->5%"+
		"(1,\"|\")->0%"+
		"(1,\"&\")->0%"+
		"(1,\"!=\")->0%"+
		"(1,\"==\")->0%"+
		"(2,\"|\")->0%"+
		"(2,\"&\")->0%"+
		"(2,\"!=\")->0%"+
		"(2,\"==\")->0%"+
		"(3,expbooleana)->6%"+
		"(4,\"(\")->3%"+
		"(5,\"identificador\")->7%"+
		"(5,\"inteiro\")->7%"+
		"(6,\")\")->2%"+
		"(7,\"]\")->1%";
		inicializarMaquina(8,strTransicoes,this.tipo);
	}

}
