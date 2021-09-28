package container;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue; // also exist with assertFalse

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;

public class TestIntFIFO  {
    private IntFIFO testList;

    @Before
    public void create_emptyList() {
        testList = new IntFIFO(10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void test_illegalCreation() {
        IntFIFO faultyList = new IntFIFO(0);
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
        for (int i = 0; i < 100; i++) {
            assertTrue(testList.insertElement(i));
            assertEquals(testList.size(), i+1);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(testList.popElement(), (Integer) i);
        }
        assertEquals(testList.size(), 0);
    }

    @After
    public void test_empty() {
        assertEquals(testList.size(), 0);
        assertTrue(testList.isEmpty());
    }
}
