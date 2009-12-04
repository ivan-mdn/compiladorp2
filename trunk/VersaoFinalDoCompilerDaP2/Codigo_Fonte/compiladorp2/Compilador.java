package leandrowilson.compiladorp2;

import java.awt.GradientPaint;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class Compilador {
	static Scanner scanner = new Scanner (System.in);
	public static Lexico lex = new Lexico();
	public static Sintatico sintatico;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String nomeDoArquivo =null;
		String arquivoSaida = null;
		System.out.println("ESTE É O COMPILADOR DESENVOLVIDO POR LEANDRO CORDEIRO DAVID E WILSON FARIA PARA A DISCIPLINA DE COMPILADORES DURANTE A HEELWEEK DE 2009\n\n");
		System.out.println("Entrada: Arquivo com código em LazyK");
		System.out.println("Saída: Arquivo com código em C que executa o programa escrito em LazyK traduzido para a notação lambda");
		System.out.println("Digite o caminho e nome do arquivo que deseja compilar(Ex.: C:\\Compiladores\\origem.txt):");
		nomeDoArquivo = scanner.next();
		System.out.println("Digite o caminho e nome do arquivo de saída(Ex.: C:\\Compiladores\\saida.c):");
		arquivoSaida = scanner.next();
//		nomeDoArquivo = "c:\\testeP2.txt";
		System.out.println("Lendo arquivo:"+nomeDoArquivo);
		nomeDoArquivo = nomeDoArquivo.replace("\\", "\\\\");
		arquivoSaida = arquivoSaida.replace("\\", "\\\\");
	    System.out.println("Lendo arquivo:"+nomeDoArquivo);
//	    testalexico(nomeDoArquivo);
	    List listaDeTokens =lex.obterListaDeTokens(nomeDoArquivo);
		sintatico = new Sintatico(listaDeTokens);
		if (sintatico.executa()){
			System.out.println("\nSintatico finalizado com sucesso!");
		}
		else{
			System.out.println("Sintatico finalizado com ERRO!");
			printErrorList(sintatico.getErros());
		}
		
		Gravar(sintatico.getCodigoFinal(),arquivoSaida);
		System.out.println("Arquivo Gerado !!!! \n\nFui Aprovado?(digite \"sim\")");
		String resposta = scanner.next();
		confirmaAprovação(resposta);
	}
	
	private static void confirmaAprovação(String resposta) {
		while(!resposta.equals("sim") ){
			System.out.println("\n\nFala sério. Não seja cruel. Fui Aprovado?(basta responder \"sim\")");
			resposta = scanner.next();
		}
		System.out.println("UFA!!!!!   Valew \\o/ - Paz e Bem !!!");
	}

	public static void Gravar(String texto,String arquivo){  
			System.out.println("\n\nGerando arquivo de saída:"+ arquivo);
		   String conteudo = texto;  
		   try{  
		      // o true significa q o arquivo será constante  
		      FileWriter x = new FileWriter(arquivo,true);   
		        
		     
		      conteudo += "\n\r"; // criando nova linha e recuo no arquivo           
		      x.write(conteudo); // armazena o texto no objeto x, que aponta para o arquivo           
		      x.close(); // cria o arquivo             
		   }  
		   // em caso de erro apreenta mensagem abaixo  
		   catch(Exception e){  
		       
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
