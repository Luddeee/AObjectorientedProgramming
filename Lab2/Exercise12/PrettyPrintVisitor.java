package Exercise12;

public class PrettyPrintVisitor<T> implements TreeVisitor<T, Integer, Integer> {

    @Override
    public Integer visit(Leaf<T> leaf, Integer depth) {
        printWithIndent(leaf.getValue(), depth);
        return depth;
    }

    @Override
    public Integer visit(Node<T> node, Integer depth) {
        printWithIndent(node.getValue(), depth);
        for (Tree<T> child : node.getChildren()) {
            child.accept(this, depth + 1);
        }
        return depth;
    }

    private void printWithIndent(T value, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("   ");
        }
        System.out.println(indent.toString() + "|-- " + value);
    }
}
