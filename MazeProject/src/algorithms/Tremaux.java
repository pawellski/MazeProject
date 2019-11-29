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
    //down = 2;
    //up = -2;
    private Maze maze;
    private Point entrance;
    private Point exit;
    private Point actualPosition;
    private ArrayList<Integer> randomDirection;
    private Cell[][] mazeToSolve;
    private int direction;

    public Tremaux(Maze maze) {
        this.maze = maze;
        searchEntrance();
        searchExit();
        actualPosition = new Point(entrance.getI(), entrance.getJ());
        mazeToSolve = new Cell[2 * maze.getHeight() + 1][2 * maze.getWidth() + 1];
        copyMaze();
        direction = 0;
    }

    public void solve() {
        //while (actualPosition.getI() != exit.getI() && actualPosition.getJ() != exit.getJ()) {
            int option = chooseDirection();
            if (option == -1) {
                System.out.println("Ide w lewo");

            } else if (option == -2) {
                System.out.println("Ide w gore");

            } else if (option == 1) {
                System.out.println("Ide w prawo");
            } else if (option == 2) {
                System.out.println("Ide w dol");
            } else {
                //blad?
                System.out.println("Nie wiem gdzie ide");
            }
            for(int x : randomDirection){
                System.out.println(x);
            }
        //}
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

    public void printEntranceAndExit() {
        System.out.println(entrance.getI() + " " + entrance.getJ());
        System.out.println(exit.getI() + " " + exit.getJ());
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
                mazeToSolve[i][j] = maze.getCell(i, j);
            }
        }
    }

    private int chooseDirection() {
        randomDirection = new ArrayList<>();
        if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIELD && direction != -1) {
            randomDirection.add(-1);
        }
        if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIELD && direction != -2 && actualPosition.getI() - 1 != 0) {
            randomDirection.add(-2);
        }
        if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIELD && direction != 1) {
            randomDirection.add(1);
        }
        if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIELD && direction != 2) {
            randomDirection.add(2);
        }
        if (randomDirection.size() > 0) {
            return whereTurn(randomDirection);
        } else {
            //try to backward            
            if (checkEnterForBackward(direction)) {
                return direction * (-1);
            } else {
                // search the best option
                randomDirection = new ArrayList<>();
                if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIRST) {
                    randomDirection.add(-1);
                } else if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIRST) {
                    randomDirection.add(-2);
                } else if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIRST) {
                    randomDirection.add(1);
                } else if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIRST) {
                    randomDirection.add(2);
                }
                return whereTurn(randomDirection);
            }
        }
    }

    private boolean checkEnterForBackward(int backward) {
        if (backward == -1) {
            if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIRST) {
                return true;
            } else {
                return false;
            }
        } else if (backward == -2) {
            if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIRST) {
                return true;
            } else {
                return false;
            }
        } else if (backward == 1) {
            if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIRST) {
                return true;
            } else {
                return false;
            }

        } else {
            if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIRST) {
                return true;
            } else {
                return false;
            }
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
