import java.util.ArrayList;

public class Exercise9 {
    ArrayList<Message> stack;

    public Exercise9() {
        this.stack = new ArrayList<Message>();
    }

    public Message get(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("index can't be less than 0");
        }

        return stack.get(index);
    }

    public int size() {
        return stack.size();
    }

    public void push(Message node) {
        this.stack.add(node);
    }

    public void push(int n, Message[] array) {
        
        if (n < 1) {
            throw new IllegalArgumentException("n can't be 0 or lower");
        }

        for (int i = 0; i < n; i++) {
            push(array[i]);
        }
    }

    public Message pop() {

        if (stack.size() == 0) {
            throw new IndexOutOfBoundsException("empty stack");
        }

        Message removed_el = get(stack.size() - 1);
        this.stack.remove(stack.size() - 1);
        return removed_el;
    }

    public Message[] pop(int n) {
        Message[] array_d = new Message[n];
        for (int i = 0; i < n; i++) {
            array_d[n - 1 - i] = this.pop();
        }
        return array_d;
    }

    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        Exercise9 stack1 = new Exercise9();
        Message[] array1 = new Message[5];
        for (int i = 0; i < 5; i++) {
            array1[i] = new Message("testing " + i);
        }
        stack1.push(new Message("ogaboga"));
        System.out.println(stack1);

        stack1.push(3, array1);
        System.out.println("Size: "+stack1.size());
        System.out.println(stack1);

        stack1.pop(2);
        System.out.println(stack1);
    }
}

