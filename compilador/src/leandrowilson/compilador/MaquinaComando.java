package leandrowilson.compilador;

public class MaquinaComando extends Maquina {
	MaquinaComando(){
		tipo=TipoMaquina.COMANDO;
		String strTransicoes = "initial:0%"+
		"final:11,22%"+
		"(0,\"identificador\")->1%"+
		"(0,\"if\")->2%"+ 
		"(0,\"while\")->3%"+
		"(0,\"input\")->4%"+ 
		"(0,\"output\")->4%"+
		"(0,\"callproc\")->5%"+
		"(1,\"[\")->6%"+
		"(1,\"=\")->7%"+
		"(2,\"(\")->17%"+
		"(3,\"(\")->26%"+
		"(4,\"identificador\")->25%"+
		"(5,\"identificador\")->8%"+
		"(6,\"identificador\")->15%"+
		"(6,\"inteiro\")->15%"+
		"(7,expressao)->9%"+
		"(8,\"(\")->10%"+
		"(9,\";\")->11%"+
		"(10,\"identificador\")->12%"+
		"(10,\")\")->9%"+"(10,\"numero\")->13%"+
		"(10,\"string\")->13%"+"(12,\"[\")->16%"+
		"(12,\")\")->9%"+
		"(12,\",\")->14%"+
		"(13,\")\")->9%"+
		"(13,\",\")->14%"+
		"(14,\"identificador\")->12%"+
		"(14,\"numero\")->13%"+
		"(14,\"string\")->13%"+
		"(15,\"]\")->1%"+
		"(16,\"identificador\")->18%"+
		"(16,\"inteiro\")->18%"+
		"(17,expbooleana)->19%"+
		"(18,\"]\")->12%"+
		"(19,\")\")->20%"+
		"(20,\"{\")->21%"+
		"(21,declaracao)->21%"+
		"(21,comando)->21%"+
		"(21,\"}\")->22%"+
		"(22,\"else\")->23%"+
		"(23,\"{\")->24%"+
		"(24,declaracao)->24%"+
		"(24,comando)->24%"+
		"(24,\"}\")->11%"+
		"(25,\"[\")->27%"+
		"(25,\";\")->11%"+
		"(26,expbooleana)->28%"+
		"(27,\"identificador\")->29%"+
		"(27,\"inteiro\")->29%"+
		"(28,\")\")->23%"+
		"(29,\"]\")->25%";
		inicializarMaquina(30,strTransicoes,this.tipo);
	}
	



}
