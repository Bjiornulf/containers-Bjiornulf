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

	public IntFIFO(int capacity) {
		this.array = new Integer[capacity];
	}
	public Iterator<Integer> iterator() {
		return null;
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
			if (this.doubleSize()) {
				return this.insertElement(i);
			}
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
		Integer[] newArray; //declare before try-block (otherwise only visible in block)
		try {
			newArray = new Integer[this.array.length * 2];
		}
		catch (Exception e) {
			return false;
		}
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
		if (this.isEmpty) {
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
		if (this.isEmpty) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		Integer returnValue = this.array[this.start];
		// checking if removing the value emptied the Queue before updating this.start
		this.size--;
		this.start = (this.start + 1) % this.array.length;
		return returnValue;
	}

}
