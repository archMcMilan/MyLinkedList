import java.util.*;

/**
 * Created by Artem on 03.06.2016.
 */
public class MyLinkedList<T> extends AbstractQueue<T> implements Queue<T> {
    private Node first;
    private Node last;
    private int size;
    private int modCount;

    private class Node<T> {
        T data;
        Node next;
        Node prev;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }
    }


    public void addFirst(T value) {
        if (size == 0) {
            first = new Node();
            first.data = value;
            last = first;
            size++;
        } else {
            Node temp = new Node();
            temp.data = value;
            temp.next = first;
            first.prev = temp;
            first = temp;
            size++;
        }
        modCount++;
    }

    public void addLast(T value) {
        if (size == 0) {
            first = new Node();
            first.data = value;
            last = first;
            size++;
        } else {
            Node temp = new Node();
            temp.data = value;
            temp.prev = last;
            last.next = temp;
            last = temp;
            size++;
        }
        modCount++;
    }

    public boolean add(T element) {
        modCount++;
        if (size()==0) {
            first = new Node(element);
            last = first;
            size++;
            return true;
        }
        Node temp = first;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(element);
        last = temp.next;
        last.prev = temp;
        size++;
        return true;
    }

    public void add(int index, T value) {
        if (size == 0 || index == 0) {
            addFirst(value);
        } else if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("index out of bounce");
        } else if (index == this.size()) {
            addLast(value);
        } else {
            modCount++;
            Node newElem = new Node();
            newElem.data = value;
            Node temp = getNode(index);

            newElem.prev = temp.prev;
            newElem.next = temp;
            temp.prev = newElem;
            newElem.prev.next = newElem;
            size++;
        }
    }

    public T removeFirst() {
        if (size()==0) {
            throw new UnsupportedOperationException("The list is empty");
        }else if(first == last){
            size--;
            Node temp = first;
            first = null;
            last = null;
            return (T) temp.data;
        } else {
            modCount++;
            Node tempfirst = first;
            first = first.next;
            first.prev = null;
            size--;
            return (T) tempfirst.data;
        }
    }

    public T removeLast() {
        if (size()==0 || first==last) {
            return removeFirst();
        } else {
            modCount++;
            Node tempLast = last;
            last = last.prev;
            last.next = null;
            size--;
            return (T) tempLast.data;
        }
    }

    public boolean contains(Object element) {
        Node temp = first;
        while (temp != null) {
            if (temp.data.equals((T)element)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    public T get(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("index out of bounce");
        } else {
            Node temp = getNode(index);
            return (T) temp.data;
        }
    }

    private Node<T> getNode(int index) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("index out of bounce");
        } else {
            if (index < (size >> 1)) {
                int count = 0;
                Node temp = first;
                while (count != index) {
                    temp = temp.next;
                    count++;
                }
                return temp;
            } else {
                int count = size - 1;
                Node temp = last;
                while (count != index) {
                    temp = temp.prev;
                    count--;
                }
                return temp;
            }

        }

    }

    public T getFirst() {
        if (size != 0) {
            return (T) first.data;
        } else {
            return null;
        }

    }

    public T getLast() {
        if (size != 0) {
            return (T) last.data;
        } else {
            return null;
        }
    }

    public void set(int index, T value) {
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException("index out of bounce");
        }else{
            modCount++;
            Node temp = getNode(index);
            temp.data = value;
        }

    }


    public int size() {
        return size;
    }

    public void print() {
        Node temp = first;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public ListIterator<T> iterator() {
        return new ListIterator<T>() {
            int modCount = 0;
            Node<T> current = first;
            int currentIndex = 0;
            Node<T> justReturned = null;

            @Override
            public boolean hasNext() {
                return  (current != null && current.next != null);
            }

            @Override
            public T next() {
                justReturned = current;
                current = current.next;
                currentIndex++;

                return (T) justReturned.data;
            }

            @Override
            public boolean hasPrevious() {
                return (current != null && current.prev != null);
            }

            @Override
            public T previous() {
                justReturned = current;
                current = current.prev;
                currentIndex--;
                return (T) justReturned.data;
            }

            @Override
            public int nextIndex() {
                return currentIndex++;
            }

            @Override
            public int previousIndex() {
                return currentIndex--;
            }

            @Override
            public void remove() {
                checkMods();
                modCount++;
                if (justReturned == null) {
                    throw new IndexOutOfBoundsException("Just Returned is null");
                }
                if (justReturned == first) {
                    MyLinkedList.this.removeFirst();
                } else if (justReturned == last) {
                    MyLinkedList.this.removeLast();
                } else {
                    justReturned.next.prev = justReturned.prev;
                    justReturned.prev.next = justReturned.next;
                    justReturned = null;
                }
            }

            @Override
            public void set(T t) {
                checkMods();
                modCount++;
                justReturned.data = t;
            }

            @Override
            public void add(T t) {
                checkMods();
                modCount++;
                if (justReturned == first) {
                    MyLinkedList.this.addFirst(t);
                } else if (justReturned == last) {
                    MyLinkedList.this.add(t);
                } else {
                    Node node = new Node(t);
                    justReturned.next.prev = node;
                    node.next = justReturned.next;
                    justReturned.next = node;
                    node.prev = justReturned;
                }
            }

            private void checkMods() {
                if (modCount != MyLinkedList.this.modCount) throw new ConcurrentModificationException();
            }
        };
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



}
