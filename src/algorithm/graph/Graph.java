package algorithm.graph;

import edu.princeton.cs.algs4.In;

/**
 *
 */
public class Graph {
    private final int V; // 顶点数目
    private int E; // 边的数目
    private Bag<Integer>[] adj; // 邻接表：数组+链表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // 创建邻接表
        for (int v = 0; v < V; v++) // 将所有链表初始化为空
            adj[v] = new Bag<Integer>();
    }

    public Graph(In in) {
        this(in.readInt()); // 读取V并将图初始化
        int E = in.readInt(); // 读取E
        for (int i = 0; i < E; i++) { // 添加一条边
            int v = in.readInt(); // 读取一个顶点
            int w = in.readInt(); // 读取另一个顶点
            addEdge(v, w); // 添加一条连接它们的边
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // 将w添加到v的链表中
        adj[w].add(v); // 将v添加到w的链表中
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
