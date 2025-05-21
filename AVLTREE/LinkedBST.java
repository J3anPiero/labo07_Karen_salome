package AVLTREE;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    protected NodeBST<E> root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRecursive(root, data);
    }

    private NodeBST<E> insertRecursive(NodeBST<E> current, E data) throws ItemDuplicated {
        if (current == null) {
            return new NodeBST<>(data);
        }

        int comparison = data.compareTo(current.getData());

        if (comparison < 0) {
            current.setLeft(insertRecursive(current.getLeft(), data));
        } else if (comparison > 0) {
            current.setRight(insertRecursive(current.getRight(), data));
        } else {
            throw new ItemDuplicated("El elemento " + data + " ya existe en el árbol.");
        }
        return current;
    }

    @Override
    public E search(E data) throws ItemNoFound {
        NodeBST<E> result = searchRecursive(root, data);
        if (result != null) {
            return result.getData();
        } else {
            throw new ItemNoFound("El elemento " + data + " no se encontró en el árbol.");
        }
    }

    private NodeBST<E> searchRecursive(NodeBST<E> current, E data) {
        if (current == null) return null;

        int comparison = data.compareTo(current.getData());

        if (comparison < 0) return searchRecursive(current.getLeft(), data);
        if (comparison > 0) return searchRecursive(current.getRight(), data);
        return current;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = deleteRecursive(root, data);
    }

    private NodeBST<E> deleteRecursive(NodeBST<E> current, E data) {
        if (current == null) return null;

        int comparison = data.compareTo(current.getData());

        if (comparison < 0) {
            current.setLeft(deleteRecursive(current.getLeft(), data));
        } else if (comparison > 0) {
            current.setRight(deleteRecursive(current.getRight(), data));
        } else {
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            NodeBST<E> successor = findMin(current.getRight());
            current.setData(successor.getData());
            current.setRight(deleteRecursive(current.getRight(), successor.getData()));
        }

        return current;
    }

    private NodeBST<E> findMin(NodeBST<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    
    /**
     * IN ORDER
     */
    public void printInOrder() {
        inOrder(root);
    }

    private void inOrder(NodeBST<E> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getData());
            inOrder(node.getRight());
        }
    }
    
    /**
     * PRE ORDER
     */
    public void printPreOrder() {
        preOrder(root);
    }

    
    private void preOrder(NodeBST<E> node) {
        if (node != null) {
            System.out.println(node.getData());        // Visitar la raíz
            preOrder(node.getLeft());                  // Recorrer subárbol izquierdo
            preOrder(node.getRight());                 // Recorrer subárbol derecho
        }
    }

    /**
     * POST ORDER
     */
    
    public void printPostOrder() {
        postOrder(root);
    }

    
    private void postOrder(NodeBST<E> node) {
        if (node != null) {
            postOrder(node.getLeft());   // Recorrer subárbol izquierdo
            postOrder(node.getRight());  // Recorrer subárbol derecho
            System.out.println(node.getData()); // Visitar la raíz
        }
    }

    
    /**
     *findMinNode y findMaxNode 
     *
     */
    private E findMinNode(NodeBST<E> node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("Subárbol vacío, no se puede encontrar el mínimo.");
        }

        NodeBST<E> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return search(current.getData()); // usar el método público con validación
    }

    
    private E findMaxNode(NodeBST<E> node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("Subárbol vacío, no se puede encontrar el máximo.");
        }

        NodeBST<E> current = node;
        while (current.getRight() != null) {
            current = current.getRight();
        }

        return search(current.getData()); // usar el método público con validación
    }

    public E findMin() throws ItemNoFound {
        return findMinNode(root);
    }

    public E findMax() throws ItemNoFound {
        return findMaxNode(root);
    }

    /**
     * destroyNodes
     */
    
    @Override
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol ya está vacío.");
        }
        root = null;  // Java se encarga de liberar memoria con GC
    }

    /**
     * countAllNodes() y countNodes()
     */
    
    @Override
    public int countAllNodes() {
        return countNonLeafNodes(root);
    }

    @Override
    public int countNodes() {
        return countAllNodes();  // mismo comportamiento
    }

    private int countNonLeafNodes(NodeBST<E> node) {
        if (node == null || (node.getLeft() == null && node.getRight() == null)) {
            return 0;
        }
        return 1 + countNonLeafNodes(node.getLeft()) + countNonLeafNodes(node.getRight());
    }

    
    /**
     * height(x) iterativo
     */
    @Override
    public int height(E x) {
        try {
            E value = search(x); // asegúrate que existe
            NodeBST<E> node = findNode(root, x);
            if (node == null) return -1;

            return iterativeHeight(node);
        } catch (ItemNoFound e) {
            return -1;
        }
    }

    private NodeBST<E> findNode(NodeBST<E> node, E x) {
        while (node != null) {
            int cmp = x.compareTo(node.getData());
            if (cmp < 0) node = node.getLeft();
            else if (cmp > 0) node = node.getRight();
            else return node;
        }
        return null;
    }

    private int iterativeHeight(NodeBST<E> rootNode) {
        if (rootNode == null) return -1;

        java.util.Queue<NodeBST<E>> queue = new java.util.LinkedList<>();
        queue.add(rootNode);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                NodeBST<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return height;
    }

    
    /**
     * amplitude(nivel)
     */
    @Override
    public int amplitude() {
        if (root == null) return 0;

        java.util.Queue<NodeBST<E>> queue = new java.util.LinkedList<>();
        queue.add(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelWidth = queue.size();
            maxWidth = Math.max(maxWidth, levelWidth);

            for (int i = 0; i < levelWidth; i++) {
                NodeBST<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }

        return maxWidth;
    }

    /**
     * Calcula el área del árbol binario de búsqueda.
     * Área = número de hojas × altura del árbol.
     * Todo el recorrido es iterativo.
     */
    public int areaBST() {
        if (root == null) return 0;

        java.util.Queue<NodeBST<E>> queue = new java.util.LinkedList<>();
        queue.add(root);
        int leafCount = 0;
        int height = -1;

        // Recorrido por niveles (BFS)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++; // Cada iteración representa un nivel

            for (int i = 0; i < levelSize; i++) {
                NodeBST<E> current = queue.poll();

                boolean isLeaf = current.getLeft() == null && current.getRight() == null;
                if (isLeaf) leafCount++;

                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }

        return leafCount * height;
    }

    /**
     * Muestra el árbol en consola en formato visual (horizontal),
     * rotado 90° hacia la izquierda.
     */
    public void drawBST() {
        drawBSTRecursive(root, 0);
    }

    // Método auxiliar para dibujar el árbol
    private void drawBSTRecursive(NodeBST<E> node, int level) {
        if (node != null) {
            drawBSTRecursive(node.getRight(), level + 1);  // Lado derecho primero
            System.out.println("    ".repeat(level) + node.getData());  // Nodo actual
            drawBSTRecursive(node.getLeft(), level + 1);   // Luego lado izquierdo
        }
    }

    
    /**
     * 
     * mostrar contenido en una forma geragica
     * representacion con parentesis de sangria de un arbol
     */
    
    public void parenthesize() {
        parenthesize(root, 0);
    }

    private void parenthesize(NodeBST<E> node, int level) {
        if (node == null) return;

        printIndent(level);
        System.out.print(node.getData());

        if (node.getLeft() != null || node.getRight() != null) {
            System.out.println(" (");

            if (node.getLeft() != null)
                parenthesize(node.getLeft(), level + 1);
            if (node.getRight() != null)
                parenthesize(node.getRight(), level + 1);

            printIndent(level);
            System.out.println(")");
        } else {
            System.out.println();
        }
    }

    private void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Dos espacios por nivel de profundidad
        }
    }

    public boolean sameArea(LinkedBST<E> other) {
        if (other == null) return false;
        return this.areaBST() == other.areaBST();
    }
    
    // Representación visual del árbol (opcional)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRecursive(root, sb, 0);
        return sb.toString();
    }

    private void toStringRecursive(NodeBST<E> node, StringBuilder sb, int level) {
        if (node != null) {
            toStringRecursive(node.getRight(), sb, level + 1);
            sb.append("    ".repeat(level)).append(node.getData()).append("\n");
            toStringRecursive(node.getLeft(), sb, level + 1);
        }
    }
}
