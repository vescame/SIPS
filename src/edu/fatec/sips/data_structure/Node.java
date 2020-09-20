package edu.fatec.sips.data_structure;

public class Node<T> {
	private Node<T> next;
	private T element;

	public Node(T documento) {
		this.element = documento;
		this.next = null;
	}

	public Node(T element, Node<T> node) {
		this.element = element;
		next = node;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> element) {
		this.next = element;
	}
}

//--> Se n�o forem criados, outras classes Node, talvez n�o seja necess�rio um pacote para o Node, ele pode ser armazenado junto com o pacote das estruturas.

//--> Como combinamos os m�todos devem ser em portugu�s, para manter o padr�o que haviamos comentado na semana passada.