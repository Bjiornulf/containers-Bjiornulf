package container;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Implementation of a FIFO Queue
 * using circular arrays
 * 
 * @author Todor PEEV
 * @param E type of the stored elements
 */
public class GenFIFO<E> implements Queue<E>{
	private E[] array;
	private int start = 0;
	private int size = 0;

	/**
	 * Creates FIFO Queue with given capacity
	 * 
	 * @param capacity
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public GenFIFO(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Provided capacity: " + capacity + ". Capacity should be > 0");
		}
		this.array = (E[]) new Object[capacity];
	}

	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {
		private int index = 0;
		public boolean hasNext() {
			return index < GenFIFO.this.size;
		}
		public E next() {
			return GenFIFO.this.array[(this.index++ + GenFIFO.this.start) % GenFIFO.this.array.length];
		}
	}

	/**
	 * Returns if the Queue is empty of not
	 * 
	 * @return is the Queue empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns the number of elements in the Queue
	 * 
	 * @return number of elements in the Queue
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Tries to insert an element at the end of the Queue. If the Queue is full,
	 * it will try to double the size before adding the element.
	 * Returns a boolean indicating if the insersion succeeded or not.
	 * 
	 * @param	e	the element to add
	 * @return 		boolean indicating the success of the insersion
	 */
	public boolean insertElement(E e) {
		// if array full, try to double its size
		if (this.size == this.array.length) {
			this.doubleSize();
			return this.insertElement(e);
		}
		this.array[(this.start + this.size) % this.array.length] = e;
		this.size++;
		return true;
	}

	/**
	 * Tries to double the size of the Queue.
	 * Returns true if successful
	 * 
	 * @return success of the size increase
	 */
	@SuppressWarnings("unchecked")
	private boolean doubleSize() {
		E[] newArray;
		newArray = (E[]) new Object[this.array.length * 2];
		for (int i = 0; i < this.size; i++) {
			newArray[i] = this.array[(i + this.start) % this.array.length];
		}
		this.start = 0;
		this.array = newArray;
		return true;
	}

	/**
	 * Returns the first element of the Queue without removing it
	 * 
	 * @return first element of the FIFO Queue
	 * @throws NoSuchElementException when Queue is empty
	 */
	public E element() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		return this.array[this.start];
	}

	/**
	 * Returns the first element of the Queue and removes it form the Queue
	 * 
	 * @return		first element in the Queue
	 * @throws NoSuchElementException when Queue is empty 
	 */
	public E popElement() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		E returnValue = this.array[this.start];
		// checking if removing the value emptied the Queue before updating this.start
		this.size--;
		this.start = (this.start + 1) % this.array.length;
		return returnValue;
	}

}
