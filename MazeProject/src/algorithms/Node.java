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
    private int ID;
    
    public Node(int id){
        this.ID = id;
    }

    public int getID() {
        return ID;
    }
    
}
