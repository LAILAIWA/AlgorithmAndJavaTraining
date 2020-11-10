package algorithm.search;

import java.base.a1abstract.MyQueue;

/**
 * @program: datastructure
 * @description: 二分查找-基于有序数组
 * @author: 来建培
 * @create: 2018-08-13
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){//构造器中初始化数组
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public Value get(Key key){//有Key则返回val，无则返回null，判断key是否相等，是根据其compareTo方法
        if(isEmpty()) return null;
        //二分查找获取key下标，未查到返回0或N，查到
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int rank(Key key){//二分查找，未查询到返回0或N，查到则返回下标
        //使用有序数组是为了根据索引减少每次查找所需要的比较次数
        int lo = 0,hi = N - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        //查找键，找到则更新值，找不到新增元素
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        //扩展数组
        for(int j = N;j > i;i--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i]= val;
        N++;
    }

    public Value delete(Key key){//删除Key以及对应val
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0){//命中
            //分别从键数组和值数组中删除
            Value value = vals[i];
            //覆盖前个元素
            for(int n = i;n < N-1;n++){
                keys[n] = keys[n+1];
                vals[n] = vals[n+1];
            }
            //复制数组
            Key[] newKeys =(Key[]) new Comparable[N-1];
            Value[] newVals = (Value[]) new Object[N-1];
            for(int m = 0;m < N-1;m++){
                newKeys[m] = keys[m];
                newVals[m] = vals[m];
            }
            keys = newKeys;
            vals = newVals;
            return value;
        }
        else
            return null;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int index){
        return keys[index];
    }

    //大于等于key的最小键
    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    //小于等于key的最大键
    public Key floor(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Iterable<Key> keys(Key lo,Key hi){
        MyQueue<Key> q = new MyQueue<Key>();
        for(int i = rank(lo);i < rank(hi);i++)
            q.enqueue(keys[i]);
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public boolean contains(Key key){
        if(isEmpty()) return false;
        //二分查找获取key下标，未查到返回0或N，查到
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        BinarySearchST<Integer,String> st = new BinarySearchST<>(6);
        st.put(2,"Child");
        st.put(3,"b");
        st.put(4,"c");
        st.put(5,"d");
        st.put(6,"e");
        st.put(7,"f");
        st.get(1);
        st.get(8);
        st.get(5);
        st.delete(1);
        st.delete(8);
        st.delete(5);
    }
}
