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

        System.out.println("");
        Tree<String> test1 = new Tree<String>("top", 
        new Tree[] {
            new Tree("test1.1"),
            new Tree("test1.2"),
            new Tree("test1.3")
        });

        Tree<String> test2 = new Tree<String>("top", 
        new Tree[] {
            new Tree("test2.1"),
            new Tree("test2.2"),
            new Tree("test2.3")
        });

        Tree<String> topnode = new Tree<String>("root", new Tree[] { test1, test2});

        String r2 = s.save(topnode);
        System.out.println(r2);

    }
}

