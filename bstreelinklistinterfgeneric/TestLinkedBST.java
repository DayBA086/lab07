package bstreelinklistinterfgeneric;
import exceptions.*;

public class TestLinkedBST {

    public static void main(String[] args) {
        try {
            LinkedBST<Integer> bst = new LinkedBST<>();

            // insertar nodos
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);
            // insertar nodos pt 2
            LinkedBST<Integer> bst2 = new LinkedBST<>();
            bst2.insert(500);
            bst2.insert(300);
            bst2.insert(800);
            bst2.insert(100);
            bst2.insert(350);
            bst2.insert(900);

            System.out.println("Area del arbol 1: " + bst.areaBST());
            System.out.println("Area del arbol 2: " + bst2.areaBST());
            System.out.println("¿Tienen la misma area? " + sameArea(bst, bst2));

            // recorridos
            System.out.println("In-Orden:");
            bst.showInOrder();
            System.out.println("Pre-Orden:");
            bst.showPreOrder();
            System.out.println("Post-Orden:");
            bst.showPostOrder();

            // info del arbol
            System.out.println("Min: " + bst.getMin());
            System.out.println("Max: " + bst.getMax());
            System.out.println("Altura del subarbol con raiz 100: " + bst.height(100));
            System.out.println("Amplitud en nivel 2: " + bst.amplitude(2)); //ancho del nivel
            System.out.println("Total de nodos: " + bst.countAllNodes());// cuantos hay en total
            System.out.println("Nodos no hoja: " + bst.countNodes());// nodos con hijos
            System.out.println("Area del arbol BST: " + bst.areaBST()); //num nodos hoja(4)× altura(3) 

            // dibujar arbol
            System.out.println("arbol BST dibujado:");
            bst.drawBST();
            // dibujar segundo arbol
            System.out.println("arbol BST 2 dibujado:");
            bst2.drawBST();
            //ejercicio  parentesis
            System.out.println("Representacion parentesis del arbol BST:");
            bst.parenthesize();

            // destruir arbol
            bst.destroyNodes();
            System.out.println("Arbol destruido.");
            System.out.println("Intento de recorrido despues de destruir:");
            bst.showInOrder();

            
        } catch (ItemDuplicated | ItemNoFound | ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean sameArea(LinkedBST<?> tree1, LinkedBST<?> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
}
