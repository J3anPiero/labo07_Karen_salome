package AVLTREE;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ExceptionIsEmpty;
    boolean isEmpty();
	void destroyNodes() throws ExceptionIsEmpty;
	int countAllNodes();
	int countNodes();
	int height(E x);
	int amplitude();
}