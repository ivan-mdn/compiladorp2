package leandrowilson.compilador;
import java.util.Hashtable;

public class TabelaDeSimbolos{

	Hashtable table = new Hashtable();
	
	public void AddSimbolo(String chave, Descritor descritor) {
		table.put(chave, descritor);
	}

	public boolean containsKey(String chave) {
		return table.containsKey(chave);
	}

	public Descritor get(String chave) {
		return (Descritor)table.get(chave);
	}
}
