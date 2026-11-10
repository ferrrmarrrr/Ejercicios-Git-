import java.util.HashMap;
import java.util.Map;
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


    // ==========================================
    // 1. Búsqueda en Profundidad Limitada (DLS)
    // ==========================================
    public Node depthLimitedSearch(int limit, boolean verbose) {
        long startTime = System.nanoTime();
        Stack<Node> stack = new Stack<>();
        
        // Mapeo estado -> menor profundidad alcanzada (evita ciclos y bloqueos)
        Map<String, Integer> visited = new HashMap<>();

        stack.push(root);
        visited.put(root.getState(), 0);

        int evaluatedNodes = 0;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            evaluatedNodes++;

            if (currentNode.getState().equals(goalState)) {
                if (verbose) {
                    long endTime = System.nanoTime();
                    double durationInSeconds = (endTime - startTime) / 1e9;

                    System.out.println("=======================================");
                    System.out.println("--- Búsqueda Limitada en Profundidad (DLS - Límite: " + limit + ") ---");
                    System.out.println("Nodos evaluados (desplegados): " + evaluatedNodes);
                    System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                    System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                    System.out.println("\n====== Pasos para la solución =======");
                    printPath(currentNode);
                }
                return currentNode;
            }

            // Solo expande si no ha superado el límite de profundidad
            if (currentNode.getDepth() < limit) {
                for (Node child : currentNode.getChildren()) {
                    int childDepth = child.getDepth();
                    if (!visited.containsKey(child.getState()) || visited.get(child.getState()) > childDepth) {
                        visited.put(child.getState(), childDepth);
                        stack.push(child);
                    }
                }
            }
        }

        if (verbose) {
            System.out.println("=======================================");
            System.out.println("--- Búsqueda Limitada en Profundidad (DLS - Límite: " + limit + ") ---");
            System.out.println("No se encontró solución dentro del límite de profundidad " + limit + ".");
            System.out.println("Nodos evaluados: " + evaluatedNodes);
        }
        return null;
    }

    // ==========================================
    // 2. Búsqueda Iterativa en Profundidad (IDS)
    // ==========================================
    public void iterativeDeepeningSearch(int maxLimit) {
        long startTime = System.nanoTime();
        int totalEvaluatedNodes = 0;

        System.out.println("=======================================");
        System.out.println("--- Búsqueda Iterativa en Profundidad (IDS) ---");

        for (int limit = 0; limit <= maxLimit; limit++) {
            Stack<Node> stack = new Stack<>();
            Map<String, Integer> visited = new HashMap<>();

            stack.push(root);
            visited.put(root.getState(), 0);

            while (!stack.isEmpty()) {
                Node currentNode = stack.pop();
                totalEvaluatedNodes++;

                if (currentNode.getState().equals(goalState)) {
                    long endTime = System.nanoTime();
                    double durationInSeconds = (endTime - startTime) / 1e9;

                    System.out.println("Solución encontrada al iterar hasta la profundidad: " + limit);
                    System.out.println("Nodos evaluados (totales entre iteraciones): " + totalEvaluatedNodes);
                    System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                    System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                    System.out.println("\n====== Pasos para la solución =======");
                    printPath(currentNode);
                    return;
                }

                if (currentNode.getDepth() < limit) {
                    for (Node child : currentNode.getChildren()) {
                        int childDepth = child.getDepth();
                        if (!visited.containsKey(child.getState()) || visited.get(child.getState()) > childDepth) {
                            visited.put(child.getState(), childDepth);
                            stack.push(child);
                        }
                    }
                }
            }
        }

        System.out.println("No se encontró solución tras alcanzar el límite máximo de " + maxLimit + ".");
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