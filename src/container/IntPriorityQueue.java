package container;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements Integer specific priority Queue using heap-array
 * 
 * @author Todor PEEV
 */
public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] array;
    private int size;

    /**
     * Creates {@code IntPriorityQueue} with given capacity
     * 
     * @param capacity initial capacity for the queue. Should be > 0
     * @throws IllegalArgumentException
     */
    public IntPriorityQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Given capacity : " + capacity + ". Capacity should be > 0");
        }
        this.array = new Integer[capacity];
        this.size = 0;
    }

    /**
     * Doubles the size of the array holding the Queue.
     */
    private void doubleSize() {
        Integer[] newArray = new Integer[this.size*2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Integer> {
        private int index = 0;
        public boolean hasNext() {
            return index < IntPriorityQueue.this.size;
        }
        public Integer next() {
            return IntPriorityQueue.this.array[index++];
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
     * Inserts an Integer in the Queue. If the insertions succeeds, return true;
     * 
     * @param e Integer to insert
     * @return  Success of insertion
     */
    @Override
    public boolean insertElement(Integer e) {
        if (this.size == this.array.length){
            this.doubleSize();
        }
        this.array[this.size++] = e;
        int currentIndex = this.size - 1;
        int ancestorIndex = (currentIndex + 1) / 2 - 1;
        Integer temp;
        while (currentIndex > 0 && this.array[currentIndex] > this.array[ancestorIndex]) {
            temp = this.array[ancestorIndex];
            this.array[ancestorIndex] = this.array[currentIndex];
            this.array[currentIndex] = temp;

            currentIndex = ancestorIndex;
            ancestorIndex = (currentIndex + 1) / 2 - 1;
        }
        return true;
    }

    /**
     * Returns (without removing) the Integer with highest priority (i.e. the biggest Integer of the Queue)
     * 
     * @return The biggest Integer of the Queue
     */
    @Override
    public Integer element() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue empty");
        }
        return this.array[0];
    }

    /**
     * Return and removes the Integer with highest priority (i.e. the biggest Integer of the Queue)
     * 
     * @return The biggest integer of the Queue
     */
    @Override
    public Integer popElement() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue empty");
        }
        Integer temp;
        temp = this.array[0];
        this.array[0] = this.array[this.size - 1];
        this.array[this.size - 1] = temp;
        this.size--; // we removed an element though it is still held in the array
        int currentIndex = 0;
        int childIndex1 = 2 * currentIndex + 1;
        while (childIndex1 < this.size) {
            if (childIndex1 < this.size - 1) {
                childIndex1 = this.array[childIndex1] > this.array[childIndex1 + 1] ? childIndex1 : childIndex1 + 1;
            }
            if (this.array[currentIndex] > this.array[childIndex1]) {
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
