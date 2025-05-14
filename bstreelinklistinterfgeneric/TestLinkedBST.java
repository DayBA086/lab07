package bstreelinklistinterfgeneric;

import exceptions.*;

public class TestLinkedBST<E> {
   
    public static void main(String[] args) {
        try {
            LinkedBST<Integer> bst = new LinkedBST<>();

            // Insertar nodos en orden para formar el árbol del gráfico
            bst.insert(400);
            bst.insert(100);
            bst.insert(700);
            bst.insert(50);
            bst.insert(200);
            bst.insert(75);

            // Mostrar recorrido inorden
            bst.showInOrder(); 
            // Mostrar recorrido preorden
            bst.showPreOrder(); 
            // Mostrar recorrido postorden
            bst.showPostOrder();

            // Prueba búsqueda
            System.out.println("Elemento encontrado: " + bst.search(75));

            // Eliminar un nodo
            bst.delete(100);  // Nodo con dos hijos

            // Mostrar con toString
            System.out.println("Despues de eliminar 100: " +bst.toString());
            // Mostrar el minimo
            System.out.println("Mínimo del árbol: " + bst.getMin());
            // Mostrar el maximo
            System.out.println("Máximo del árbol: " + bst.getMax());
        } catch (ItemDuplicated | ItemNoFound | ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

  
}