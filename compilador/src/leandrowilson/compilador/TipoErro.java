package leandrowilson.compilador;

public enum TipoErro {
	LEXICO("Erro Lexico"),
	SINTATICO("Erro sintático"), 
	SINTATICO_FIMDEPILHA_ANTES_DO_FIM_DOS_TOKENS("Erro Sintático - Pilha Sintática esvaziada antes do fim dos tokens"), 
	SINTATICO_FIMDETOKENS_ANTES_DO_FIM_DA_PILHA("Erro Sintático - Fim de tokens sem esvaziar a pilha sintática - Esqueceu de algo no fim do código?Parece meio incompleto."), 
	LEXICO_ARQUIVO_EM_BRACO("Erro Léxico - Arquivo em branco"), 
	LEXICO_ERRO_DE_ES("Erro Léxico - Erro de E/S"), 
	LEXICO_ERRO_DE_LEITURA_DE_ARQUIVO("Erro na leitura de caracter do arquivo"), 
	SEMANTICO_TRANS_SEMANTICA_INESPERADA("Erro Semântico - Transicão semantica inesperada"), 
	SINTATICO_ESTADO_SINTATICO_INESPERADO("Erro Sintático - Estado sintático inesperado atingido"), 
	SEMANTICO("Erro Semantico"), 
	SEMANTICO_REDECALRACAO_DE_VARIAVEL("Erro Semantico - Variavel já foi declarada no escopo"), 
	SEMANTICO_VARIAVEL_NAO_DECLARADA("Erro Semantico - Variável Não Declarada"), 
	SEMANTICO_TIPOS_DE_DADOS_INCOMPATIVEIS("Erro Semantico - Tipo de dados incompativeis");
	
	public String mensagem;
	
	TipoErro(String mensagem){
		this.mensagem = mensagem;
	}
}
