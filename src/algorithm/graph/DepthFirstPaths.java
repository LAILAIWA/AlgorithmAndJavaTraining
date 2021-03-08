package algorithm.graph;

import java.util.Stack;

/**
 * 路径
 */
public class DepthFirstPaths {

    /**
     * 在此顶点上是否调用过 dfs()
     */
    private boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    private final int s;

    /**
     * 在 G 中找出所有起点为 s 的路径
     */
    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * 是否存在从 s 到 v 的路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * s 到 v 的路径，如果不存在则返回 null
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
