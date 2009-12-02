package leandrowilson.compilador;

public class MaquinaProcedimento extends Maquina {
	MaquinaProcedimento(){
		tipo=TipoMaquina.PROCEDIMENTO;
		String strTransicoes = "initial:0%"+
		"final:10%"+
		"(0,\"procedure\")->1%"+
		"(1,\"identificador\")->2%"+
		"(2,\"(\")->3%"+
		"(3,\"identificador\")->4%"+
		"(3,\"int\")->4%"+
		"(3,\"float\")->4%"+
		"(3,\"string\")->4%"+
		"(3,\"boolean\")->4%"+
		"(3,\")\")->5%"+
		"(4,\"identificador\")->6%"+
		"(4,\"[\")->7%"+
		"(5,\"{\")->8%"+
		"(6,\",\")->11%"+
		"(6,\")\")->5%"+
		"(7,\"identificador\")->9%"+
		"(7,\"inteiro\")->9%"+
		"(8,declaracao)->8%"+
		"(8,comando)->8%"+
		"(8,\"}\")->10%"+
		"(9,\"]\")->4%"+
		"(11,\"identificador\")->4%"+
		"(11,\"int\")->4%"+
		"(11,\"float\")->4%"+
		"(11,\"string\")->4%"+
		"(11,\"boolean\")->4%";
		inicializarMaquina(12,strTransicoes,this.tipo);
	}

}
