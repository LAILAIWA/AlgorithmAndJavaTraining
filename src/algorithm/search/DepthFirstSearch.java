package algorithm.search;

import algorithm.graph.Graph;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {

    /**
     * 顶点是否调用过dfs()
     */
    private boolean[] marked;

    /**
     * 计数
     */
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
