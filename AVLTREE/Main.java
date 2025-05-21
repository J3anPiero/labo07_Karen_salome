package AVLTREE;

import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class Main {
    public static void main(String[] args) throws ExceptionIsEmpty, ItemNoFound {
    	LinkedBST<Integer> tree = new LinkedBST<>();
    	LinkedBST<Integer> tree2 = new LinkedBST<>();
    	LinkedBST<String> tree3 = new LinkedBST<>();


        try {
            // Inserciones en el árbol tree (recursivas)
            tree.insert(10);
            tree.insert(5);
            tree.insert(1);
            tree.insert(2);
            tree.insert(8);
            tree.insert(30);
            tree.insert(20);
            tree.insert(12);
            tree.insert(18);
            tree.insert(29);
            tree.insert(26);
            tree.insert(37);

            // Inserciones en el árbol tree2 (iterativas)
            tree2.insert(10);
            tree2.insert(5);
            tree2.insert(1);
            tree2.insert(2);
            tree2.insert(8);
            tree2.insert(30);
            tree2.insert(20);
            tree2.insert(12);
            tree2.insert(18);
            tree2.insert(29);
            tree2.insert(26);
            tree2.insert(37);
            
            
            tree3.insert("Sales");
            tree3.insert("Domestic");
            tree3.insert("International");
            tree3.insert("Canada");
            tree3.insert("S. America");
            tree3.insert("Overseas");
            tree3.insert("Africa");
            tree3.insert("Europe");
            tree3.insert("Asia");
            tree3.insert("Australia");


            // Prueba del recorrido inOrden
            System.out.println("InOrden del tree:");
            tree.printInOrder();

            // Prueba del recorrido iterativePreOrden
            System.out.println("\nIterative PreOrden del tree:");
            tree.printPreOrder();

            // Prueba de countNodes() (nodos NO hoja)
            System.out.println("\nNúmero de nodos NO hoja en tree: " + tree.countNodes());
            System.out.println("Número de nodos NO hoja en tree2: " + tree2.countNodes());

            // Prueba de height(x)
            System.out.println("\nAltura del subárbol con raíz en 10: " + tree.height(10));
            System.out.println("Altura del subárbol con raíz en 20: " + tree.height(20));
            System.out.println("Altura del subárbol con raíz en 999 (inexistente): " + tree.height(999));

            // Prueba de areaBST()
            System.out.println("\nÁrea del tree: " + tree.areaBST());
            System.out.println("Área del tree2: " + tree2.areaBST());

            // Prueba de sameArea() - ahora en BSTreeTest
            System.out.println("\n¿tree y tree2 tienen la misma área? " + sameArea(tree, tree2));
            
            tree3.parenthesize();
           
            
        } catch (ItemDuplicated e) {
            System.out.println("Item ya existe en el árbol.");
        }
    }

    // Método sameArea() agregado a la clase de pruebas BSTreeTest
    public static boolean sameArea(LinkedBST<?> t1, LinkedBST<?> t2) {
        return t1.areaBST() == t2.areaBST();
    }
}
