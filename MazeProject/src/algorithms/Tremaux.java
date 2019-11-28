/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayList;
import java.util.Random;
import maze.Cell;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class Tremaux {

    //right = 1;
    //left = -1;
    //up = -2;
    //down = 2;
    
    private Maze maze;
    private int[][] states;
    private Point entrance;
    private Point exit;
    private Point actualPosition;
    private ArrayList<Integer> random;
    private Cell[][] mazeToSlolve;
    private int direction;

    public Tremaux(Maze maze) {
        this.maze = maze;
        searchEntrance();
        searchExit();
        actualPosition = new Point(entrance.getI(), entrance.getJ());
        states = new int[maze.getHeight()][maze.getWidth()];
        mazeToSlolve = new Cell[2 * maze.getHeight() + 1][2 * maze.getWidth() + 1];
        direction = 0;
    }

    public void solve() {
        copyMaze();
        while (actualPosition.getI() == exit.getI() && actualPosition.getJ() == exit.getJ()) {
            int option = chooseDirection();
            if (option == -1) {
                
            } else if (option == -2) {

            } else if (option == 1) {

            } else if (option == 2) {

            } else {
                //blad?
            }
        }
    }

    private void searchEntrance() {
        for (int i = 1; i < 2 * maze.getWidth(); i += 2) {
            if (maze.getCell(0, i) == Cell.ENTRANCE) {
                entrance = new Point(1, i);
            }
        }
    }

    private void searchExit() {
        for (int i = 1; i < 2 * maze.getWidth(); i += 2) {
            if (maze.getCell(2 * maze.getHeight(), i) == Cell.EXIT) {
                exit = new Point(2 * maze.getWidth() - 1, i);
            }
        }
    }

    public void printStates() {
        for (int x = 0; x < states.length; x++) {
            for (int y = 0; y < states[0].length; y++) {
                System.out.print(states[x][y] + " ");
            }
            System.out.println();
        }
        searchEntrance();
        searchExit();
        System.out.println(entrance.getI() + " " + entrance.getJ());
        System.out.println(exit.getI() + " " + exit.getJ());
        copyMaze();
    }

    private int whereTurn(ArrayList<Integer> table) {
        int length = table.size();
        Random random = new Random();
        int where = random.nextInt(length);
        return table.get(where);
    }

    private void copyMaze() {
        for (int i = 0; i < 2 * maze.getHeight() + 1; i++) {
            for (int j = 0; j < 2 * maze.getWidth() + 1; j++) {
                mazeToSlolve[i][j] = maze.getCell(i, j);
            }
        }
    }

    private int chooseDirection() {
        random = new ArrayList<Integer>();
        if (mazeToSlolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIELD) {
            random.add(-1);
        }
        if (mazeToSlolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIELD && actualPosition.getI() - 1 != 0) {
            random.add(-2);
        }
        if (mazeToSlolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIELD) {
            random.add(1);
        }
        if (mazeToSlolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIELD) {
            random.add(2);
        }
        if (random.size() > 0) {
            return whereTurn(random);
        } else {
            //backward
            return direction * (-1);
        }
    }

    class Point {

        private int i;
        private int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

    }
}
