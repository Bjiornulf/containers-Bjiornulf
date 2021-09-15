package container;

public class Test {
    public static void main(String[] args) {
        IntFIFO liste = new IntFIFO(10);
        for (int i = 0; i < 10; i++) {
            liste.insertElement(i);
        }
        System.out.println(liste.element());
        System.out.println(liste.size());
        System.out.println(liste.insertElement(10));
        System.out.println("Hello World");
    }
}
