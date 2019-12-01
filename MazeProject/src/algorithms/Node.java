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

    private Node up;
    private Node down;
    private Node left;
    private Node right;
    private Node predecessor;
    private int ID;
    private boolean seen;

    public Node(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public Node getUp() {
        return up;
    }

    public Node getDown() {
        return down;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
