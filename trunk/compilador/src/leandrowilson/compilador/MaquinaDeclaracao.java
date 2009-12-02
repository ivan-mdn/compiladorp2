package leandrowilson.compilador;

public class MaquinaDeclaracao extends Maquina {
	
	MaquinaDeclaracao(){
		tipo=TipoMaquina.DECLARACAO;
		String strTransicoes = "initial:0%"+
		"final:7%"+
		"(0,\"int\")->1%"+
		"(0,\"float\")->1%"+
		"(0,\"string\")->1%"+
		"(0,\"boolean\")->1%"+
		"(0,\"declare\")->2%"+
		"(0,\"struct\")->3%"+
		"(1,\"[\")->4%"+
		"(1,\"identificador\")->5%"+
		"(2,\"identificador\")->1%"+
		"(3,\"identificador\")->6%"+
		"(4,\"inteiro\")->9%"+
		"(4,\"identificador\")->9%"+
		"(5,\";\")->7%"+
		"(6,\"{\")->8%"+
		"(8,declaracao)->8%"+
		"(8,\"}\")->7%"+
		"(9,\"]\")->1%";
		inicializarMaquina(10,strTransicoes,this.tipo);
		
	}

}
