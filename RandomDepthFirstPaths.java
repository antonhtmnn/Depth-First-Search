import java.util.*;

public class RandomDepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    private int[] distTo;      // distTo[v] = number of edges s-v path
    public Queue<Integer> preorder;   // vertices in preorder
    public Queue<Integer> postorder;  // vertices in postorder

    private int previousNode;
    private int edgesCount;

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     *
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
    }

    public void randomDFS(Graph G) {
        randomDFS(G, s);
    }

    // depth first search from v (setup)
    private void randomDFS(Graph G, int v) {
        this.distTo = new int[G.V()];
        this.postorder = new LinkedList<Integer>();
        this.preorder = new LinkedList<Integer>();
        recRandomDFS(G, v);
    }

    // depth first search from v (actual recursive method)
    private void recRandomDFS(Graph G, int v) {

        marked[v] = true; // mark node v as visited
        this.preorder.add(v); // add v to preorder
        this.previousNode = v; // set v as previous node
        this.distTo[v] = this.edgesCount; // set number of edges of path s-v

        LinkedList<Integer> adjCopy = new LinkedList<>();
        adjCopy = (LinkedList<Integer>) G.adj(v).clone();
        Collections.shuffle(adjCopy);

        for (int w : adjCopy) { // for all nodes w adjacent to node v (choose random)
            if (!marked[w]) { // if node w is not visited
                this.edgeTo[w] = this.previousNode; // set previous node of w
                this.edgesCount++;
                this.distTo[w] = this.edgesCount; // set number of edges of path s-w
                recRandomDFS(G, w);
                this.previousNode = v; // update previous
                this.edgesCount--;
            }
        }
        this.postorder.add(v); // add v to postorder
    }

    public void randomNonrecursiveDFS(Graph G) {
        // TODO (optional)
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     *
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     *
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     * {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     *                                  <p>
     *                                  This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {

        if (this.hasPathTo(v)) {
            List<Integer> path = new ArrayList<>();
            int tmpNode = v;
            while (tmpNode != this.s) {
                path.add(tmpNode);
                tmpNode = this.edgeTo[tmpNode];
            }
            path.add(this.s);
            return path;
        }
        return null;
    }

    public int[] edge() {
        return edgeTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

}

