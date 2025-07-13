package lru;

import java.nio.channels.Channel;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

class Node{
    int key,val;
    Node prev,next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
class DoubleLinkedList{
    private Node head;
    private Node tail;
    public DoubleLinkedList() {
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    public void addFirst(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    public int delete(Node node){

        node.next.prev = node.prev;
        node.prev.next = node.next;
        return node.key;
    }
    public int deleteLast(Node node){
        if (head.next == tail) {
            return -1;
        }
        return delete(tail.prev);
    }
}
/**
 * 实现LRU缓存
 */
public class LRUCache {
    private Map<Integer,Node> map;
    private DoubleLinkedList cache;
    private int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        this.capacity = capacity;
    }
    public void put(int key,int val){
        Node newNode = new Node(key, val);
        if (map.containsKey(key)){
            cache.delete(map.get(key));
            cache.addFirst(newNode);
            map.put(key,newNode);
        }else {
            if (map.size() == capacity) {
                int k = cache.deleteLast(newNode);
                map.remove(k);
                cache.addFirst(newNode);
                map.put(key,newNode);
            }
        }
    }
    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        put(key,val);
        return val;
    }

    public static void main(String[] args) {
        //LRUCache lruCache = new LRUCache(10);
        //lruCache.put(2,2);
        String s = "A man, a plan, a canal: Panama";
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
    }
    public static int minimizedStringLength(String s) {
        int mask = 0;
        for (char c:s.toCharArray()){
            mask |=1<<(c-'0');
        }
        return Integer.bitCount(mask);
    }
    public static int lengthOfLastWord(String s) {
        String str = s.trim();
        int len = 0;
        for (int i = str.length()-1; i >=0; i--) {
            if(str.charAt(i)==' '){
                break;
            }
            len++;
        }
        return len;
    }
}

