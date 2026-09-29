public class App {
    public static void main(String[] args) {
        String initialState = "7621 3458"; 
        String goalState = "12345678 "; 

        SearchTree searchTree = new SearchTree(initialState, goalState);
        searchTree.breadthFirstSearch();
        System.out.println("");
    }
}