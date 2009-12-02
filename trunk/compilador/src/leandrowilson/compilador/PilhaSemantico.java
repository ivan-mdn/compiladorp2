package leandrowilson.compilador;

import java.util.Stack;

public class PilhaSemantico{
	Stack<Object> pilha = new Stack<Object>();
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	public void push(Token token) {
		pilha.push(token);
	}

	public Token pop_Token() {
		return (Token)pilha.pop();
	}
	
	public Descritor pop_Descritor() {
		return (Descritor)pilha.pop();
	}
	
	public Token peek(){
		return (Token)pilha.peek();
	}
}
