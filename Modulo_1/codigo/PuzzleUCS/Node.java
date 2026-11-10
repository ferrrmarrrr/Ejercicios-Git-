
import java.util.ArrayList;
import java.util.List;

import src.Node;

public class Node implements Comparable<Node> {
    private String state;
    private Node parent;
    private int depth; // En 8-puzzle, depth equivale al costo acumulado g(n)

    public Node(String state, Node parent) {
        this.state = state;
        this.parent = parent;
        this.depth = (parent == null) ? 0 : parent.getDepth() + 1;
    }

    public String getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    // Compara nodos según su costo acumulado g(n) para la PriorityQueue de UCS
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.depth, o.getDepth());
    }

    // Genera los nodos hijos moviendo el espacio en blanco (" ")
    public List<Node> getChildren() {
        List<Node> children = new ArrayList<>();
        int spaceIndex = state.indexOf(' ');

        // Mapeo de posiciones adyacentes en una matriz 3x3
        int[][] moves = {
            {1, 3},       // 0
            {0, 2, 4},    // 1
            {1, 5},       // 2
            {0, 4, 6},    // 3
            {1, 3, 5, 7}, // 4
            {2, 4, 8},    // 5
            {3, 7},       // 6
            {4, 6, 8},    // 7
            {5, 7}        // 8
        };

        for (int nextPos : moves[spaceIndex]) {
            char[] stateArray = state.toCharArray();
            // Intercambiar el espacio con el número contiguo
            stateArray[spaceIndex] = stateArray[nextPos];
            stateArray[nextPos] = ' ';

            String childState = new String(stateArray);
            children.add(new Node(childState, this));
        }

        return children;
    }
}