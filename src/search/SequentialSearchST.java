package search;

import java.util.Iterator;

/**
 * @program: datastructure
 * @description: 顺序查找-基于无序链表
 * @author: YISHUI
 * @create: 2018-08-11
 */
public class SequentialSearchST<Key extends Comparable<Key>,Value>{
    private Node first;//链表首结点
    private class Node{//定义链表结点
        Key key;
        Value val;
        Node next;
        public Node(Key key,Value val,Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key){//查找给定的键，返回相关联的值
        for(Node x = first;x != null;x = x.next)
            if(key.equals(x.key))
                return x.val;
        return null;//未命中
    }

    public void put(Key key,Value val){
        //查找给定的键，找到则更新，否则在表中新建结点，保证Key唯一
        for(Node x = first;x != null;x = x.next) {
            if (key.equals(x.key)) {//命中，更新
                x.val = val;
                return;
            }
        }
        first = new Node(key,val,first);//未命中，新建结点
    }

    public int size(){//返回链表大小
        int count = 0;
        for(Node x = first;x != null;x = x.next)
            count++;
        return count;
    }

    public Node delete(Key key){//删除链表结点
        Node lastNode = null;//上个结点
        Node node = null;//删除结点
        for(Node x = first;x != null;x = x.next){
            if(key.equals(x.key)){//命中
                node = x;
                if(lastNode != null){//若上个结点不为空，连接上个结点和后个结点
                    lastNode.next = node.next;
                }else {//若上个结点为空，后个结点为首节点
                    first = x.next;
                }
                return node;
            }
            lastNode = x;
        }
        return null;//未找到Key
    }

    @SuppressWarnings("unchecked")
    public Key[] keys(){//返回链表的键数组
        Key[] keys = (Key[])new Comparable[size()];
        int count = 0;
        for(Node x = first;x != null;x = x.next)
            keys[count] = x.key;
        return keys;
    }
}
