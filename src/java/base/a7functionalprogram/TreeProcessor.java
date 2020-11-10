package java.base.a7functionalprogram;

public class TreeProcessor {
    /**
     * 查找树中给定字符串对应键值的整型value
     * @param k 给定字符串
     * @param defaultval 默认value
     * @param t 二叉查找树
     * @return 对应整型value
     */
    public static int lookup(String k, int defaultval, Tree t){
        if (t == null) return defaultval;
        if (k.equals(t.getKey())) return t.getVal();
        return lookup(k, defaultval, k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    /**
     * 更新键对应值
     * @param k 给定字符串
     * @param newval 新值
     * @param t 二叉查找树
     * @return 更新后的二叉查找树
     */
    public static Tree update(String k, int newval, Tree t){
        if (t == null){
            t = new Tree(k, newval, null, null);
        }
        else if (k.equals(t.getKey())) t.setVal(newval);
        else if ( k.compareTo(t.getKey()) < 0) t.setLeft(update(k, newval, t.getLeft()));
        else t.setRight(update(k, newval, t.getRight()));
        return t;
    }

    /**
     * 函数式的更新键对应值
     * @param k 给定字符串
     * @param newval 新值
     * @param t 二叉查找树
     * @return 新的二叉查找树
     */
    public static Tree fupdate(String k, int newval, Tree t){
        return (t == null) ?
                new Tree(k, newval, null, null) :
                k.equals(t.getKey()) ?
                        new Tree(k, newval, t.getLeft(), t.getRight()) :
                        k.compareTo(t.getKey()) < 0 ?
                                new Tree(t.getKey(), t.getVal(), fupdate(k, newval, t.getLeft()), t.getRight()) :
                                new Tree(t.getKey(), t.getVal(), t.getLeft(), fupdate(k, newval, t.getRight()));
    }
}
