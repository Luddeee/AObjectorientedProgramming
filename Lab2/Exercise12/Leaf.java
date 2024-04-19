package Exercise12;

public class Leaf<T> implements Tree<T> {

	T value;

	public Leaf(T v) {
		value = v;
	}

	public T getValue() {
		return value;
	}

	@Override
	public <R,A> R accept(TreeVisitor<T, R, A> v, A val) {
		return v.visit(this, val);
	}

}