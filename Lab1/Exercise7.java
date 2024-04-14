import java.util.ArrayList;

public class Exercise7 {


    ArrayList<Integer> stack;

    public Exercise7() {
        this.stack = new ArrayList<Integer>();
    }

    public int get(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("index can't be less than 0");
        }

        return stack.get(index);
    }

    public int size() {
        return stack.size();
    }

    public void push(int node) {
        this.stack.add(node);
    }

    public void push(int n, int[] array) {
        
        if (n < 1) {
            throw new IllegalArgumentException("n can't be 0 or lower");
        }

        for (int i = 0; i < n; i++) {
            push(array[i]);
        }
    }

    public int pop() {

        if (stack.size() == 0) {
            throw new IndexOutOfBoundsException("empty stack");
        }

        int removed_el = get(stack.size() - 1);
        this.stack.remove(stack.size() - 1);
        return removed_el;
    }

    // removes n elements from the stack
    public int[] pop(int n) {
        int[] array_d = new int[n];
        for (int i = 0; i < n; i++) {
            array_d[n - 1 - i] = this.pop();
        }
        return array_d;
    }

    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) {
        Exercise7 stack1 = new Exercise7();
        int[] array1 = new int[] { 6, 7, 8, 9, 5};
        stack1.push(2, array1);
        System.out.println(stack1);

        Exercise7 stack2 = new Exercise7();
        stack2.push(3, array1);
        System.out.println(stack2);
    }
}
