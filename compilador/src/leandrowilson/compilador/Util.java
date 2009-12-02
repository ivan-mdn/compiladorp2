package leandrowilson.compilador;

import java.util.Scanner;

public   class Util {
	public static Boolean log = true;
	
	public static void main(String[] args) {
		String strTransicoes = "initial:0%"+
		"final:7%"+
		"(0,\"int\")->1%"+
		"(0,\"float\")->1%"+
		"(0,\"string\")->1%"+
		"(0,\"boolean\")->1%"+
		"(0,\"declare\")->2%"+
		"(0,\"struct\")->3%"+
		"(1,\"[\")->4%"+
		"(1,\"identificador\")->5%"+
		"(2,\"identificador\")->1%"+
		"(3,\"identificador\")->6%"+
		"(4,\"inteiro\")->9%"+
		"(4,\"identificador\")->9%"+
		"(5,\";\")->7%"+
		"(6,\"{\")->8%"+
		"(8,declaracao)->8%"+
		"(8,\"}\")->7%"+
		"(9,\"]\")->1" ;
		obterTransicoes( strTransicoes);
		obterEstadosFinais(strTransicoes);
		obterEstadoInicial(strTransicoes);
		
	}
	public static void Log(String mensagem){
		if (log){
			System.out.println(mensagem);
		}
	}
	public static Integer obterEstadoInicial(String strTransicoes){
		String[] linhas = strTransicoes.split("%");
		Integer inicial =new Integer(linhas[0].split("initial:")[1]);
		Log("Estado Inicial obtido:"+inicial.toString());
		return inicial;
	}
	public static Integer[] obterEstadosFinais(String strTransicoes){
		String[] linhas = strTransicoes.split("%");
		String[] finais = linhas[1].split("final:")[1].split(",");
		Integer[] est_final =new Integer[finais.length];
		for(int i =0;i<finais.length;i++){
			est_final[i] = new Integer(finais[i]);
		}
		if (log){
			Log("Estados Finais obtidos:");
			StringBuffer str = new StringBuffer("{");
			for(int i =0;i<est_final.length;i++){
				str.append(est_final[i].toString()+",");
			}
			str.append("}");
			Log(str.toString());
		}
		
		return est_final;
	}
	
	/**
	 * String tem que estar formatada da seguinte forma: <br/>
	 * simbolo % no final de cada linha <br/>
	 * sem espaços em branco <br/>
	 * aspas duplas " corrigidas com caracter de escapa \" <br/>
	 * Exemplo:<br/>
	 * String strTransicoes = "initial:0%"+<br/>
		"final:7%"+<br/>
		"(0,\"int\")->1%"+<br/>
		"(0,\"float\")->1%"+<br/>
		"(0,\"string\")->1%"+<br/>
		"(0,\"boolean\")->1%"+<br/>
		"(0,\"declare\")->2%"+<br/>
		"(0,\"struct\")->3%"+<br/>
		"(1,\"[\")->4%"+<br/>
		"(1,\"identificador\")->5%"+<br/>
		"(2,\"identificador\")->1%"+<br/>
		"(3,\"identificador\")->6%"+<br/>
		"(4,\"inteiro\")->9%"+<br/>
		"(4,\"identificador\")->9%"+<br/>
		"(5,\";\")->7%"+<br/>
		"(6,\"{\")->8%"+<br/>
		"(8,declaracao)->8%"+<br/>
		"(8,\"}\")->7%"+<br/>
		"(9,\"]\")->1" ;<br/>
	 * @param strTransicoes
	 * @return
	 */
	public static List obterTransicoes(String strTransicoes){
		List listaDeTransicoes = new List();
		
		String[]lados =null;
		String[] ladoEsquerdo =null;
		String estadoInicial =null;
		String entrada =null;
		String proximoEstado=null;
		String[] linhas = strTransicoes.split("%");
		for (int i =2;i<linhas.length;i++){
			Log(linhas[i]);
			lados = linhas[i].split("->");
			proximoEstado = lados[1];
			Log(lados[0]);
			Log(proximoEstado);
			//lados[0]=lados[0].replaceFirst("\\(", "");
			lados[0]=lados[0].substring(1, lados[0].length()-1);
			Log(lados[0]);
			lados[0] = lados[0].replaceFirst(",", "%");
			ladoEsquerdo = lados[0].split( "%");
			estadoInicial = ladoEsquerdo[0];
			entrada = ladoEsquerdo[1];
			Log(estadoInicial);
			Log(entrada);
			Log("Transicao Lida - estado inicial:"+estadoInicial+" entrada:"+entrada+" proximo estado:"+proximoEstado);
			if (TipoMaquina.ehMaquina(entrada)){
				listaDeTransicoes.add(new TransicaoSintatica(true,estadoInicial,entrada,proximoEstado,i-2));
			}
			else{
				listaDeTransicoes.add(new TransicaoSintatica(false, estadoInicial, entrada.replace("\"",""), proximoEstado,i-2));
			}
		}
		if(log){
			Log("Transicoes reconhecidas:");
			TransicaoSintatica t = null;
			for (int i = 0;i<listaDeTransicoes.tamanho;i++){
				t = (TransicaoSintatica) listaDeTransicoes.get(i);
				Log("Tipo:"+((t.transicaoParaMaquina)?"Maquina ":"Terminal") +"  Estado Inicial:"+t.estadoInicial.toString()+" Entrada:" +((t.transicaoParaMaquina)?t.maquina.toString():t.token.toString()) +"  Proximo Estado:"+t.proximoEstado.toString());
			}
		}
		
		return listaDeTransicoes;
		
	}
	
}
