package leandrowilson.compilador;

public class MaquinaExpAritmetica extends Maquina {
	MaquinaExpAritmetica(){
		tipo=TipoMaquina.EXPARITMETICA;
		String strTransicoes = "initial:0%"+
		"final:2,3%"+
		"(0,\"-\")->1%"+
		"(0,\"+\")->1%"+
		"(1,\"identificador\")->2%"+
		"(1,\"numero\")->3%"+
		"(1,\"(\")->4%"+
		"(2,\"-\")->1%"+
		"(2,\"+\")->1%"+
		"(2,\"[\")->6%"+
		"(2,\"/\")->1%"+
		"(2,\"*\")->1%"+
		"(3,\"-\")->1%"+
		"(3,\"+\")->1%"+
		"(3,\"/\")->1%"+
		"(3,\"*\")->1%"+
		"(4,exparitmetica)->5%"+
		"(5,\")\")->3%"+
		"(6,\"identificador\")->7%"+
		"(6,\"inteiro\")->7%"+
		"(7,\"]\")->2%";
		inicializarMaquina(8,strTransicoes,this.tipo);
	}

}
