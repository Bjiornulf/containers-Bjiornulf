package container;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue; // also exist with assertFalse

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;

public class TestIntPriorityQueue  {
    private IntPriorityQueue testList;

    @Before
    public void create_emptyList() {
        testList = new IntPriorityQueue(10);
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

    @After
    public void test_empty() {
        assertEquals(testList.size(), 0);
        assertTrue(testList.isEmpty());
    }
}