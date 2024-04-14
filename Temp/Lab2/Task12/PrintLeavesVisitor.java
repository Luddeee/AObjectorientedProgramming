import java.util.ArrayList;

// package lecture7;

public class PrintLeavesVisitor<T> implements TreeVisitor<T, Integer, Integer> {

    int depth;

    PrintLeavesVisitor() {
        depth = -1;
    }

    @Override
    public Integer visit(Leaf<T> leaf, Integer currDepth) {

        String indent = "";

        for (int i = 0; i < currDepth; i++) {
            indent += " ";
        }

        System.out.println(indent + leaf.getValue());
        return currDepth;
    }

    @Override
    public Integer visit(Node<T> node, Integer currDepth) {
        String indent = "";
        depth++;

        for (int i = 0; i < depth; i++) {
            indent += " ";
        }

        if (currDepth < depth) {
            System.out.println(indent + node.getValue());
            currDepth = depth;
        } else {
            System.out.println(indent + node.getValue());
        }

        for (Tree<T> c : node.getChildred()) {
            currDepth = c.accept(this, depth + 1);
        }
        depth--;

        return currDepth;
    }

}
