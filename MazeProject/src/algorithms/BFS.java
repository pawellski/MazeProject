/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
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
    private LinkedList<Node> way;

    public BFS(Maze maze) {
        this.nodes = new ArrayList<Node>();
        this.maze = maze;
        this.way = new LinkedList<Node>();
    }

    public void createAndSolveMaze() {
        createGraph();
        solveMaze();
    }

    public void createGraph() {
        readNode();
        createEdges();
        searchEntrance();
        searchExit();
    }

    public void solveMaze() {
        searchWay();
        showWay();
    }

    public void searchWay() {
        way.add(entrance);
        while (way.getFirst() != exit) {
            if (way.getFirst().getLeft() != null && way.getFirst().getLeft() != way.getFirst().getPredecessor()) {
                way.add(way.getFirst().getLeft());
                way.getLast().setPredecessor(way.getFirst());
            }
            if (way.getFirst().getTop() != null && way.getFirst().getTop() != way.getFirst().getPredecessor()) {
                way.add(way.getFirst().getTop());
                way.getLast().setPredecessor(way.getFirst());
            }
            if (way.getFirst().getRight() != null && way.getFirst().getRight() != way.getFirst().getPredecessor()) {
                way.add(way.getFirst().getRight());
                way.getLast().setPredecessor(way.getFirst());
            }
            if (way.getFirst().getBottom() != null && way.getFirst().getBottom() != way.getFirst().getPredecessor()) {
                way.add(way.getFirst().getBottom());
                way.getLast().setPredecessor(way.getFirst());
            }
            way.removeFirst();
        }
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

        //left
        if (maze.getCell(i, j - 1) == Cell.FIELD) {
            nodes.get(x).setLeft(nodes.get(x - 1));
        }
        //top
        if (maze.getCell(i - 1, j) == Cell.FIELD) {
            //top
            nodes.get(x).setTop(nodes.get(x - maze.getWidth()));
        }
        //right
        if (maze.getCell(i, j + 1) == Cell.FIELD) {
            nodes.get(x).setRight(nodes.get(x + 1));
        }
        //bottom
        if (maze.getCell(i + 1, j) == Cell.FIELD) {
            nodes.get(x).setBottom(nodes.get(x + maze.getWidth()));
        }
    }

    private void searchEntrance() {
        for (int x = 0; x < maze.getWidth(); x++) {
            int j = 1 + 2 * (x % maze.getWidth());
            if (maze.getCell(0, j) == Cell.ENTRANCE) {
                entrance = nodes.get(x);
            }
        }
    }

    private void searchExit() {
        for (int x = (maze.getHeight() - 1) * maze.getWidth(); x < nodes.size(); x++) {
            int j = 1 + 2 * (x % maze.getWidth());
            if (maze.getCell(2 * maze.getHeight(), j) == Cell.EXIT) {
                exit = nodes.get(x);
            }
        }
    }

    public void showWay() {
        Node temporary = exit;
        while (temporary != entrance) {
            System.out.print(temporary.getID() + " -> ");
            temporary = temporary.getPredecessor();
        }
        System.out.println(entrance.getID());
    }
}
