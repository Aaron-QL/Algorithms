package other.Ex1_3;

public class SimpleStack {
    public static void main(String[] args) {
        SimpleStack s = new SimpleStack(3);

        s.push("aaa");
        s.push("bb");
        System.out.println(s.pop());
        System.out.println(s.pop());
    }

    private int ptr  = 0;

    private String[] stack;

    public SimpleStack(int cap) {
        this.stack = new String[cap];
    }

    public void push(String item) {
        stack[ptr++] = item;
    }

    public String pop() {
        return stack[--ptr];
    }

    public boolean isEmpty() {
        return ptr == 0;
    }

    public int size() {
        return ptr;
    }
}
