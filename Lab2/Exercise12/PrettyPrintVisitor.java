package Exercise12;

public class PrettyPrintVisitor<T> implements TreeVisitor<T, String, Integer> {

    @Override
    public String visit(Leaf<T> leaf, Integer depth) {
        return printWithIndent(leaf.getValue(), depth);
    }

    @Override
    public String visit(Node<T> node, Integer depth) {
        StringBuilder temp = new StringBuilder();
        temp.append(printWithIndent(node.getValue(), depth));
        for (Tree<T> child : node.getChildren()) {
            temp.append(child.accept(this, depth + 1));
        }
        return temp.toString();
    }

    private String printWithIndent(T value, int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("   ");
        }
        //System.out.println(indent.toString() + "|-- " + value);
        return(indent.toString() + "|-- " + value + "\n");
    }
}
