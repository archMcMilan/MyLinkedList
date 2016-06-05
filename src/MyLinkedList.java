import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by Artem on 03.06.2016.
 */
public class MyLinkedList<T> extends AbstractQueue<T> implements Queue<T> {
    private Node first;
    private Node last;
    private int size;
    private int modCount;

    private class Node<T>{
        T data;
        Node next;
        Node prev;

        public Node(){

        }
        public Node(T data){
            this.data=data;
        }
    }


    public void addFirst(T value){
        if(size==0){
            first=new Node();
            first.data=value;
            last=first;
            size++;
        }
        else{
            Node temp=new Node();
            temp.data=value;
            temp.next=first;
            first.prev=temp;
            first=temp;
            size++;
        }
    }

    public void addLast(T value){
        if(size==0){
            first=new Node();
            first.data=value;
            last=first;
            size++;
        }
        else{
            Node temp=new Node();
            temp.data=value;
            temp.prev=last;
            last.next=temp;
            last=temp;
            size++;
        }
    }

    public void add(int index,T value){
        if (size==0 || index==0) {
            addFirst(value);
        }else if(index>size() || index<0){
            throw new IndexOutOfBoundsException("index out of bounce");
        }else if (index==this.size()){
            Node newElem=new Node();
            newElem.data=value;
            last.next=newElem;
            newElem.prev=last;
            last=newElem;
            size++;
        }else{
            Node newElem=new Node();
            newElem.data=value;
            Node temp=get(index);

            newElem.prev=temp.prev;
            newElem.next=temp;
            temp.prev=newElem;
            newElem.prev.next=newElem;
            size++;
        }
    }

    public T removeFirst(){
        if(last==null){
            return null;
        }else if(first==last){
            Node temp=first;
            first=null;
            last=null;
            return (T) temp.data;
        }else{
            Node temp=first;
            first=first.next;
            first.prev=null;
            return (T) temp.data;
        }
    }

    public T removeLast(){
        if(first==null){
            return null;
        }else if(first==last){
            Node temp=last;
            first=null;
            last=null;
            return (T) temp.data;
        }else{
            Node temp=last;
            last=last.prev;
            last.next=null;
            return (T) temp.data;
        }
    }

    public T remove()

    public Node<T> get(int index){
        if(index>size() || index<0) {
            throw new IndexOutOfBoundsException("index out of bounce");
        }else{
            if(index<(size>>1)){
                int count=0;
                Node temp=first;
                while(count!=index){
                    temp=temp.next;
                    count++;
                }
                return temp;
            }else{
                int count=size-1;
                Node temp=last;
                while(count!=index){
                    temp=temp.prev;
                    count--;
                }
                return temp;
            }

        }

    }


    public int size(){
        return size;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param o the element to add
     * @return {@code true} if the element was added to this queue, else
     * {@code false}
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    @Override
    public boolean offer(Object o) {
        return false;
    }



    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public T poll() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public T peek() {
        return null;
    }



    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator iterator() {
        return null;
    }


}
