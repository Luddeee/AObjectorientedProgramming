public class Exercise8<Element>{
    private Element[] elements;
    private int tail = 0;  
    private int head = 0; 
    private int size = 0;

    @SuppressWarnings("unchecked")
    public Exercise8(int capacity) {
        elements = (Element[]) new Object[capacity];
    }

    public void enqueue(Element item) {
        assert item != null : "Assert so item is NOT NULL";
        
        if (size == elements.length) {
            increaseSize();
        }

        elements[tail] = item;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public Element dequeue() {
        assert size > 0 : "Assert so que is not empty";
        
        Element item = elements[head];
        elements[head] = null; // Help garbage collection
        head = (head + 1) % elements.length;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void increaseSize() {
        Element[] newArray = (Element[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[(head + i) % elements.length];
        }
        elements = newArray;
        head = 0;
        tail = size;
    }

    public static void main(String[] args) {
        Exercise8<Integer> queue = new Exercise8<>(2);
        queue.enqueue(1);
        queue.enqueue(2); //Null instead of 2 to check assert and run with json
        System.out.println("Que size is now: "+queue.size);

        //que needs to grow to que theese itmes
        queue.enqueue(3);
        queue.enqueue(4);

        while (!queue.isEmpty()) {
            System.out.println("Dequeued items: " + queue.dequeue());
        }
        System.out.println("Que size is now: " + queue.size());
    }
}
