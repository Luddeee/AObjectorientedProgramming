package Exercise12;

import java.util.*;

public class ListLeavesVisitor<T> implements TreeVisitor<T, List<T>, List<T>> {

	@Override
	public List<T> visit(Leaf<T> leaf, List<T> val) {
		val.add(leaf.getValue());
		return val;
	}

	@Override
	public List<T> visit(Node<T> node, List<T> val) {
		for(Tree<T> t : node.getChildren()) {
			t.accept(this, val);
		}
		return val;
	}

}
