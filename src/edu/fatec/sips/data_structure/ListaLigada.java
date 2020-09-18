package edu.fatec.sips.data_structure;

import edu.fatec.sips.data_structure.node.Node;

public class ListaLigada<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public ListaLigada() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void add(Node<T> node) {
		if (isEmpty()) {
			insertFirst(node);
		} else {
			node.setNext(null);
			tail.setNext(node);
			tail = node;
			this.size++;
		}
	}

	public void insertFirst(Node<T> node) {
		node.setNext(head);
		head = node;
		size++;
		if (size == 1) {
			tail = head;
		}
	}

	public void insertAt(Node<T> node, int index) {
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

	public Node<T> removeFirst() {
		Node<T> node = head;
		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		} else if (this.size == 1) {
			this.head = this.tail = null;
			this.size--;
		} else {
			this.head = this.head.getNext();
			this.size--;
		}

		return node;
	}

	public Node<T> removeLast() {
		Node<T> node = this.tail;

		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		} else if (size == 1) {
			removeFirst();
		} else {
			Node<T> current = this.head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(null);
			this.tail = current;
			this.size--;
		}

		return node;
	}

	public Node<T> remove(int index) {
		Node<T> node = null;

		if (isEmpty()) {
			System.err.println("EMPTY LIST");
		}
		if (index < 0 || index >= size) {
			System.err.println("INVALID POSITION");
		} else {
			if (index == 0) {
				node = removeFirst();
			} else if (index == this.size) {
				node = removeLast();
			} else {
				int currentIndex = 0;
				Node<T> current = this.head;
				while (currentIndex + 1 < index) {
					current = current.getNext();
					currentIndex++;
				}
				node = current;
				current.setNext(node.getNext().getNext());
				this.size--;
			}
		}

		return node;
	}

	public Node<T> peek(int index) {
		Node<T> node = null;

		if (isEmpty()) {
			System.err.println("EMPTY LIST");
			return null;
		}

		if (index < 0 || index >= size) {
			System.err.println("INVALID POSITION");
		} else {
			node = head;
			int currentIndex = 0;
			while (currentIndex < index) {
				node = node.getNext();
				currentIndex++;
			}
		}

		return node;
	}
}
