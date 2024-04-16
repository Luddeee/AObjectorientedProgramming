package Exercise12;

public class NumLeaves<T> implements TreeVisitor<T, Integer, Integer> {

	@Override
	public Integer visit(Leaf<T> leaf, Integer val) {
		return 1;
	}

	@Override
	public Integer visit(Node<T> node, Integer val) {
		// for all the children see the number of leaves in the children and sum
		int n = 0;
		for(Tree<T> t : node.getChildren()) {
			n += t.accept(this, null);
		}
		return n;
	}

}
