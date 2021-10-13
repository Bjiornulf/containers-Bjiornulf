package container;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue; // also exist with assertFalse
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;

class MyComparator implements Comparator<Integer> {
	public int compare(Integer i1, Integer i2) {
		return i1.compareTo(i2);
	}
}
 
public class TestGenPriorityQueueCmp  {
    private GenPriorityQueueCmp<Integer> testList;

    @Before
    public void create_emptyList() {
        testList = new GenPriorityQueueCmp<Integer>(10, new MyComparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_illegalCreation() {
        GenPriorityQueueCmp<Integer> faultyList = new GenPriorityQueueCmp<Integer>(0, new MyComparator());
    }
    
    @Test
    public void test_emptyCreation() {
        assertTrue(testList.isEmpty());
        assertEquals(testList.size(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void test_emptyPop() {
        testList.popElement();
    }

    @Test (expected = NoSuchElementException.class)
    public void test_emptyElement() {
        testList.element();
    }

    @Test
    public void test_singleInsertion() {
        assertTrue(testList.insertElement(0));
        assertEquals(testList.size(), 1);
        assertFalse(testList.isEmpty());
        assertEquals(testList.element(), (Integer) 0); // returns Integer not int
        assertEquals(testList.popElement(), (Integer) 0);
        assertEquals(testList.size(), 0);
    }

    @Test
    public void test_bigInsert() {
        List<Integer> testValues = new ArrayList<Integer>(100);
        for (int i = 0; i < 100; i++) {
            testValues.add((Integer) i);
        }
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(testValues);
            for (Integer e : testValues) {
                assertTrue(testList.insertElement(e));
            }
            for (int e = 99; e >= 0 ; e--) {
                assertEquals(testList.popElement(), (Integer) e);
                assertEquals(testList.size(), e);
            }
        }  
    }

	@Test(expected=NoSuchElementException.class)
	public void test_iteratorException() {
		testList.iterator().next();
	}

	@Test
	public void test_iterator() {
		boolean[] seen = new boolean[10];
		for (int i = 0; i < 10; i++) {
			testList.insertElement((Integer) i);
			seen[i] = false;
		}
		Iterator<Integer> iter = testList.iterator();
		while (iter.hasNext()) {
			seen[iter.next()] = true;
		}
		while (!testList.isEmpty()) {
			assertTrue(seen[testList.popElement()]);
		}
	}

    @After
    public void test_empty() {
        assertEquals(testList.size(), 0);
        assertTrue(testList.isEmpty());
    }
}
