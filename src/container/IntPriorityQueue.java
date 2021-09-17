package container;

import java.util.Currency;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Implements Integer specific priority Queue
 * 
 * @author Todor PEEV
 */
public class IntPriorityQueue implements Queue<Integer> {

    private Integer[] array;
    private int size;

    public IntPriorityQueue(int capacity) {
        this.array = new Integer[capacity];
        this.size = 0;
    }

    private void doubleSize() {
        Integer[] newArray = new Integer[this.size*2];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    public String toString() {
        String toReturn = "";
        for (int i = 0; i < this.size; i++) {
            toReturn += this.array[i] + ":";
        }
        return toReturn;
    }

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

    @Override
    public Integer element() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue empty");
        }
        return this.array[0];
    }

    @Override
    public Integer popElement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
