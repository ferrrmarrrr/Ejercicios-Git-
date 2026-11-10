public class App {
    public static void main(String[] args) {
        String initialState = "7621 3458"; 
        String goalState = "12345678 "; 

        SearchTree searchTree = new SearchTree(initialState, goalState);

        // 2. Ejecutar DLS con límite fijado (ej. 20)
        searchTree.depthLimitedSearch(20, true);
        // 3. Ejecutar IDS (Iterative Deepening Search)
        searchTree.iterativeDeepeningSearch(50);
    }
}