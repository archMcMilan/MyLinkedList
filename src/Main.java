/**
 * Created by Artem on 05.06.16.
 */
public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(100);
        linkedList.add(200);
        linkedList.add(300);
        linkedList.add(400);
        linkedList.add(500);
        linkedList.add(600);
        linkedList.print();
        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());
        linkedList.add(2, -50);
        linkedList.print();
        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        linkedList.remove(600);
        linkedList.print();
        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());

        System.out.println("Element #3 = " + linkedList.get(3));
        linkedList.set(3, -10000);
        System.out.println("Element #3 = " + linkedList.get(3));
        linkedList.print();
        linkedList.addFirst(-1);
        linkedList.print();
        System.out.println("First = " + linkedList.getFirst() + "; Last = " + linkedList.getLast() +
                "; Size = " + linkedList.size());
                
        System.out.println("Element #1 = " + linkedList.get(1));
        System.out.println("Element #4 = " + linkedList.get(4));
        linkedList.removeLast();
        linkedList.print();


        for (Integer i : linkedList) {
            System.out.print(i + " ");
        }

    }
}
