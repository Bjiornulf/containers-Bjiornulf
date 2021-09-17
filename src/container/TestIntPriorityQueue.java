package container;

public class Test {
    public static void main(String[] args) {
        IntPriorityQueue liste = new IntPriorityQueue(10);
        for (int i = 10; i > 0; i--) {
            liste.insertElement(i);
            System.out.println("after inserting " + i + " liste.element() = " + liste.element());
        }
        System.out.println(liste);

        for (int i = 11; i <= 20; i++) {
            liste.insertElement(i);
            System.out.println("after inserting " + i + " liste.element() = " + liste.element());
        }
        System.out.println(liste);
        for (int i = 0; i < 20; i++) {
            System.out.println(liste.popElement());
            System.out.println(liste);
        }
    }
}
