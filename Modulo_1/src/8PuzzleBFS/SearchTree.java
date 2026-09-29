import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SearchTree {
    private Node root;
    private String goalState;
    private String initialState;

    public SearchTree(String initialState, String goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.root = new Node(initialState, null);
    }

    public SearchTree(String goalState, String initialState, Node root) {
        this.goalState = goalState;
        this.initialState = initialState;
        this.root = root;
    }

    public void breadthFirstSearch() {
        long startTime = System.nanoTime();

        // Buscar el nodo raíz y agregarlo a la cola 
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        visited.add(root.getState());

        int evaluatedNodes = 0;

        // Mientras la cola no esté vacía, hacer lo siguiente:
        while (!queue.isEmpty()) {
            // Sacar el primer nodo de la cola y verificar si es el nodo objetivo.
            Node currentNode = queue.poll();
            evaluatedNodes++;

            if (currentNode.getState().equals(goalState)) {
                long endTime = System.nanoTime();
                double durationInSeconds = (endTime - startTime) / 1e9;

                System.out.println("=======================================");
                System.out.println("Nodos evaluados (desplegados): " + evaluatedNodes);
                System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                System.out.println("\n====== Pasos para la solución =======");
                printPath(currentNode);
                return;
            }

            // Expandir el nodo actual y agregar los hijos no visitados a la cola
            for (Node child : currentNode.getChildren()) {
                if (!visited.contains(child.getState())) {
                    visited.add(child.getState());
                    queue.add(child);
                }
            }
        }

        System.out.println("No se encontró solución.");
    }

    // Imprime la secuencia de pasos desde la raíz hasta la solución
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
            System.out.println("Paso " + step + ": [" + n.getState() + "]");
            step++;
        }
    }
}