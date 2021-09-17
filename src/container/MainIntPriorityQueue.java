package container;

import static org.junit.Assert.assertEquals;

public class MainIntPriorityQueue {
    public static void main(String[] arg) {

        IntPriorityQueue queue = new IntPriorityQueue(10);

        System.out.println(queue.isEmpty());

        queue.insertElement(12);
        queue.insertElement(42);
        queue.insertElement(1);

        System.out.println(queue.size() + " expect " +  3);

        System.out.println(queue.element() + " expect " +  42);
        System.out.println(queue.popElement() + " expect " +  42);
        System.out.println(queue.size() + " expect " +  2);

        System.out.println(queue.element() + " expect " +  12);
        System.out.println(queue.popElement() + " expect " +  12);
        System.out.println(queue.size()  + " expect " +  1);

        System.out.println(!queue.isEmpty());

        System.out.println(queue.element() + " expect " +  1);
        System.out.println(queue.popElement() + " expect " +  1);
        System.out.println(queue.size() + " expect " +  0);

        System.out.println(queue.isEmpty());
    }
}
