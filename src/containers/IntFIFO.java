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
	private int end = -1;
	private boolean isEmpty = true;
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
		return this.isEmpty;
	}

	/**
	 * Returns the size of the Queue
	 * 
	 * @return size of the Queue
	 */
	public int size() {
		if (this.isEmpty) {
			return 0;
		}
		return (this.end - this.start) % this.array.length + 1;
	}

	/**
	 * This method allows the insertion of an Integer in the FIFO Queue.
	 * Returns a boolean indication if the insersion succeeded or not.
	 * 
	 * @param	i	the integer to add
	 * @return 		boolean indicating the success of the insersion
	 */
	public boolean insertElement(Integer i) {
		if (!this.isEmpty && (this.end - this.start + 1) % this.array.length == 0) {
			return false;
		}
		this.end = (this.end + 1) % this.array.length;
		this.array[this.end] = i;
		this.isEmpty = false;
		return true;
	}

	/**
	 * Returns the hightest element of the Queue without removing it
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
	 * Returns the first inserted element in the Queue
	 * 
	 * @return		first inserted element in the Queue
	 * @throws NoSuchElementException when Queue is empty 
	 */
	public Integer popElement() {
		if (this.isEmpty) {
			throw new NoSuchElementException("IntFIFO empty");
		}
		Integer returnValue = this.array[this.start];
		this.start = (this.start + 1) % this.array.length;
		if (this.start == this.end) {
			this.isEmpty = true;
		}
		return returnValue;
	}

}
