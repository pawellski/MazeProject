/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.List;
import maze.Cell;
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
        createEdges();
        chooseEntrance();
        chooseExit();
        System.out.println("Entrance: " + entrance.getID());
        System.out.println("Exit: " + exit.getID());
    }

    private void readNode() {
        for (int i = 1; i < 2 * maze.getHeight(); i += 2) {
            for (int j = 1; j < 2 * maze.getWidth(); j += 2) {
                nodes.add(new Node(index++));
            }
        }
    }

    private void createEdges() {
        for (int i = 0; i < nodes.size(); i++) {
            createEdgesForNode(i);
        }
    }

    private void createEdgesForNode(int x) {
        int i = 1 + 2 * (x / maze.getWidth());
        int j = 1 + 2 * (x % maze.getWidth());
        
        if (maze.getCell(i, j - 1) == Cell.FIELD) {
            //left
            nodes.get(x).setLeft(nodes.get(x - 1));
        }
        if (maze.getCell(i - 1, j) == Cell.FIELD) {
            //top
            nodes.get(x).setTop(nodes.get(x - maze.getWidth()));
        }
        if (maze.getCell(i, j + 1) == Cell.FIELD) {
            //right
            nodes.get(x).setRight(nodes.get(x + 1));
        }
        if (maze.getCell(i + 1, j) == Cell.FIELD) {
            //bottom
            nodes.get(x).setBottom(nodes.get(x + maze.getWidth()));
        }
    }

    private void chooseEntrance() {
        for (int x = 0; x < maze.getWidth(); x++) {
            int j = 1 + 2 * (x % maze.getWidth());
            if (maze.getCell(0, j) == Cell.ENTRANCE) {
                entrance = nodes.get(x);
            }
        }
    }

    private void chooseExit() {
        for (int x = (maze.getHeight() - 1) * maze.getWidth(); x < nodes.size(); x++) {
            int j = 1 + 2 * (x % maze.getWidth());
            if (maze.getCell(2 * maze.getHeight(), j) == Cell.EXIT) {
                exit = nodes.get(x);
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
