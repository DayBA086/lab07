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

    // Insertar dato validando duplicado
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
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
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

    // Recorrido In-Orden
    public void showInOrder() {
        System.out.print("Recorrido In-Orden: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // Recorrido Pre-Orden
    public void showPreOrder() {
        System.out.print("Recorrido Pre-Orden: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Recorrido Post-Orden
    public void showPostOrder() {
        System.out.print("Recorrido Post-Orden: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Mínimo y máximo
    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("Subárbol vacío: no se puede encontrar el mínimo.");
        while (node.left != null)
            node = node.left;
        return this.search(node.data);
    }

    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null)
            throw new ItemNoFound("Subárbol vacío: no se puede encontrar el máximo.");
        while (node.right != null)
            node = node.right;
        return this.search(node.data);
    }

    public E getMin() throws ItemNoFound {
        return findMinNode(this.root);
    }

    public E getMax() throws ItemNoFound {
        return findMaxNode(this.root);
    }

    // Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol ya está vacío.");
        root = null;
    }

    // Contar todos los nodos
    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node node) {
        if (node == null) return 0;
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }

    // Contar nodos no hoja
    public int countNodes() {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node node) {
        if (node == null || (node.left == null && node.right == null)) return 0;
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }

    // Altura de subárbol con raíz x
    public int height(E x) {
        Node current = root;
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp == 0) break;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        if (current == null) return -1;

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(current);
        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);
            }
        }

        return height;
    }

    // Amplitud en un nivel
    public int amplitude(int nivel) {
        if (nivel < 0 || root == null) return 0;
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        int nivelActual = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (nivelActual == nivel) return size;

            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);
            }
            nivelActual++;
        }
        return 0;
    }

    // Área del árbol
    public int areaBST() {
        if (root == null) return 0;
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        int nivel = 0, hojas = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left == null && node.right == null) hojas++;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            nivel++;
        }

        return hojas * nivel;
    }

    // Dibujar arbol
    public void drawBST() {
        System.out.println(this.toString());
    }

    // método grafico (toString)
    @Override
    public String toString() {
        if (root == null) return "(árbol vacío)";
        StringBuilder sb = new StringBuilder();
        buildTreeString(root, "", true, sb);
        return sb.toString();
    }

    private void buildTreeString(Node node, String prefix, boolean isTail, StringBuilder sb) {
        if (node == null) return;

        sb.append(prefix)
          .append(isTail ? "└── " : "├── ")
          .append(node.data)
          .append("\n");

        boolean hasLeft = node.left != null;
        boolean hasRight = node.right != null;

        if (hasLeft || hasRight) {
            if (hasLeft && hasRight) {
                buildTreeString(node.left, prefix + (isTail ? "    " : "│   "), false, sb);
                buildTreeString(node.right, prefix + (isTail ? "    " : "│   "), true, sb);
            } else if (hasLeft) {
                buildTreeString(node.left, prefix + (isTail ? "    " : "│   "), true, sb);
            } else {
                buildTreeString(node.right, prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

    //
    public void parenthesize() {
        if (root == null) {
            System.out.println("()");
        } else {
            parenthesizeRec(root, 0);
        }
    }
    
    // Metodo auxiliar recursivo para construir la representacion con sangria
    private void parenthesizeRec(Node node, int nivel) {
        if (node == null) return;
    
        // imprimir sangria
        for (int i = 0; i < nivel; i++) System.out.print("  ");
    
        // imprimir nodo con parentesis
        System.out.print("(" + node.data);
    
        if (node.left != null || node.right != null) {
            System.out.println();
            if (node.left != null)
                parenthesizeRec(node.left, nivel + 1);
            else {
                for (int i = 0; i <= nivel; i++) System.out.print("  ");
                System.out.println("()");
            }
    
            if (node.right != null)
                parenthesizeRec(node.right, nivel + 1);
            else {
                for (int i = 0; i <= nivel; i++) System.out.print("  ");
                System.out.println("()");
            }
    
            for (int i = 0; i < nivel; i++) System.out.print("  ");
            System.out.println(")");
        } else {
            System.out.println(")");
        }
    }
    
}
