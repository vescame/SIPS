package edu.fatec.sips.data_structure;

public class PilhaLigada<T> {
	private Node<T> top;
	private int size;
	
	public PilhaLigada() {
		top = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public final int getSize() {
		return size;
	}

	public final T peek() {
		T element = null;
		if (isEmpty()) {
			System.out.println("EMPTY LIST");
		} else {
			element = top.getElement();
		}
		
		return element;
	}
	
	public void push(final T element) {
		final Node<T> node = new Node<T>(element);
		if (isEmpty()) {
			top = node;
		} else {
			node.setNext(top);
			top = node;
		}
		size++;
	}
	
	public final T pop() {
		T element = null;
		if (isEmpty()) {
			System.out.println("EMPTY STACK");
		} else {
			element = top.getElement();
			top = top.getNext();
			size--;
		}
		
		return element;
	}
}

//--> Como combinamos os métodos devem ser em português, para manter o padrão que haviamos comentado na semana passada.
