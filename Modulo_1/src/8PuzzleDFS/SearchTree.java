import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class SearchTree {
    private Node root;
    private String goalState;
    private String initialState;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
    }

    // BÚSQUEDA PRIMERO EN PROFUNDIDAD (DFS - LIFO)
    public void depthFirstSearch(int maxDepthLimit) {
        long startTime = System.nanoTime();

        // Pila LIFO (Last In, First Out)
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(root);
        visited.add(root.getState());

        int evaluatedNodes = 0;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            evaluatedNodes++;

            // Verificar si es el estado objetivo
            if (currentNode.getState().equals(goalState)) {
                long endTime = System.nanoTime();
                double durationInSeconds = (endTime - startTime) / 1e9;

                System.out.println("=== ¡SOLUCIÓN ENCONTRADA (DFS)! ===");
                System.out.println("Nodos evaluados (desplegados): " + evaluatedNodes);
                System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                System.out.println("Costo total de la solución g(n): " + currentNode.getDepth());
                System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                System.out.println("\n====== Pasos para la solución ======");
                printPath(currentNode);
                return;
            }

            // Evitar profundizar infinitamente
            if (currentNode.getDepth() < maxDepthLimit) {
                for (Node child : currentNode.getChildren()) {
                    if (!visited.contains(child.getState())) {
                        visited.add(child.getState());
                        stack.push(child);
                    }
                }
            }
        }

        System.out.println("No se encontró solución dentro del límite de profundidad de " + maxDepthLimit + ".");
    }

    private void printPath(Node node) {
        Stack<Node> path = new Stack<>();
        Node current = node;

        while (current != null) {
            path.push(current);
            current = current.getParent();
        }

        int step = 0;
        while (!path.isEmpty()) {
            Node n = path.pop();
            System.out.println("Paso " + step + " (Costo = " + n.getDepth() + "): [" + n.getState() + "]");
            step++;
        }
    }
}