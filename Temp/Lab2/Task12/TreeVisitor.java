
public interface TreeVisitor<T, R, A> {
	R visit(Leaf<T> leaf, A val);
	R visit(Node<T> node, A val);
}
