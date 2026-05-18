package selfwritten.triald;

import org.w3c.dom.Node;

public class LinkedList {
    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    private Node head;

    public void append(int data) {
        if(head==null){
            head = new Node(null, data);
            return;
        }
        Node node = head;
        var next = node.getNext();
        while(next!=null){
            node = next;
            next = node.getNext();
        }
        node.setNext(new Node(null, data));
    }

    public int size(){
        int count = 0;
        if (head == null) {
            return count;
        }
        Node node = head;
        count++; //count the head
        var next = node.getNext();
        while(next!=null){
            node = next;
            next = node.getNext();
            count++;
        }
        return count;
    }

    public LinkedList reverse() {
       Node prev = null;
       Node current = head;
       Node next;
       while (current!=null) {
           next = current.getNext();
           current.setNext(prev);
           prev = current;
           current = next;
       }
       var rll = new LinkedList();
       rll.setHead(prev);
       return rll;
    }





    private class Node {
        private int data;
        private Node next;

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
