package leandrowilson.compilador;


public class List {
	public Object elementos[];
	public int tamanho;
	
	List(){
		this.elementos = new Object[0];
		this.tamanho = this.elementos.length;
	}
	
	public void add(Object obj){
		aumentaTamanho();
		this.elementos[this.tamanho-1]=obj;
	}
	
	public Object get(int index){
		return elementos[index];
	}
	
	public void set(int index,Object obj){
		elementos[index]= obj;
	}
	
	public void remove(int index){
		for (int i = index;i<tamanho-1;i++){
			elementos[i]=elementos[i+1];
		}
		elementos[tamanho-1]=null;
		tamanho--;
	}
	
	public void remove(Object obj){
		for (int i = 0;i<tamanho;i++){
			if (elementos[i].equals(obj)){
				remove(i);
			}
		}
	}

	private void aumentaTamanho() {
		Object oldElements[] = this.elementos;
		Object newElements[] = new Object[this.tamanho+1];
		for (int i =0;i<oldElements.length;i++){
			newElements[i]=oldElements[i];
		}
		this.elementos=newElements;
		this.tamanho = this.elementos.length;
		
	}

	public Integer[] toIntArray() {
		Integer[] intVector = new Integer[this.tamanho];
		for (int i =0;i<tamanho;i++){
			intVector[i] = (Integer)this.get(i);
		}
		return intVector;
	}
	
	
}
