package container;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Implementation of an Integer specific FIFO Queue
 * using circular arrays
 * 
 * @author Todor PEEV
 */
public class IntFIFO implements Queue<Integer>{
	private Integer[] array;
	private int start = 0;
	private int size = 0;

	/**
	 * Creates {@code IntFIFO} with given capacity
	 * 
	 * @param capacity
	 * @throws IllegalArgumentException
	 */
	public IntFIFO(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Given capacity : " + capacity + ". Capacity should be > 0");
		}
		this.array = new Integer[capacity];
	}
	public Iterator<Integer> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<Integer> {
		private int index;
		public Itr() {
			index = 0;
		}
		public boolean hasNext() {
			return index < IntFIFO.this.size;
		}
		public Integer next() {
			return IntFIFO.this.array[(this.index++ + IntFIFO.this.start) % IntFIFO.this.array.length];
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
	 * Tries to insert an element at the end ofthe Queue. If the Queue is full,
	 * it will try to double the size before adding the element.
	 * Returns a boolean indicating if the insersion succeeded or not.
	 * 
	 * @param	i	the integer to add
	 * @return 		boolean indicating the success of the insersion
	 */
	public boolean insertElement(Integer i) {
		// if array full, try to double its size
		if (this.size == this.array.length) {
			this.doubleSize();
			return this.insertElement(i);
		}
		this.array[(this.start + this.size) % this.array.length] = i;
		this.size++;
		return true;
	}

	/**
	 * Tries to double the size of the Queue.
	 * Returns true if successful
	 * 
	 * @return success of the size increase
	 */
	private boolean doubleSize() {
		Integer[] newArray = new Integer[this.array.length * 2];
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
	 * @return highest Integer of the FIFO Queue
	 * @throws NoSuchElementException when Queue is empty
	 */
	public Integer element() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		return this.array[this.start];
	}

	/**
	 * Returns the first element of the Queue
	 * 
	 * @return		first inserted element in the Queue
	 * @throws NoSuchElementException when Queue is empty 
	 */
	public Integer popElement() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		Integer returnValue = this.array[this.start];
		// checking if removing the value emptied the Queue before updating this.start
		this.size--;
		this.start = (this.start + 1) % this.array.length;
		return returnValue;
	}

}
