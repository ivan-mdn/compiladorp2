package leandrowilson.compilador;

import java.util.Stack;

public class PilhaSintatico{
	Stack<ElementoSintatico> pilha = new Stack<ElementoSintatico>();
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	public void push(Integer proximoEstado, TipoMaquina maquinaAtual) {
		pilha.push(new ElementoSintatico(proximoEstado,maquinaAtual));
	}

	public ElementoSintatico pop() {
		return pilha.pop();
	}
	
	public ElementoSintatico peek(){
		return pilha.peek();
	}

}
