
public class NumLeavesVisitor<T> implements TreeVisitor<T,Integer,Integer> {

	@Override
	public Integer visit(Leaf<T> leaf, Integer val) {
		return 1;
	}

	@Override
	public Integer visit(Node<T> node, Integer val) {
		int result = 0;
		for(Tree<T> c : node.getChildred()) {
			result += c.accept(this, val);
			/* This is not the way to do it!!!:
			if(c instanceof Leaf) {
				result += visitLeaf((Leaf<T>)c);
			}else {
				result += visitNode((Node<T>)c);
			}
			*/
		}
		return result;
	}

}
