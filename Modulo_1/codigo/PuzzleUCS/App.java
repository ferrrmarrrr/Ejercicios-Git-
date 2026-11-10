import src.SearchTree;

public class App {
    public static void main(String[] args) {
        String initialState = "7621 3458"; 
        String goalState = "12345678 "; 

        System.out.println("========= Búsqueda Primero en Anchura (BFS) =========");
        SearchTree bfsTree = new SearchTree(initialState, goalState);
        bfsTree.breadthFirstSearch();

        System.out.println("\n========= Búsqueda por Costo Uniforme (UCS) =========");
        SearchTree ucsTree = new SearchTree(initialState, goalState);
        ucsTree.uniformCostSearch();
        
    }
}