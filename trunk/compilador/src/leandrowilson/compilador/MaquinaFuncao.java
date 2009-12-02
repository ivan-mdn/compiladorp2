package leandrowilson.compilador;

public class MaquinaFuncao extends Maquina {
	
	MaquinaFuncao(){
		tipo=TipoMaquina.FUNCAO;
		String strTransicoes = "initial:0%"+
		"final:13%"+
		"(0,\"function\")->1%"+
		"(1,\"int\")->2%"+
		"(1,\"identificador\")->2%"+
		"(1,\"float\")->2%"+
		"(1,\"string\")->2%"+
		"(1,\"boolean\")->2%"+
		"(2,\"[\")->3%"+
		"(2,\"identificador\")->4%"+
		"(3,\"inteiro\")->6%"+
		"(3,\"identificador\")->6%"+
		"(4,\"(\")->5%"+
		"(5,\"int\")->7%"+
		"(5,\"identificador\")->7%"+
		"(5,\"float\")->7%"+
		"(5,\"string\")->7%"+
		"(5,\"boolean\")->7%"+
		"(5,\")\")->8%"+
		"(6,\"]\")->2%"+
		"(7,\"[\")->14%"+
		"(7,\"identificador\")->15%"+
		"(8,\"{\")->9%"+
		"(9,declaracao)->9%"+
		"(9,comando)->9%"+
		"(9,\"return\")->10%"+
		"(10,\"identificador\")->11%"+
		"(11,\";\")->12%"+
		"(12,\"}\")->13%"+
		"(14,\"inteiro\")->17%"+
		"(14,\"identificador\")->17%"+
		"(15,\",\")->16%"+
		"(15,\")\")->8%"+
		"(16,\"int\")->7%"+
		"(16,\"identificador\")->7%"+
		"(16,\"float\")->7%"+
		"(16,\"string\")->7%"+
		"(16,\"boolean\")->7%"+
		"(17,\"]\")->7%";
		inicializarMaquina(18,strTransicoes,this.tipo);
	}
	
}
