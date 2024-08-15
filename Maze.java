import java.util.*;

/**
 * Class that represents a maze with N*N junctions.
 *
 * @author Vera Röhr
 */
public class Maze {
    private final int N;
    private Graph M;    //Maze
    public int startnode;

    public Maze(int N, int startnode) {

        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M = new Graph(N * N);
        this.startnode = startnode;
        buildMaze();
    }

    public Maze(In in) {
        this.M = new Graph(in);
        this.N = (int) Math.sqrt(M.V());
        this.startnode = 0;
    }


    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        if (v < 0 || v >= this.M.V())
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.M.V() - 1));
        this.M.addEdge(v, w);
    }

    /**
     * Returns true if there is an edge between 'v' and 'w'
     *
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge(int v, int w) {
        if (v >= this.M.V() || w >= this.M.V()) return false;
        if (v == w) return true;
        if (this.M.adj(v).contains(w)) return true; // check all possible nodes for v
        if (this.M.adj(w).contains(v)) return true; // check all possible nodes for w
        return false;
    }

    /**
     * Returns true if there is an edge between 'v' and 'w' in graph G
     *
     * @param G graph
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public static boolean hasEdge(Graph G, int v, int w) {
        if (v >= G.V() || w >= G.V()) return false;
        if (v == w) return true;
        if (G.adj(v).contains(w)) return true; // check all possible nodes for v
        if (G.adj(w).contains(v)) return true; // check all possible nodes for w
        return false;
    }

    /**
     * Builds a grid as a graph.
     *
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {

        Graph graph = new Graph(this.N * this.N);

        int maxValue = (this.N * this.N) - 1;
        for (int i = 0; i < maxValue; i++) {
            if (i + this.N <= maxValue) { // if not the most right column
                graph.addEdge(i, i + this.N); // add edge right horizontal
            }
            if (((i + 1) % this.N) != 0) { // if not the last row
                graph.addEdge(i, i + 1); // add edge down vertical
            }
        }
        return graph;
    }

    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {

        Graph G = this.mazegrid(); // store basic grid (graph) in G
        RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(G, this.startnode);
        rdfp.randomDFS(G); // perform randomDFS on basic grid (graph)
        int[] edgeTo = rdfp.edge();

        for (int i = 1; i < edgeTo.length; i++) { // "rebuild" maze out of edgeTo[]
            this.M.addEdge(i, edgeTo[i]);
        }
    }

    /**
     * Find a path from node v to w
     *
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w) {

        int start = v;
        int end = w;

        DepthFirstPaths dfp = new DepthFirstPaths(this.M, end);
        dfp.nonrecursiveDFS(this.M); // perform dfs
        LinkedList<Integer> path = new LinkedList<>(dfp.pathTo(start)); // store path

        return path;
    }

    /**
     * @return Graph M
     */
    public Graph M() {
        return M;
    }


    public static void main(String[] args) {
        // FOR TESTING

        int n = 50;
        int start = 0;
        Maze maze = new Maze(n, start);

        Graph gridGraph = maze.mazegrid(); // store basic grid (graph) in gridGraph
        //GridGraph g = new GridGraph(gridGraph); // draw gridGraph

        RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(gridGraph, start);
        rdfp.randomDFS(gridGraph);
        int[] edgeTo = rdfp.edge();

        Graph M = new Graph(n * n); // store maze in M
        for (int i = 1; i < edgeTo.length; i++) { // "rebuild maze" out of edgeTo[]
            M.addEdge(i, edgeTo[i]);
        }
        //GridGraph gg = new GridGraph(M); // draw M (maze)

        int begin = 10;
        int end = (n * n) - 1;

        DepthFirstPaths dfp = new DepthFirstPaths(M, end);
        dfp.nonrecursiveDFS(M); // perform dfs
        LinkedList<Integer> path = new LinkedList<>(dfp.pathTo(begin)); // store path

        GridGraph gggg = new GridGraph(M, path); // draw path

        /*if (begin < end) {
            path.push(end);
            int tmpNode = end;
            while (tmpNode != begin) {
                path.push(edgeTo[tmpNode]);
                tmpNode = edgeTo[tmpNode];
            }
        } else if (begin > end) {
            path.add(begin);
            int tmpNode = begin;
            while (tmpNode != end) {
                path.add(edgeTo[tmpNode]);
                tmpNode = edgeTo[tmpNode];
            }
        } else {
            path.push(begin);
        }

        GridGraph ggg = new GridGraph(M, path); // draw*/
    }


}
