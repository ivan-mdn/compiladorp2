package leandrowilson.compilador;

public class TabelaDeTransicao {
	private int[][] tabelaDeTransicao;
	//Estado final de erro  - alterar posteriormente
	public static final int ESTADO_INICIAL = 2;
	public static final int ESTADO_ERRO =14;
	public static final int ESTADO_GERATOKEM_ID = 0;
	public static final int ESTADO_GERATOKEM_OPERADOR = 8;
	public static final int ESTADO_GERATOKEM_OPERADOR_RET = 13;//Esse tem um retorno de caracter
	public static final int ESTADO_GERATOKEM_NUMERO = 12;
	public static final int ESTADO_GERATOKEM_STRING = 11;
	
	private final int ASCII_TAMANHO = 256;
    private final int ESTADOS_QUANT = 13+1;//Os estados vão de 0 a N entao tem N mais 1 estados :-)
    private int[] estadosFinais;
    
    
	public TabelaDeTransicao() {
		Util.Log("Construtor da tabela de transiçao");
		tabelaDeTransicao = new int[ESTADOS_QUANT][ASCII_TAMANHO];	
		iniciaTabelaDeTransicao();
		iniciaEstadosFinais();
	}

	private void iniciaEstadosFinais() {
		Util.Log("iniciaEstadosFinais");
		estadosFinais = new int[ESTADOS_QUANT];
		for (int i=0;i<ESTADOS_QUANT;i++){
			estadosFinais[i] = ESTADO_ERRO;
		}
		estadosFinais[0] = ESTADO_GERATOKEM_ID;
		estadosFinais[1] = ESTADO_GERATOKEM_OPERADOR;
		estadosFinais[2] = ESTADO_GERATOKEM_OPERADOR_RET;
		estadosFinais[3] = ESTADO_GERATOKEM_NUMERO;
		estadosFinais[4] = ESTADO_GERATOKEM_STRING;
	}

	
	public int proximoEstado(int estadoAtual,int caracterLido){
		//Retorna o devido elemento da tabela de transição
		//Util.Log("Proximo estado:estadoAtual:"+String.valueOf(estadoAtual)+" caracterLido:"+String.valueOf(caracterLido));
		return tabelaDeTransicao[estadoAtual][caracterLido];
	}
	
	private void iniciaTabelaDeTransicao() {
		Util.Log("iniciaTabelaDeTransicao");
		//preenche toda a Tabela de Tranição com Estado de Erro
		for (int i = 0;i<ESTADOS_QUANT;i++){
			for (int j = 0;j<ASCII_TAMANHO;j++){
				tabelaDeTransicao[i][j]=ESTADO_ERRO;
			}
		}		
		//Preenche a Tabela de Transição
		//tabelaDeTransicao[ESTADO][CARACTER] = 0;
		//tabelaDeTransicao[0][0] = 0;
		
		//preenche transição dos estados finais
		for (int i = 0;i<ASCII_TAMANHO;i++){
			tabelaDeTransicao[0][i]=2;
			tabelaDeTransicao[8][i]=2;
			tabelaDeTransicao[11][i]=2;
			tabelaDeTransicao[12][i]=2;
			tabelaDeTransicao[13][i]=2;
		}
		
		//preenche o padrão dos outros estados (preenche com a maioria da tabela)
		//Deixei separado do FOR acima para ficar organizado, os estados abaixo ainda serão alterados
		for (int i = 0;i<ASCII_TAMANHO;i++){
			tabelaDeTransicao[1][i]=0;
			tabelaDeTransicao[2][i]=ESTADO_ERRO;
			tabelaDeTransicao[3][i]=12;
			tabelaDeTransicao[4][i]=ESTADO_ERRO;
			tabelaDeTransicao[5][i]=12;
			tabelaDeTransicao[6][i]=13;
			tabelaDeTransicao[7][i]=ESTADO_ERRO;
			tabelaDeTransicao[9][i]=13;
			tabelaDeTransicao[10][i]=10;
		}
		
		//preenche [A-Za-z] dos estados
		//A = 65
		//Z = 90
		//a = 97
		//z = 122
		for (int i = 65;i<=122;i++){
			tabelaDeTransicao[1][i]=1;
			tabelaDeTransicao[2][i]=1;
			//Gambiarra pra pular os caracteres intermediários --> Não testado
			if(i == 90) i = 96;
		}
		
		//preenche [0-9] dos estados
		//'0' = 48
		//'9' = 57
		for (int i = 48;i<=57;i++){
			tabelaDeTransicao[1][i]=1;
			tabelaDeTransicao[2][i]=3;
			tabelaDeTransicao[3][i]=3;
			tabelaDeTransicao[4][i]=5;
			tabelaDeTransicao[5][i]=5;
		}

		//preenche o resto
		tabelaDeTransicao[2][33]=6;
		tabelaDeTransicao[2][34]=10;
		tabelaDeTransicao[2][38]=8;
		tabelaDeTransicao[2][40]=8;
		tabelaDeTransicao[2][41]=8;
		tabelaDeTransicao[2][42]=8;
		tabelaDeTransicao[2][43]=8;
		tabelaDeTransicao[2][44]=8;
		tabelaDeTransicao[2][45]=8;
		tabelaDeTransicao[2][47]=8;
		tabelaDeTransicao[2][59]=8;
		tabelaDeTransicao[2][91]=8;
		tabelaDeTransicao[2][93]=8;
		tabelaDeTransicao[2][123]=8;
		tabelaDeTransicao[2][124]=8;
		tabelaDeTransicao[2][125]=8;
		tabelaDeTransicao[2][60]=9;
		tabelaDeTransicao[2][61]=6;
		tabelaDeTransicao[2][62]=9;
		tabelaDeTransicao[3][46]=4;
		tabelaDeTransicao[6][61]=8;
		tabelaDeTransicao[7][61]=8;
		tabelaDeTransicao[9][61]=8;
		tabelaDeTransicao[10][34]=11;
		
		//(
		tabelaDeTransicao[10][34]=11;
		//)
	}

	public boolean estadoFinal(int estado) {

		boolean achouEstadoFinal = false;
		for (int i = 0;i<ESTADOS_QUANT;i++)
		{
			achouEstadoFinal=achouEstadoFinal || (estadosFinais[i]==estado);
		}
		//if (achouEstadoFinal) {Util.Log("Estado "+estado+" é Estado final ");}
		//else {Util.Log("Estado "+estado+" NAO é Estado final ");}
		
		return achouEstadoFinal;
	}

	public boolean estadoInicial(int estadoAtual) {
		return estadoAtual==ESTADO_INICIAL;
	}
}
