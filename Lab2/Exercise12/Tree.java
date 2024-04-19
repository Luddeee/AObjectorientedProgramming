package Exercise12;


public interface Tree<T> {
	<R,A> R accept(TreeVisitor<T,R,A> v, A val);
	
	T getValue();
}