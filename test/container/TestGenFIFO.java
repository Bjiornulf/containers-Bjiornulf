package container;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue; // also exist with assertFalse
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;

public class TestGenFIFO  {
    private GenFIFO<Integer> testList;

    @Before
    public void create_emptyList() {
        testList = new GenFIFO<Integer>(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_invalidCreation() {
        GenFIFO<Integer> faultyList = new GenFIFO<Integer>(0);
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
        assertTrue(testList.isEmpty());
    }

    @Test
    public void test_bigInsert() {
        for (int i = 0; i < 100; i++) {
            assertTrue(testList.insertElement((Integer) i));
            assertEquals(testList.size(), i+1);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(testList.popElement(), (Integer) i);
        }
    }

	@Test(expected=NoSuchElementException.class)
	public void test_iteratorException() {
		testList.iterator().next();
	}

	@Test
	public void test_iterator() {
		boolean[] seen = new boolean[10];
		for (int i = 0; i < 10; i ++) {
			testList.insertElement((Integer) i);
			seen[i] = false;
		}
		Iterator<Integer> iter = testList.iterator();
		while (iter.hasNext()) {
			seen[iter.next()] = true;
		}
		for (int i = 0; i < 10; i++) {
			assertTrue(seen[testList.popElement()]);
		}
	}

    @After
    public void test_empty() {
        assertEquals(testList.size(), 0);
        assertTrue(testList.isEmpty());
    }
}
