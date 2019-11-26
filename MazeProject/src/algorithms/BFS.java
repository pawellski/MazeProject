/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.List;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class BFS {

    private Node entrance;
    private Node exit;
    private List<Node> nodes;
    private static int index;
    private Maze maze;

    public BFS(Maze maze) {
        nodes = new ArrayList<Node>();
        this.maze = maze;
    }

    public void createGraph() {
        readNode();
    }

    private void readNode() {
        for (int i = 1; i < 2 * maze.getHeight(); i += 2) {
            for (int j = 1; j < 2 * maze.getWidth(); j += 2) {
                nodes.add(new Node(index++));
            }
        }
    }

    public void printer() {
        for (Node x : nodes) {
            System.out.println(x.getID());
        }
        System.out.println("Index to" + index);
    }

}
