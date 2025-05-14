package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // Clase interna Node
    private class Node {
        public E data;
        public Node left, right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Atributo raíz
    private Node root;

    // Constructor
    public LinkedBST() {
        this.root = null;
    }

    // Método para saber si el árbol está vacío
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // 1️⃣ Insertar dato validando duplicado
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp == 0)
            throw new ItemDuplicated("El dato ya existe: " + data);
        else if (cmp < 0)
            node.left = insertRec(node.left, data);
        else
            node.right = insertRec(node.right, data);
        return node;
    }

    // Buscar dato validando si no existe
    public E search(E data) throws ItemNoFound {
        Node node = searchRec(root, data);
        if (node == null) {
            throw new ItemNoFound("Elemento no encontrado: " + data);
        }
        return node.data;
    }

    private Node searchRec(Node node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp == 0) return node;
        else if (cmp < 0) return searchRec(node.left, data);
        else return searchRec(node.right, data);
    }

    // Eliminar dato validando si está vacío
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("No se puede eliminar de un árbol vacío.");
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = deleteRec(node.left, data);
        else if (cmp > 0)
            node.right = deleteRec(node.right, data);
        else {
            // Nodo con solo un hijo o sin hijos
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Nodo con dos hijos: reemplazar por su sucesor inorden
            Node minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = deleteRec(node.right, minNode.data);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Mostrar en orden como String
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BST (inorden): [ ");
        inOrder(root);
        sb.append("]");
        return sb.toString();
    }

// Método público para probar el recorrido inOrden
public void showInOrder() {
    System.out.print("Recorrido In-Orden: ");
    inOrder(root);
    System.out.println();
}

// Método privado que hace el recorrido In-Orden recursivamente
private void inOrder(Node node) {
    if (node != null) {
        inOrder(node.left);               // Visitar subárbol izquierdo
        System.out.print(node.data + " "); // Visitar nodo actual
        inOrder(node.right);              // Visitar subárbol derecho
    }
}
// Método público para probar el recorrido Pre-Orden
public void showPreOrder() {
    System.out.print("Recorrido Pre-Orden: ");
    preOrder(root);
    System.out.println();
}

// Método privado que realiza el recorrido Pre-Orden: nodo, izquierda, derecha
private void preOrder(Node node) {
    if (node != null) {
        System.out.print(node.data + " "); // Visitar nodo actual
        preOrder(node.left);               // Recorrer subárbol izquierdo
        preOrder(node.right);              // Recorrer subárbol derecho
    }
}

// Método público para mostrar recorrido Post-Orden
public void showPostOrder() {
    System.out.print("Recorrido Post-Orden: ");
    postOrder(root);
    System.out.println();
}

// Método privado que realiza el recorrido Post-Orden: izquierda, derecha, nodo
private void postOrder(Node node) {
    if (node != null) {
        postOrder(node.left);              // Subárbol izquierdo
        postOrder(node.right);             // Subárbol derecho
        System.out.print(node.data + " "); // Nodo actual
    }
}

// Método privado que encuentra el nodo con valor mínimo a partir de un nodo dado
private E findMinNode(Node node) throws ItemNoFound {
    if (node == null)
        throw new ItemNoFound("Subárbol vacío: no se puede encontrar el mínimo.");

    // Desciende hasta el nodo más a la izquierda
    while (node.left != null)
        node = node.left;

    // Valida usando el método search()
    return this.search(node.data);
}

// Método privado que encuentra el nodo con valor máximo a partir de un nodo dado
private E findMaxNode(Node node) throws ItemNoFound {
    if (node == null)
        throw new ItemNoFound("Subárbol vacío: no se puede encontrar el máximo.");

    // Desciende hasta el nodo más a la derecha
    while (node.right != null)
        node = node.right;

    // Valida usando el método search()
    return this.search(node.data);
}
// Devuelve el valor mínimo del árbol (uso externo)
public E getMin() throws ItemNoFound {
    return findMinNode(this.root); // uso interno del atributo privado
}

// Devuelve el valor máximo del árbol (uso externo)
public E getMax() throws ItemNoFound {
    return findMaxNode(this.root); // uso interno del atributo privado
}


}
