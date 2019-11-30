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

    private Maze maze;
    private Point entrance;
    private Point exit;
    private Point actualPosition;
    private ArrayList<Integer> randomDirection;
    private Cell[][] mazeToSolve;
    private int direction;

    //right = 1;
    //left = -1;
    //down = 2;
    //up = -2;
    public Tremaux(Maze maze) {
        this.maze = maze;
        searchEntrance();
        searchExit();
        actualPosition = new Point(entrance.getI(), entrance.getJ());
        mazeToSolve = new Cell[2 * maze.getHeight() + 1][2 * maze.getWidth() + 1];
        copyMaze();
        direction = 0;
    }
    
    public void solveMaze() {
        exploreExit();
        exploreWay();
    }

    private void exploreExit() {
        while (actualPosition.getI() != exit.getI() || actualPosition.getJ() != exit.getJ()) {
            int option = chooseDirection();
            if (option == -1) {
                //System.out.println("Ide w lewo");
                goHorizontal(-2, 1, -1);
                direction = -1;
            } else if (option == -2) {
                //System.out.println("Ide w gore");
                goVertical(-2, 1, -1);
                direction = -2;
            } else if (option == 1) {
                //System.out.println("Ide w prawo");
                //goRight();
                goHorizontal(2, -1, 1);
                direction = 1;
            } else if (option == 2) {
                //System.out.println("Ide w dol");
                goVertical(2, -1, 1);
                direction = 2;
            } else {
                //blad?
                System.err.println("Error");
            }
        }
    }

    public void printEntranceAndExit() {
        System.out.println(entrance.getI() + " " + entrance.getJ());
        System.out.println(exit.getI() + " " + exit.getJ());
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
                exit = new Point(2 * maze.getHeight() - 1, i);
            }
        }
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
        if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIELD) {
            randomDirection.add(-1);
        }
        if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIELD && actualPosition.getI() - 1 != 0) {
            randomDirection.add(-2);
        }
        if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIELD) {
            randomDirection.add(1);
        }
        if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIELD) {
            randomDirection.add(2);
        }
        if (randomDirection.size() > 0) {
            return whereTurn(randomDirection);
        } else {
            //try to backward            
            if (checkEnterForBackward(direction * (-1))) {
                return direction * (-1);
            } else {
                // search the best option
                randomDirection = new ArrayList<>();
                if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIRST) {
                    randomDirection.add(-1);
                }
                if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIRST) {
                    randomDirection.add(-2);
                }
                if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIRST) {
                    randomDirection.add(1);
                }
                if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIRST) {
                    randomDirection.add(2);
                }
                if (randomDirection.size() > 0) {
                    return whereTurn(randomDirection);
                } else {
                    System.err.println("Error!!!");
                    return -100;
                }
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

        } else if (backward == 2) {
            if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIRST) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // w gore => i = -2, j = 1, k = -1
    // w dol => i = 2, j = -1, k = 1
    private void goVertical(int i, int j, int k) {
        int index = 0;
        actualPosition.setI(actualPosition.getI() + i);

        if (mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] == Cell.FIELD) {
            mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] = Cell.FIRST;
        } else {
            mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] = Cell.SECOND;
        }

        while (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.WALLL
                && mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.WALLL
                && (mazeToSolve[actualPosition.getI() + k][actualPosition.getJ()] == Cell.FIELD
                || mazeToSolve[actualPosition.getI() + k][actualPosition.getJ()] == Cell.FIRST)) {
            index++;
            actualPosition.setI(actualPosition.getI() + i);
            if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.EXIT) {
                break;
            }
        }
        if (index > 0) {
            if (mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] == Cell.FIELD) {
                mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] = Cell.FIRST;
            } else {
                mazeToSolve[actualPosition.getI() + j][actualPosition.getJ()] = Cell.SECOND;
            }
        }
    }

    //w lewo => i = -2 j = 1 k = -1
    // w prawo => i = 2 j = -1 k =1
    private void goHorizontal(int i, int j, int k) {
        int index = 0;
        actualPosition.setJ(actualPosition.getJ() + i);

        if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] == Cell.FIELD) {
            mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] = Cell.FIRST;
        } else {
            mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] = Cell.SECOND;
        }

        while ((mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.WALLL
                || mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.ENTRANCE)
                && mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.WALLL
                && (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + k] == Cell.FIELD
                || mazeToSolve[actualPosition.getI()][actualPosition.getJ() + k] == Cell.FIRST)) {
            index++;
            actualPosition.setJ(actualPosition.getJ() + i);
            if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.EXIT) {
                break;
            }
        }
        if (index > 0) {
            if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] == Cell.FIELD) {
                mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] = Cell.FIRST;
            } else {
                mazeToSolve[actualPosition.getI()][actualPosition.getJ() + j] = Cell.SECOND;
            }
        }
    }

    private void exploreWay() {
        direction = 0;
        mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
        while (actualPosition.getI() != entrance.getI() || actualPosition.getJ() != entrance.getJ()) {
            if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] == Cell.FIRST && direction * (-1) != -1) {
                actualPosition.setJ(actualPosition.getJ() - 2);
                mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
                direction = -1;
            } else if (mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] == Cell.FIRST && direction * (-1) != -2) {
                actualPosition.setI(actualPosition.getI() - 2);
                mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
                direction = -2;
            } else if (mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] == Cell.FIRST && direction * (-1) != 1) {
                actualPosition.setJ(actualPosition.getJ() + 2);
                mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
                direction = 1;
            } else if (mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] == Cell.FIRST && direction * (-1) != 2) {
                actualPosition.setI(actualPosition.getI() + 2);
                mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
                direction = 2;
            } else {
                if (direction == -1) {
                    searchWayHorizontal(-2);
                } else if (direction == -2) {
                    searchWayVertical(-2);
                } else if (direction == 1) {
                    searchWayHorizontal(2);
                } else if (direction == 2) {
                    searchWayVertical(2);
                } else {
                    System.err.println("Error!!!!!");
                }

            }
        }
    }

    private void searchWayHorizontal(int i) {
        actualPosition.setJ((actualPosition.getJ() + i));
        mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
        while (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] != Cell.FIRST
                && mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] != Cell.FIRST
                && mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] != Cell.FIRST
                && mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] != Cell.FIRST) {
            if (actualPosition.getI() == entrance.getI() && actualPosition.getJ() == entrance.getJ()) {
                break;
            }
            actualPosition.setJ(actualPosition.getJ() + i);
            mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
        }
    }

    private void searchWayVertical(int i) {
        actualPosition.setI((actualPosition.getI() + i));
        mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
        while (mazeToSolve[actualPosition.getI()][actualPosition.getJ() - 1] != Cell.FIRST
                && mazeToSolve[actualPosition.getI() - 1][actualPosition.getJ()] != Cell.FIRST
                && mazeToSolve[actualPosition.getI()][actualPosition.getJ() + 1] != Cell.FIRST
                && mazeToSolve[actualPosition.getI() + 1][actualPosition.getJ()] != Cell.FIRST) {
            if (actualPosition.getI() == entrance.getI() && actualPosition.getJ() == entrance.getJ()) {
                break;
            }
            actualPosition.setI(actualPosition.getI() + i);
            mazeToSolve[actualPosition.getI()][actualPosition.getJ()] = Cell.SOLVED;
        }
    }

    //wyswietlenie w celu zobrazowania w automacie CA
    public void displayCA() {
        for (int i = 0; i < mazeToSolve.length; i++) {
            for (int j = 0; j < mazeToSolve[0].length; j++) {
                if (mazeToSolve[i][j] == Cell.FIELD || mazeToSolve[i][j] == Cell.FIRST) {
                    System.out.print("0 ");
                } else if (mazeToSolve[i][j] == Cell.WALLL) {
                    System.out.print("1 ");
                } else if (mazeToSolve[i][j] == Cell.EXIT || mazeToSolve[i][j] == Cell.ENTRANCE || mazeToSolve[i][j] == Cell.SOLVED) {
                    System.out.print("2 ");
                } else if (mazeToSolve[i][j] == Cell.SECOND) {
                    System.out.print("3 ");
                }
            }
            System.out.println();
        }
    }

    //wyswietlnie stanow Cell
    public void displayLab() {
        for (int i = 0; i < mazeToSolve.length; i++) {
            for (int j = 0; j < mazeToSolve[0].length; j++) {
                System.out.print(mazeToSolve[i][j] + " ");
            }
            System.out.println();
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

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(int j) {
            this.j = j;
        }

    }
}
