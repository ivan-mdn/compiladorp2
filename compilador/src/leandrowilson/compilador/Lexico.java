package leandrowilson.compilador;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;




/**
 * Esta classe faz a analise lexica do codigo
 * @author Leandro Cordeiro David(leandrodvd@gmail.com) e Wilson Faria(wilsonfaria86@gmail.com)
 */
public class Lexico {
	public List erros = new List();
    private final int TAMANHOBUFFER = 4096;
    //private final int MAX_TAMANHO_TOKEN = 1024;
	private int estadoAtual ;
	private char[] bufferDeLeitura1;
	private char[] bufferDeLeitura2;
	private int bufferEmUso;
	private int posicaoDoBuffer;
	private TabelaDeTransicao tabelaDeTransicao;
	private List listaDeTokens = new List();
	//private char[] tokenBuffer; //buffer para ir armazenando caracter a cartecer lido. É resetado quando um token é gerado(volta pro estado 0)
	private StringBuffer tokenBuffer;
	//private int tokenBufferPointer; // Ponteiro para o tokenBuffer
	private File arquivoLido ; //Arquivo
	FileInputStream fis =null ; //FileInputStream
	BufferedInputStream bis =null; //BufferedInputStream
	DataInputStream dis =null; //DataInputStream
	private int linha = 1; //contagem de linhas do arquivo
//	public String[] palavrasReservadas=
//		{"true","false","int","float","string","boolean",
//			"procedure","callproc","function","callfunc",
//			"if","else","while","input","output","return"};
//	
	public Lexico() {
		tabelaDeTransicao= new TabelaDeTransicao();
		bufferDeLeitura1 = new char[TAMANHOBUFFER];
		bufferDeLeitura2 = new char[TAMANHOBUFFER];
		estadoAtual = TabelaDeTransicao.ESTADO_INICIAL;
		posicaoDoBuffer = -1;
		//tokenBuffer = new char[MAX_TAMANHO_TOKEN];
		tokenBuffer =new StringBuffer();
		//tokenBufferPointer = -1;
		
	}
	
	public List getErros(){
		return erros;
	}
	
	public List obterListaDeTokens(String nomeDoArquivo){
		leArquivo(nomeDoArquivo);
		analisaArquivo();
		listaDeTokens.add(new Token("",TipoToken.LAST,linha));
		return listaDeTokens;
	}
	
	/**
	 * Analisa o arquivo e gera os tokens - Esse metodo faz o grossodo lexico
	 * ELE RODA A MAQUINA DE AUTOMATO< FICA LENDO A TABELA DE TRANSICAO E TAL
	 * 
	 */
	private void analisaArquivo() {
		char ch = leProximoCaracter();
		Util.Log("Caracter lido:'"+ch+"'-"+(long)ch);
		//tokenBufferPointer++;
		//tokenBuffer[tokenBufferPointer]= ch;
		
		while (!fimDoArquivo(ch)){
			Util.Log(String.valueOf(ch));
			//System.out.print(ch+"'-"+(int)ch);
			if (descartarCaracter(ch)){
				ch = leProximoCaracter();
			}
			else{
				tokenBuffer.append(ch);
				listaDeTokens.add(new Token(TipoToken.tipoToken(String.valueOf(ch)), linha));
				ch = leProximoCaracter();
			}
		}
		
		//MUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
		if(!(tabelaDeTransicao.estadoFinal(estadoAtual)||tabelaDeTransicao.estadoInicial(estadoAtual))){
			//Acabou de ler o arquivo mas o ultimo caracter lido não levou a um estado final
			//Fecha um token quebrado - O analisador sintatico se vira para trata-lo
			fechaTokenQuebrado();
		}
		//
	}
	private boolean descartarCaracter(char ch) {
		//Descarta espaço só se estiver no estado inicial
		if(tabelaDeTransicao.estadoInicial(estadoAtual) && (int)ch==32){ 
			return true;
		}
		//Descarta caracter de line feed
		if((int)ch == 10){
			linha++;
			Util.Log("linha:"+linha);
			return true;
		}
		//Descarta caracter de carriage return
		if((int)ch == 13){
			return true;
		}
		//Descarta caracter de carriage TAB
		if((int)ch == 9){
			return true;
		}
		return false;
	}

	private void fechaTokenQuebrado() {
		Token token = new Token(tokenBuffer.toString(),obterTipoParaEstado(estadoAtual),linha);
		adicionaTokenNaLista(token);
		tokenBuffer = new StringBuffer();
		//tokenBuffer = new char[MAX_TAMANHO_TOKEN];
	}

	private void adicionaTokenNaLista(Token token) {
		//Util.Log("Adicionando Token na Lista-Tipo:"+token.tipo+" Valor:"+String.valueOf(token.valor));
		this.listaDeTokens.add(token);
		Util.Log("<"+token.tipo+","+token.valor+">");
	}

	/**
	 * Verifica a qual automato um estado pertence</br>
	 * usado para quando precisar gerar um token sem ter atingido um estado final
	 * @param estado
	 * @return int Tipo
	 */
	private TipoToken obterTipoParaEstado(int estado) {
		// TODO Auto-generated method stub
		return TipoToken.QUEBRADO;
	}

//	private void executaAcaoDeEstadoFinal(int estado) {
//		switch(estado)
//		{	
//			case TabelaDeTransicao.ESTADO_GERATOKEM_ID:
//				retornaCaracter();
//				if (TipoToken.isPalavraReservada(tokenBuffer.toString())){
//					adicionaTokenNaLista(new Token(TipoToken.tipoToken(tokenBuffer.toString()),linha));
//				}
//				else{
//					adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.ID,linha));
//				}
//				limpaTokenBuffer();
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				break;
//			case TabelaDeTransicao.ESTADO_GERATOKEM_OPERADOR:
//				adicionaTokenNaLista(new Token(TipoToken.tipoToken(tokenBuffer.toString()),linha));
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				limpaTokenBuffer();
//				break;
//			case TabelaDeTransicao.ESTADO_GERATOKEM_OPERADOR_RET:
//				retornaCaracter();
//				adicionaTokenNaLista(new Token(TipoToken.tipoToken(tokenBuffer.toString()),linha));
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				limpaTokenBuffer();
//				break;
//			case TabelaDeTransicao.ESTADO_GERATOKEM_NUMERO:
//				retornaCaracter();
//				adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.NUMERO,linha));
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				limpaTokenBuffer();
//				break;
//			case TabelaDeTransicao.ESTADO_GERATOKEM_STRING:
//				adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.STRING,linha));
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				limpaTokenBuffer();
//				break;
//			case TabelaDeTransicao.ESTADO_ERRO:
//				fechaTokenQuebrado();
//				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
//				limpaTokenBuffer();
//				break;
//				//demais cases de estado final
//		}
//		
//	}

//	private boolean palavrareservada(String identificador) {
//		for (int i =0;i<palavrasReservadas.length;i++){
//			if (identificador.equals(palavrasReservadas[i])){
//				return true;
//			}
//		}
//		return false;
//	}

	private void retornaCaracter() {
		
		if (posicaoDoBuffer==0 || posicaoDoBuffer==-1){
			posicaoDoBuffer=TAMANHOBUFFER-2;
			if (bufferEmUso ==1){
				bufferEmUso=2;
			}
			else{
				bufferEmUso=1;
			}
		}
		else{
			posicaoDoBuffer--;
		}
		tokenBuffer.deleteCharAt(tokenBuffer.length()-1);
	}

	private void limpaTokenBuffer() {
		tokenBuffer = new StringBuffer();
		//tokenBufferPointer=-1;
		
	}

	/**
	 * Este metodo le o arquivo inicializa corretamente o buffer
	 * @param nomeDoArquivo2
	 */
	private void leArquivo(String nomeDoArquivo) {
		    arquivoLido = new File(nomeDoArquivo);
		    Util.Log("Lendo arquivo:"+nomeDoArquivo);
		    fis = null;
		    bis = null;
		    dis = null;

		    try {
			    if (!arquivoLido.canRead()){
			    	Util.Log("Arquivo não disponível para leitura");
			    }
			    else
			    {
			      fis = new FileInputStream(arquivoLido);
	
			      // Here BufferedInputStream is added for fast reading.
			      bis = new BufferedInputStream(fis);
			      dis = new DataInputStream(bis);
			      // dis.available() returns 0 if the file does not have more lines.
			      if (dis.available()!= 0) {
			    	  carregaBuffer1();
			      }
			      else{
			    	  erros.add(new Erro(TipoErro.LEXICO_ARQUIVO_EM_BRACO,(Token) listaDeTokens.get(listaDeTokens.tamanho-1)));
			      }
	
			      // dispose all the resources after using them.
			      //fis.close();
			      //bis.close();
			      //dis.close();
			    }
		    } catch (FileNotFoundException e) {
		    	Util.Log("Arquivo não encontrado");
		    	
		      e.printStackTrace();
		    } catch (IOException e) {
		    	erros.add(new Erro(TipoErro.LEXICO_ERRO_DE_ES,(Token)listaDeTokens.get(listaDeTokens.tamanho-1)));
		      e.printStackTrace();
		    }		
	}

	
	/**
	 * @param args
	 */
	public  char leProximoCaracter() {
		
		return leProximoCaracterDoBuffer();

	}

	private char leProximoCaracterDoBuffer() {
		
		if (bufferEmUso ==1){
			posicaoDoBuffer++;
			char caracterLido = bufferDeLeitura1[posicaoDoBuffer]; 
			if (chegouNoFimDoBuffer()){
				carregaBuffer2();
			}
			return caracterLido;
		}
		else {
			posicaoDoBuffer++;
			char caracterLido = bufferDeLeitura2[posicaoDoBuffer];
			if (chegouNoFimDoBuffer()){
				carregaBuffer1();
			}
			return caracterLido;
		}
		
	}

	private void carregaBuffer1() {
		atualizaBuffer1();
		bufferEmUso = 1 ;
		posicaoDoBuffer = -1;
	}

	private void carregaBuffer2() {
		atualizaBuffer2();
		bufferEmUso = 2 ;
		posicaoDoBuffer = -1;
	}

	private void atualizaBuffer1() {
		
		try {
			for(int i =0 ;i<TAMANHOBUFFER ;i++){
				if( dis.available()!= 0){
					bufferDeLeitura1[i]=(char) dis.read();
				}
				else{
					bufferDeLeitura1[i]=(char) 0;
					break;
				}
				
			}
		} catch (IOException e) {
			erros.add(new Erro(TipoErro.LEXICO_ERRO_DE_LEITURA_DE_ARQUIVO,(Token)listaDeTokens.get(listaDeTokens.tamanho-1)));
			e.printStackTrace();
		}
		
	}

	private boolean fimDoArquivo(char ch) {
		return (0==(int)ch);
	}

	private void atualizaBuffer2() {
		try {
			for(int i =0 ;i<TAMANHOBUFFER ;i++){
				if( dis.available()!= 0){
					bufferDeLeitura2[i]=(char) dis.read();
				}
				else{
					bufferDeLeitura2[i]=(char) 0;
				}
				
			}
		} catch (IOException e) {
			erros.add(new Erro(TipoErro.LEXICO_ERRO_DE_LEITURA_DE_ARQUIVO,(Token)listaDeTokens.get(listaDeTokens.tamanho-1)));
			e.printStackTrace();
		}
		
	}

	private boolean chegouNoFimDoBuffer() {
		return posicaoDoBuffer==TAMANHOBUFFER-1;
	}

}
