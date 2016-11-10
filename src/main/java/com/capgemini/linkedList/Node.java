package com.capgemini.linkedList;

public class Node {

    protected int data;
    protected Node next;
    protected Node prev;

    public Node() {
        next = null;
        prev = null;
        data = 0;
    }

    public Node(int data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public void setLinkNext(Node n) {
        next = n;
    }

    public void setLinkPrev(Node p) {
        prev = p;
    }

    public Node getLinkNext() {
        return next;
    }

    public Node getLinkPrev() {
        return prev;
    }

    public void setData(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }
}
