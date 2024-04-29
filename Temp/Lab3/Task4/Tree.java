package Temp.Lab3.Task4;

import java.lang.reflect.InvocationTargetException;

@Element(name = "node")
public class Tree<T> {

    private Tree<T>[] children = null;
    private T value;

    public Tree(T v, Tree<T>[] trees) {
        children = trees;
        value = v;
    }

    public Tree(T v) {
        value = v;
    }

    @SubElements(name = "subnodes")
    public Tree<T>[] getChildren() {
        return children;
    }

    @ElementField(name = "value")
    public T getValue() {
        return value;
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Tree<String> t = new Tree<String>("top",
        new Tree[] {
        new Tree("sub1"),
        new Tree("sub2")
        });

        Saver s = new Saver();
        String r = s.save(t);
        System.out.println(r);

        System.out.println();

        Tree<String> t1 = new Tree<String>("node1",
                new Tree[] { new Tree<String>("leaf1"), new Tree<String>("leaf2"), new Tree<String>("leaf3") });

        Tree<String> t2 = new Tree<String>("node2",
                new Tree[] { new Tree<String>("leaf4"), new Tree<String>("leaf5"), new Tree<String>("leaf6") });

        Tree<String> t3 = new Tree<String>("node3",
                new Tree[] { new Tree<String>("leaf7"), new Tree<String>("leaf8"), new Tree<String>("leaf9") });

        Tree<String> rootnode = new Tree<String>("root", new Tree[] { t1, t2, t3 });

        String r2 = s.save(rootnode);
        System.out.println(r2);
    }
}
