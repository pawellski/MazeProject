/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author Paweł
 */
public class Node {

    private Node top;
    private Node bottom;
    private Node left;
    private Node right;
    private Node predecessor;
    private int ID;

    public Node(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public Node getTop() {
        return top;
    }

    public Node getBottom() {
        return bottom;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

}
