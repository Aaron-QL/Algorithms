package Ex1_3;

public class StandardStack<T> {
    private int ptr = 0;
    private T[] stack;

    public static void main(String[] args) {
        StandardStack<String> s = new StandardStack(2);
        s.push("q");
        System.out.println(s.pop());
    }

    public StandardStack(int cap) {
        this.stack = (T[]) new Object[cap];
    }

    public void push(T item) {
        if (ptr == stack.length) {
            resize(ptr * 2);
        }
        stack[ptr++] = item;
    }

    public T pop() {
        T item = stack[--ptr];
        stack[ptr] = null;
        if (ptr > 0 && ptr == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return ptr == 0;
    }

    public int size() {
        return ptr;
    }

    public boolean isFull() {
        return ptr == stack.length;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < ptr; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }
}
