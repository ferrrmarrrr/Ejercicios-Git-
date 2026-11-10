
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Stack;

import src.Node;

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

    // BÚSQUEDA PRIMERO EN ANCHURA (BFS)
    public void breadthFirstSearch() {
        long startTime = System.nanoTime();

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        visited.add(root.getState());

        int evaluatedNodes = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            evaluatedNodes++;

            if (currentNode.getState().equals(goalState)) {
                long endTime = System.nanoTime();
                double durationInSeconds = (endTime - startTime) / 1e9;

                System.out.println("=== ¡SOLUCIÓN ENCONTRADA (BFS)! ===");
                System.out.println("Nodos evaluados: " + evaluatedNodes);
                System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                System.out.println("Costo total de la solución g(n): " + currentNode.getDepth());
                System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                System.out.println("\n--- Pasos para la solución ---");
                printPath(currentNode);
                return;
            }

            for (Node child : currentNode.getChildren()) {
                if (!visited.contains(child.getState())) {
                    visited.add(child.getState());
                    queue.add(child);
                }
            }
        }

        System.out.println("No se encontró solución con BFS.");
    }

    // BÚSQUEDA POR COSTO UNIFORME (UCS)
    public void uniformCostSearch() {
        long startTime = System.nanoTime();

        // Cola de prioridad ordenada por menor costo acumulado g(n)
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        // Guarda el menor costo con el que hemos llegado a cada estado
        Map<String, Integer> costSoFar = new HashMap<>();

        priorityQueue.add(root);
        costSoFar.put(root.getState(), root.getDepth());

        int evaluatedNodes = 0;

        while (!priorityQueue.isEmpty()) {
            // Se extrae el nodo con el menor costo g(n)
            Node currentNode = priorityQueue.poll();
            evaluatedNodes++;

            // Verificación de objetivo al extraer el nodo
            if (currentNode.getState().equals(goalState)) {
                long endTime = System.nanoTime();
                double durationInSeconds = (endTime - startTime) / 1e9;

                System.out.println("=== ¡SOLUCIÓN ENCONTRADA (UCS)! ===");
                System.out.println("Nodos evaluados: " + evaluatedNodes);
                System.out.println("Profundidad de la solución: " + currentNode.getDepth());
                System.out.println("Costo total óptimo g(n): " + currentNode.getDepth());
                System.out.printf("Tiempo transcurrido: %.6f segundos%n", durationInSeconds);
                System.out.println("\n--- Pasos para la solución ---");
                printPath(currentNode);
                return;
            }

            for (Node child : currentNode.getChildren()) {
                int newCost = child.getDepth(); // Costo acumulado g(n)

                // Si es un estado nuevo o encontramos un camino con menor costo
                if (!costSoFar.containsKey(child.getState()) || newCost < costSoFar.get(child.getState())) {
                    costSoFar.put(child.getState(), newCost);
                    priorityQueue.add(child);
                }
            }
        }

        System.out.println("No se encontró solución con UCS.");
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
            System.out.println("Paso " + step + " (Costo acumulado g(n) = " + n.getDepth() + "): [" + n.getState() + "]");
            step++;
        }
    }

    public void depthFirstSearch(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'depthFirstSearch'");
    }

    public void depthLimitedSearch(int i, boolean b) {
        throw new UnsupportedOperationException("Unimplemented method 'depthLimitedSearch'");
    }

    public void iterativeDeepeningSearch(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'iterativeDeepeningSearch'");
    }
}