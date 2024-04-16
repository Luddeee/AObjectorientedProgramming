package Exercise12;

import java.util.List;

public interface Tree<T> {
	<R,A> R accept(TreeVisitor<T,R,A> v, A val);
	
	T getValue();
}