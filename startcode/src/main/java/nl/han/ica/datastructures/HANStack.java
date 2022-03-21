package nl.han.ica.datastructures;

public class HANStack<T> implements IHANStack<T> {

    ListNode<T> tos;

    @Override
    public void push(T value) {
        tos = new ListNode<>(value, tos);
    }

    @Override
    public T pop() {
        T element = tos.element;
        tos = tos.next;
        return element;
    }

    @Override
    public T peek() {
        return tos != null ? tos.element : null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append("[");
        ListNode<T> current = tos;
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
