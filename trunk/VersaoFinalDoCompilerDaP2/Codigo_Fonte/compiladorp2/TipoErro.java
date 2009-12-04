package leandrowilson.compiladorp2;

public enum TipoErro {
	LEXICO("Erro Lexico"),
	SINTATICO("Erro sint�tico"), 
	SINTATICO_FIMDEPILHA_ANTES_DO_FIM_DOS_TOKENS("Erro Sint�tico - Pilha Sint�tica esvaziada antes do fim dos tokens"), 
	SINTATICO_FIMDETOKENS_ANTES_DO_FIM_DA_PILHA("Erro Sint�tico - Fim de tokens sem esvaziar a pilha sint�tica - Esqueceu de algo no fim do c�digo?Parece meio incompleto."), 
	LEXICO_ARQUIVO_EM_BRACO("Erro L�xico - Arquivo em branco"), 
	LEXICO_ERRO_DE_ES("Erro L�xico - Erro de E/S"), 
	LEXICO_ERRO_DE_LEITURA_DE_ARQUIVO("Erro na leitura de caracter do arquivo"), 
	SEMANTICO_TRANS_SEMANTICA_INESPERADA("Erro Sem�ntico - Transic�o semantica inesperada"), 
	SINTATICO_ESTADO_SINTATICO_INESPERADO("Erro Sint�tico - Estado sint�tico inesperado atingido"), 
	SEMANTICO("Erro Semantico"), 
	SEMANTICO_REDECALRACAO_DE_VARIAVEL("Erro Semantico - Variavel j� foi declarada no escopo"), 
	SEMANTICO_VARIAVEL_NAO_DECLARADA("Erro Semantico - Vari�vel N�o Declarada"), 
	SEMANTICO_TIPOS_DE_DADOS_INCOMPATIVEIS("Erro Semantico - Tipo de dados incompativeis");
	
	public String mensagem;
	
	TipoErro(String mensagem){
		this.mensagem = mensagem;
	}
}
