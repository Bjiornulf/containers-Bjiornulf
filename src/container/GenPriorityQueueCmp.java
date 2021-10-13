package container;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * Implements priority Queue with a user-defined Comparator using heap-array
 * 
 * @author Todor PEEV
 */
public class GenPriorityQueueCmp<E> implements Queue<E> {

    private E[] array;
    private int size;
	private Comparator<? super E> cmp;

    /**
     * Creates the priority queue with given capacity
     * 
     * @param capacity the initial capacity of the priority queue
	 * @param cmp user defined Comparator
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public GenPriorityQueueCmp(int capacity, Comparator<? super E> cmp) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Provided capacity : " + capacity + ". Capacity should be > 0");
        }
        this.array = (E[]) new Object[capacity];
        this.size = 0;
		this.cmp = cmp;
    }

    /**
     * Doubles the size of the array holding the Queue.
     */
    @SuppressWarnings("unchecked")
    private void doubleSize() {
        E[] newArray = (E[]) new Object[this.size*2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        private int index = 0;
        public boolean hasNext() {
            return index < GenPriorityQueueCmp.this.size;
        }
        public E next() {
			if (this.index >= GenPriorityQueueCmp.this.size) {
				throw new NoSuchElementException("No more elements to iterate on. Consider checking with hasNext()");
			}
            return GenPriorityQueueCmp.this.array[this.index++];
        }
    }

    public String toString() {
        String toReturn = "";
        for (int i = 0; i < this.size; i++) {
            toReturn += this.array[i] + ":";
        }
        return toReturn;
    }

    /**
     * Inserts an element in the Queue. If the insertions succeeds, return true;
     * 
     * @param e Element to insert
     * @return  Success of insertion
     */
    @Override
    public boolean insertElement(E e) {
        if (this.size == this.array.length){
            this.doubleSize();
        }
        this.array[this.size++] = e;
        int currentIndex = this.size - 1;
        int ancestorIndex = (currentIndex + 1) / 2 - 1;
        E temp;
        while (currentIndex > 0 && this.cmp.compare(this.array[currentIndex],this.array[ancestorIndex]) > 0) {
            temp = this.array[ancestorIndex];
            this.array[ancestorIndex] = this.array[currentIndex];
            this.array[currentIndex] = temp;

            currentIndex = ancestorIndex;
            ancestorIndex = (currentIndex + 1) / 2 - 1;
        }
        return true;
    }

    /**
     * Returns (without removing) the element with highest priority (i.e. the biggest element of the Queue)
     * 
     * @return The biggest element of the Queue
     */
    @Override
    public E element() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue empty");
        }
        return this.array[0];
    }

    /**
     * Return and removes the element with highest priority (i.e. the biggest element of the Queue)
     * 
     * @return The biggest integer of the Queue
     */
    @Override
    public E popElement() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue empty");
        }
        E temp;
        temp = this.array[0];
        this.array[0] = this.array[this.size - 1];
        this.array[this.size - 1] = temp;
        this.size--; // we removed an element though it is still held in the array
        int currentIndex = 0;
        int childIndex1 = 2 * currentIndex + 1;
        while (childIndex1 < this.size) {
            if (childIndex1 < this.size - 1) {
                childIndex1 = this.cmp.compare(this.array[childIndex1], this.array[childIndex1+1]) > 0 ? childIndex1 : childIndex1 + 1;
            }
            if (this.cmp.compare(this.array[currentIndex], this.array[childIndex1]) > 0) {
                break;
            }
            temp = this.array[currentIndex];
            this.array[currentIndex] = this.array[childIndex1];
            this.array[childIndex1] = temp;
            currentIndex = childIndex1;
            childIndex1 = 2 * currentIndex + 1;
        }
        return this.array[this.size];
    }

    /**
     * Return if Queue is empty
     * @return is Queue empty
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size (number of elements) of the Queue
     * 
     * @return size of the Queue
     */
    @Override
    public int size() {
        return this.size;
    }
    
}
