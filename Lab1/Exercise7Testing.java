import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.*;

public class Exercise7Testing {
    Exercise7 stack;

    @Before
    public void setup(){
        stack = new Exercise7();
    }

    @Test
    public void push_test(){
        stack.push(3);
        stack.push(2);
        stack.push(1);
        assertEquals(3, stack.get(0));
        assertEquals(2, stack.get(1));
        assertEquals(1, stack.get(2));

        int[] array = new int[] {11, 22, 33};
        stack.push(3, array);
        assertEquals(11, stack.get(3));
        assertEquals(22, stack.get(4));
        assertEquals(33, stack.get(5));
        assertEquals(6, stack.size());
    }

    @Test
    public void pop_test(){
        stack.push(3);
        stack.push(2);
        stack.push(1);    
        
        assertEquals(3, stack.size());
        stack.pop();
        assertEquals(2, stack.size());

        stack.push(4);
        assertEquals(2, stack.get(1));
        stack.pop(2);
        assertEquals(1, stack.size());
        assertEquals(3, stack.get(0));
        stack.pop();
        assertEquals(0, stack.size());
        
        assertThrows(IndexOutOfBoundsException.class, () -> stack.pop());
    }



}
