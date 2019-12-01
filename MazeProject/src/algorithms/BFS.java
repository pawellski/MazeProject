/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    private int index;
    private Maze maze;
    private Queue<Node> way;

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
        while (way.element() != exit) {
            if (way.element().getLeft() != null && way.element().getLeft() != way.element().getPredecessor() && !way.element().getLeft().isSeen()) {
                way.add(way.element().getLeft());
                way.element().getLeft().setPredecessor(way.element());
                way.element().getLeft().setSeen(true);
            }
            if (way.element().getUp()!= null && way.element().getUp()!= way.element().getPredecessor() && !way.element().getUp().isSeen()) {
                way.add(way.element().getUp());
                way.element().getUp().setPredecessor(way.element());
                //
                way.element().getUp().setSeen(true);
            }
            if (way.element().getRight() != null && way.element().getRight() != way.element().getPredecessor() && !way.element().getRight().isSeen()) {
                way.add(way.element().getRight());
                way.element().getRight().setPredecessor(way.element());
                //
                way.element().getRight().setSeen(true);
            }
            if (way.element().getDown()!= null && way.element().getDown()!= way.element().getPredecessor() && !way.element().getDown().isSeen()) {
                way.add(way.element().getDown());
                way.element().getDown().setPredecessor(way.element());
                //
                way.element().getDown().setSeen(true);
            }
            way.remove();
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
        //up
        if (maze.getCell(i - 1, j) == Cell.FIELD) {
            //top
            nodes.get(x).setUp(nodes.get(x - maze.getWidth()));
        }
        //right
        if (maze.getCell(i, j + 1) == Cell.FIELD) {
            nodes.get(x).setRight(nodes.get(x + 1));
        }
        //down
        if (maze.getCell(i + 1, j) == Cell.FIELD) {
            nodes.get(x).setDown(nodes.get(x + maze.getWidth()));
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
