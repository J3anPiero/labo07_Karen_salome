package BSTREE;

public class NodeBST<E> {
    protected E data;
    protected NodeBST<E> left, right;
    protected NodeBST<E> next; // para la cola

    // constructor modificado para incluir next:
    public NodeBST(E data) {
        this(data, null, null, null);
    }

    public NodeBST(E data, NodeBST<E> left, NodeBST<E> right, NodeBST<E> next) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    public NodeBST<E> getNext() { return next; }
    public void setNext(NodeBST<E> next) { this.next = next; }

    public E getData() {
        return data;
    }

    public NodeBST<E> getLeft() {
        return left;
    }

    public void setLeft(NodeBST<E> left) {
        this.left = left;
    }

    public NodeBST<E> getRight() {
        return right;
    }

    public void setRight(NodeBST<E> right) {
        this.right = right;
    }

    public String toString() {
        return data.toString();
    }

	public void setData(E data2) {
		
		
	}
}