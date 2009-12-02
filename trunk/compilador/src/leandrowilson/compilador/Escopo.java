package leandrowilson.compilador;

public class Escopo {
	Escopo escopoPai;
	TabelaDeSimbolos tabelaDeSimbolos;
	int id_escopo;
	
	Escopo(){
		id_escopo = 0;
		this.escopoPai = null;
		this.tabelaDeSimbolos = new TabelaDeSimbolos();
	}
	
	Escopo(Escopo escopoPai, int id){
		this.id_escopo = id;
		this.escopoPai = escopoPai;
		this.tabelaDeSimbolos = new TabelaDeSimbolos();
	}
	
	void AddSimbolo(String chave, Descritor descritor){
		tabelaDeSimbolos.AddSimbolo(chave, descritor);
	}
	
	Boolean existeNaTabela(String chave){
		Boolean existe = false;
		if((tabelaDeSimbolos.containsKey(chave)) || (escopoPai != null && escopoPai.tabelaDeSimbolos.containsKey(chave)) ){
			existe = true;
		}
		return existe;
	}
	
	Descritor busca(String chave){
		return (Descritor) tabelaDeSimbolos.get(chave);
	}
}
