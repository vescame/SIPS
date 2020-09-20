package edu.fatec.sips.data_structure;

public class ListaLigadaSimples<T> {
	private Node<T> head;
	private int size;

	public ListaLigadaSimples() {
		this.head = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void insertFirst(T element) {
		final Node<T> node = new Node<T>(element);
		node.setNext(head);
		head = node;
		size++;
	}

	public void add(T element) {
		final Node<T> node = new Node<T>(element);
		if (isEmpty()) {
			insertFirst(element);
		} else {
			Node<T> current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(node);
			this.size++;
		}
	}

	public void insertAt(T element, int index) {
		final Node<T> node = new Node<T>(element);
		if (index < 0 || index >= size) {
			System.err.println("INVALID POSITION");
		} else {
			Node<T> current = head;
			Node<T> previous = null;
			int currentIndex = 0;
			while (currentIndex < index) {
				previous = current;
				current = current.getNext();
				currentIndex++;
			}
			node.setNext(current);
			previous.setNext(node);
			this.size++;
		}
	}

	public T removeFirst() {
		Node<T> node = head;
		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		} else if (this.size == 1) {
			this.head = null;
			this.size--;
		} else {
			this.head = this.head.getNext();
			this.size--;
		}

		return node.getElement();
	}

	public T removeLast() {
		Node<T> node = null;

		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		} else if (size == 1) {
			removeFirst();
		} else {
			Node<T> current = this.head;
			while (current.getNext().getNext() != null) {
				current = current.getNext();
			}
			node = current.getNext();
			current.setNext(null);
			this.size--;
		}

		return node.getElement();
	}

	public T remove(int index) {
		T element = null;
		
		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		} else if (index < 0 || index >= size) {
			System.err.println("INVALID POSITION");
		} else {
			if (index == 0) {
				element = removeFirst();
			} else if (index == this.size) {
				element = removeLast();
			} else {
				int currentIndex = 0;
				Node<T> current = this.head;
				while (currentIndex + 1 < index) {
					current = current.getNext();
					currentIndex++;
				}
				element = current.getNext().getElement();
				current.setNext(current.getNext().getNext());
				this.size--;
			}
		}
		
		return element;
	}

	public T peek(int index) {
		T element = null;

		if (isEmpty()) {
			System.err.println("EMPTY LIST");
			return null;
		} else if (index < 0 || index >= size) {
			System.err.println("INVALID POSITION");
		} else {
			Node<T> node = head;
			int currentIndex = 0;
			while (currentIndex < index) {
				node = node.getNext();
				currentIndex++;
			}
		}

		return element;
	}
	
	public void print() {
		Node<T> current = this.head;
		while (current != null) {
			System.out.println(current.getElement());
			current = current.getNext();
		}
	}
}

//--> Como combinamos os métodos devem ser em português, para manter o padrão que haviamos comentado na semana passada.
