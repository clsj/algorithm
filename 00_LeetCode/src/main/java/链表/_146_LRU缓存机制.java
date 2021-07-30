package 链表;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 * https://www.jianshu.com/p/b1ab4a170c3c
 */
public class _146_LRU缓存机制 {

    public static class LRUCache{
        private LinkedHashMap<Integer, Integer> map;
        private final int capacity;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    // 使用hashMap + 双向链表
//    public static class LRUCache{
//
//        public static void main(String[] args) {
//            LRUCache cache = new LRUCache(2);
//            cache.put(1, 1);
//            cache.put(2, 2);
//            System.out.println(cache.get(1));
//            cache.put(3, 3);
//            System.out.println(cache.get(2));
//            cache.put(4, 4);
//            System.out.println(cache.get(1));
//            System.out.println(cache.get(3));
//            System.out.println(cache.get(4));
//        }
//
//
//        private final int capacity;
//
//        // 头结点 存储老的数据
//        private final Node head;
//
//        // 尾结点 存储刚刚更新的数据
//        private final Node tail;
//
//        private final HashMap<Integer, Node> map;
//
//        public LRUCache(int capacity) {
//            this.capacity = capacity;
//            map = new HashMap<>(capacity);
//            head = new Node(-1, -1);
//            tail = new Node(-2, -2);
//
//            head.prev = null;
//            head.next = tail;
//
//            tail.prev = head;
//            tail.next = null;
//        }
//
//        public int get(int key) {
//            if (map.containsKey(key)) {
//                Node node = map.get(key);
//                int value = node.value;
//                // 将node移动到尾结点
//                moveNodeTail(node);
//                return value;
//            }
//            return -1;
//        }
//
//        public void put(int key, int value) {
//            // 查看map是否包含key
//            if (map.containsKey(key)) {
//                Node node = map.get(key);
//                moveNodeTail(node);
//                node.value = value;
//            }else {
//                Node addNode = new Node(key, value);
//                if (map.size() >= capacity) {
//                    // 删除首节点
//                    Node removeNode = removeNodeFirst();
//                    if (removeNode != null) {
//                        map.remove(removeNode.key);
//                    }
//                }
//                map.put(key, addNode);
//                addNodeTail(addNode);
//            }
//        }
//
//        private static class Node{
//            int key;
//            int value;
//            Node prev;
//            Node next;
//
//            public Node() {
//            }
//
//            public Node(int key, int value) {
//                this.value = value;
//                this.key = key;
//            }
//        }
//
//        private void moveNodeTail(Node node) {
//            Node prev = tail.prev;
//
//            if (node == prev) return;
//
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//
//            prev.next = node;
//            node.prev = prev;
//
//            node.next = tail;
//            tail.prev = node;
//        }
//
//        // 删除首节点
//        private Node removeNodeFirst() {
//
//            Node node = head.next;
//            if (node == tail) {
//                return null;
//            }
//
//            Node next = node.next;
//
//            head.next = next;
//            next.prev = head;
//            return node;
//        }
//
//
//        // 将Node添加移动到链表末端
//        private void addNodeTail(Node node) {
//            Node prev = tail.prev;
//            node.next = tail;
//            tail.prev = node;
//            prev.next = node;
//            node.prev = prev;
//        }
//
//    }

}
