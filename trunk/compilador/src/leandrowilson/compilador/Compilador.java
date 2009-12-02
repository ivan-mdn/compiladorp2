package leandrowilson.compilador;

import java.io.File;
import java.util.Scanner;


public class Compilador {
	static Scanner scanner = new Scanner (System.in);
	public static Lexico lex = new Lexico();
	public static Sintatico sintatico;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String nomeDoArquivo;
		
		System.out.println("ESTE É O COMPILADOR DESENVOLVIDO POR LEANDRO CORDEIRO DAVID E WILSON FARIA\n\n");
		System.out.println("Digite o nome do arquivo que deseja compilar:");
		
		
		nomeDoArquivo = scanner.next();
//		nomeDoArquivo = "c:\\teste.txt";
//		nomeDoArquivo = "c:\\teste_reservadas.txt";
//		nomeDoArquivo = "c:\\testePrograma.txt";
//		nomeDoArquivo = "c:\\testeSemantico.txt";
		System.out.println("Lendo arquivo:"+nomeDoArquivo);
		nomeDoArquivo = nomeDoArquivo.replace("\\", "\\\\");
	    System.out.println("Lendo arquivo:"+nomeDoArquivo);
//	    testalexico(nomeDoArquivo);
		sintatico = new Sintatico(lex.obterListaDeTokens(nomeDoArquivo));
		if (sintatico.executa()){
			System.out.println("Sintatico finalizado com sucesso!");
		}
		else{
			System.out.println("Sintatico finalizado com ERRO!");
			printErrorList(sintatico.getErros());
		}
	}
	private static void printErrorList(List erros) {
		Erro erro =null;
		for(int i =0;i<erros.tamanho;i++){
			erro =(Erro)erros.get(i);
			System.out.println(erro.tipo.mensagem+" Token:<"+erro.token.tipo.toString()+","+erro.token.valor+">"+" Linha:"+((erro.token.linha!=null)?erro.token.linha.toString():"") );
		}
	}
	private static void testalexico(String nomeDoArquivo) {
		List listaDeTokens = lex.obterListaDeTokens(nomeDoArquivo);
		imprimeListaDeTokens(listaDeTokens);
		
	}
	private static void imprimeListaDeTokens(List listaDeTokens) {
		Token token;
		for(int i =0;i<listaDeTokens.tamanho;i++){
			token = (Token)listaDeTokens.get(i);
			System.out.println(i+"-<"+token.tipo.toString()+","+String.valueOf(token.valor)+">");
		}
		
	}

}
