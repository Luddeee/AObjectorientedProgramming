package Lab3;

import java.lang.reflect.InvocationTargetException;

@Element(name="node")
public class Tree<T> {

    private Tree<T>[] children = null;
    private T value;
    
    public Tree(T v, Tree<T>[] trees) { 
        children = trees; value = v; 
    }

    public Tree(T v) { 
        value = v; 
    }

    @SubElements(name="subnodes")
    public Tree<T>[] getChildren() { 
        return children; 
    }

    @ElementField(name="value")
    public T getValue() { 
        return value; 
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Tree<String> t = new Tree<String>("top", new Tree[] {
            new Tree("sub1"),
            new Tree("sub2")
        });
        Saver s = new Saver();
        String r = s.save(t);
        System.out.println(r);
    }
}

