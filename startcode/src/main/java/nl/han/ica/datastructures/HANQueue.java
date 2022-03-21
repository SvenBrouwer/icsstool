package nl.han.ica.datastructures;

public class HANQueue<T> implements  IHANQueue<T>{

    ListNode<T> front;
    ListNode<T> back;

    @Override
    public void clear() {
        front = null;
        back = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void enqueue(T value) {
        if( isEmpty() )
            back = front = new ListNode<>(value, null);
        else
            back = back.next = new ListNode<>(value, null);
    }

    @Override
    public T dequeue() {
        if( isEmpty() ){
            throw new RuntimeException();
        } else {
            T element = front.element;
            front = front.next;
            return element;
        }
    }

    @Override
    public T peek() {
        if( isEmpty() ){
            throw new RuntimeException();
        } else {
            return front.element;
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        ListNode current = front;
        while(front != null){
            size++;
            front = front.next;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append("[");
        ListNode<T> current = front;
        while (current != null) {
            s.append(current.element.toString()).append(", ");
            current = current.next;
        }
        if (s.length() > 1) {
            s.replace(s.length() - 2, s.length(), "");
        }
        return s.append("]").toString();
    }

    private static class ListNode<T> {
        T element;
        ListNode<T> next;

        ListNode(T element, ListNode<T> next) {
            this.element = element;
            this.next = next;
        }
    }
}
