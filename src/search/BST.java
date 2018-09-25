package search;

import abstractClass.MyQueue;

import java.util.Queue;

/**
 * @program: datastructure
 * @description: 基于二叉查找树的符号表
 * @author: 来建培
 * @create: 2018-09-01
 */
public class BST<Key extends Comparable<Key>,Value> {
    //每个结点含有：一个键值对，左右链接，一个结点计数器。左链接所有结点Key小于该结点Key，右链接所有结点Key大于该结点Key
    //size(x) = size(x.left) + size(x.right) + 1
    private Node root; //二叉查找树的根节点
    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N; //以该结点为根的子树的结点总数

        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    public int size(){ return size(root); }

    private int size(Node node){
        if(node == null) return 0;
        else return node.N;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        //在以x为根节点的子树中查找并返回Key所对应的值
        //若找不到则返回null
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left,key);
        else if(cmp > 0) return get(x.right,key);
        else return x.value;
    }

    public void put(Key key,Value value){
        //查找Key，找到则更新值，否则为它创建一个新的结点
        root = put(root,key,value);
    }

    private Node put(Node x,Key key,Value value){
        //如果Key存在于以x为根节点的子树中则更新它的值
        //否则将以key和val为键值对的新结点插入到该子树中
        if(x == null) return new Node(key,value,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left,key,value);
        else if(cmp > 0) x.right = put(x.right,key,value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key){//向下取整
        Node x = floor(root,key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x,Key key){//向下取整
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        else if (cmp < 0) return floor(x.left,key);
        Node t = floor(x.right,key);
        if(t != null) return t;
        else return null;
    }

    public Key ceiling(Key key){//向上取整
        Node x = ceiling(root,key);
        if(x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x,Key key){//向上取整
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right,key);
        Node t = ceiling(x.left,key);
        if(t != null) return t;
        else return null;
    }

    public Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node x,int k){
        //返回排名为k的结点
        if(x == null) return null;
        int t = size(x.left);
        if(t > k) return select(x.left,k);
        else if(t > k) return select(x.right,k-t-1);
        else return x;
    }

    public int rank(Key key){
        return rank(key,root);
    }

    private int rank(Key key,Node x){
        //返回以x为根结点的子树中小于x.key的键的数量
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(key,x.left);
        else if(cmp > 0) return 1 + size(x.left) + rank(key,x.right);
        else return size(x.left);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;//?
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node x){
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;//?
        return x;
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = delete(x.left,key);
        else if(cmp > 0) x.right = delete(x.right,key);
        else{
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);//?
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;//?
        return x;
    }

    public Iterable<Key> keys(){
        //二叉查找树范围查找
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo,Key hi){
        MyQueue<Key> queue = new MyQueue<Key>();
        keys(root,queue,lo,hi);
        return queue;
    }

    private void keys(Node x,MyQueue<Key> queue,Key lo,Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left,queue,lo,hi);
        if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if(cmphi > 0) keys(x.right,queue,lo,hi);
    }
}
