package selfwritten.triald;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU - Least Recently Used is a caching strategy that keeps a fixed
 * number of items in the cache and automatically removes the least
 * recently used item when the cache reaches capacity.
 * Operations:
 *  - put: inserts or updates a key-value pair - moves the key to the most recently used position
 *  - get: retrieves the value if present and moves the key to the most recently used position or returns null otherwise
 * When the cache is full, the least recently used item is removed to make room for the new item
 */
public class LRUCacheWithDoubleLinkedList {
    private Node head;
    private Node tail;
    private int size;
    private Map<Integer, Node> pointerMap = new HashMap<>();

    public LRUCacheWithDoubleLinkedList(int size) {
        this.size = size;
        head = null;
        tail = null;
    }

    public void put(int key, int value) {
        var current = new Node(key, value);
        current.setNext(null);
        if (tail!=null){
            tail.setNext(current);
            current.setPrev(tail);
        }
        tail = current;
        if(pointerMap.isEmpty()) {
            head = current;
            current.setPrev(null);
        } else if (pointerMap.size()==size){
            //cache is full - remove the LRU element
            //remove LRU from the pointerMap
            pointerMap.remove(head.getKey());
            head = head.getNext();
            head.setPrev(null);
        }
        pointerMap.put(key, current);
    }

    public Integer get(int key) {
        //1. retrieve the element and return the value if present. if not present, return null
        //2. move the retrieved element to the front/MRU position
        var element = pointerMap.get(key);
        if (element==null){
            return null;
        }

        var prev = element.getPrev();
        var next = element.getNext();
        if (prev!=null){
            prev.setNext(next);
        }
        tail.setNext(element);
        element.setNext(null);
        element.setPrev(tail);

        //update pointers
        tail = element;
        if (head==element){
            head = next;
            next.setPrev(null);
        }

        pointerMap.put(key, element);
        return element.getData();
    }

    private class Node{
        private Node prev;
        private int key;
        private int data;
        private Node next;

        public Node(int key, int data) {
            this.prev = null;
            this.key = key;
            this.data = data;
            this.next = null;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
