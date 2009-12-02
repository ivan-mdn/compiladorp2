package leandrowilson.compilador;

public class MaquinaExpString extends Maquina {
	MaquinaExpString(){
		tipo=TipoMaquina.EXPSTRING;
		String strTransicoes = "initial:0%"+
		"final:1%"+
		"(0,\"tipo_string\")->1%"+
		"(1,\"+\")->2%"+
		"(2,\"tipo_string\")->1%"+
		"(2,\"identificador\")->1%";
		inicializarMaquina(3,strTransicoes,this.tipo);
	}

}
