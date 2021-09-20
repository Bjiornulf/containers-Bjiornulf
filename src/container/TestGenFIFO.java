package container;

public class TestGenFIFO {
    public static void main(String[] args) {
        GenFIFO<Integer> liste = new GenFIFO<Integer>(10);
        for (int i = 0; i < 10; i++) {
            liste.insertElement(i);
        }
        System.out.println(liste.element());
        System.out.println(liste.size());
        System.out.println(liste.insertElement(10));
		System.out.println(liste.size());
        while (!liste.isEmpty()) {
            System.out.println(liste.popElement());
        }        
    }
}
