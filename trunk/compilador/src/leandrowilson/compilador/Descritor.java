package leandrowilson.compilador;

public class Descritor {
	
	private int end_true;
	private int end_false;
	
	TipoDescritor tipo;
	String label;
	Object[] valor;
	Integer posicaoInicial;
	Integer tamanho;
	private Integer[] indicesMaximos;
	private Descritor[] DescritorStrings;
	private Integer indicesMaximosTam;
	
	//VAL_INT E VAL_BOOL
	Descritor(Object valor, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		tamanho = 1;
		valor = new Object[tamanho];
		this.valor[0] = valor;
	}
	
	//VAL_STRING
	Descritor(Object[] valor, Integer tamanho, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.tamanho = tamanho;
		this.valor = valor;
	}
	
	//VAR_INT E VAR_BOOL
	Descritor(String label,  TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
	}
	
	//VAR_STRING
	Descritor(String label, Integer tamanho, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.tamanho = tamanho;
	}
	
	//VVAR_INT E VVAR_BOOL
	Descritor(String label, Integer[] indices, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indicesMaximos;
		this.indicesMaximosTam = indicesMaximosTam;
	}
	
	//VVAR_STRING
	Descritor(String label, Descritor[] strings, Integer[] indicesMaximos, Integer indicesMaximosTam, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indicesMaximos;
		this.indicesMaximosTam = indicesMaximosTam; 
		this.DescritorStrings = strings;
	}
	
	void AtualizaPosicao(Integer posicao){
		this.posicaoInicial = posicao;
	}
	
	void AtualizaEndBool(Integer posicaoTrue, Integer posicaoFalse){
		end_false = posicaoFalse;
		end_true = posicaoTrue;
	}
	
	Object GetValor(){
		return valor;
	}
	
	int GetPosicao(Integer[] indices){
		Integer posicao = posicaoInicial + indicesMaximos[indicesMaximosTam-1];
		for(Integer i = indicesMaximosTam-2; i>=0; i--){
			posicao+=produtorio(i,indicesMaximosTam-1,indicesMaximos);
		}
		return posicao;
	}
	
	private int produtorio(Integer inicio, Integer fim, Integer[] vetor){
		int result = 1;
		for(int i = inicio; i<=fim; i++){
			result *=vetor[i];
		}
		return result;
		
	}
	
}
