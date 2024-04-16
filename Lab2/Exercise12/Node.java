package Exercise12;

import java.util.*;

public class Node<T> implements Tree<T> {

	private List<Tree<T>> children;
	private T value;

	public Node(T v, List<Tree<T>> trees) {
		children = trees;
		value = v;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}
	
	public T getValue() {
		return value;
	}

	@Override
	public <R,A> R accept(TreeVisitor<T,R,A> v, A val) {
		return v.visit(this, val);
	}
}