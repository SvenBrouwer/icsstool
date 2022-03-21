package nl.han.ica.datastructures;

import java.util.Iterator;

public class HANLinkedList<T> implements IHANLinkedList<T> {

    ListNode<T> header;

    public HANLinkedList() {
        header = new ListNode<>(null, null);
    }

    @Override
    public void addFirst(T value) {
        header.next = new ListNode<>(value, header.next);
    }

    @Override
    public void clear() {
        header.next = null;
    }

    @Override
    public void insert(int index, T value) {
        ListNode<T> tmp = header;

        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        tmp.next = new ListNode<>(value, tmp.next);
    }

    @Override
    public void delete(int pos) {
        ListNode<T> tmp = header;

        for (int i = 0; i < pos; i++) {
            tmp = tmp.next;
        }

        tmp.next = tmp.next.next;
    }

    @Override
    public T get(int pos) {
        ListNode<T> tmp = header;

        for (int i = 0; i <= pos; i++) {
            tmp = tmp.next;
        }

        return tmp.element;
    }

    @Override
    public void removeFirst() {
       header.next = header.next.next;
    }

    @Override
    public T getFirst() {
        return header.next.element;
    }

    @Override
    public int getSize() {
        int size = 0;
        ListNode<T> current = header;
        while(current.next != null){
            size++;
        }
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(header);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder().append("[");
        ListNode<T> current = header;
        while (current.next != null){
            current = current.next;
            s.append(current.element.toString()).append(", ");
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

    private static class LinkedListIterator<T> implements Iterator<T> {

        ListNode<T> current;

        public LinkedListIterator(ListNode<T> node) {
            this.current = node;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.element;
        }
    }
}
