// NLNode java program created by Adam Yassine
import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> {

	private NLNode<T> parent;

	private ListNodes<NLNode<T>> children;

	private T data;

	public NLNode() {
		// first constructor used to initialize instance variables to null
		this.parent = null;
		this.data = null;
		this.children = new ListNodes<NLNode<T>>();
	}

	public NLNode(T d, NLNode<T> p) {
		// second constructor used to initialize given instance variables as d and p
		this.data = d;
		this.parent = p;
		this.children = new ListNodes<NLNode<T>>();
	}

	public void setParent(NLNode<T> p) {
		// set parent variable to p
		this.parent = p;
	}

	public NLNode<T> getParent() {
		// return parent
		return parent;
	}

	public void addChild(NLNode<T> newChild) {
		// method to add a new child to the node
		
		// set new child to its parent
		newChild.setParent(this);
		//add the new child to the list
		children.add(newChild);

	}

	public Iterator<NLNode<T>> getChildren() {
		// method used to return the children in an iterated format
		return children.getList();
	}

	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
		// return an iterator containing the children in the order specified by sorter
		return children.sortedList(sorter);
	}

	public T getData() {
		// return the data stored in 'this' node
		return this.data;
	}

	public void setData(T d) {
		// set data to 'd'
		data = d;
	}
}
