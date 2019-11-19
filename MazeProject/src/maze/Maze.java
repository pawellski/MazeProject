/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Paweł
 */
public class Maze {

    private Cell[][] maze;
    private int width;
    private int height;

    public Maze(int hei, int wid) {
        this.height = hei;
        this.width = wid;
        this.maze = new Cell[2 * hei + 1][2 * wid + 1];
    }

    public void generate() {
        drawWalls();
        divide(1, 2 * width - 1, 1, 2 * height - 1);
        int entrance = random(1, 2 * width - 1);
        while (entrance % 2 == 0) {
            entrance = random(1, 2 * width - 1);
        }
        maze[0][entrance] = Cell.ENTRANCE;

        int exit = random(1, 2 * width - 1);
        while (exit % 2 == 0) {
            exit = random(1, 2 * width - 1);
        }
        maze[2 * height][exit] = Cell.EXIT;

    }

    public void divide(int x1, int x2, int y1, int y2) {
        if (x2 - x1 > 1 || y2 - y1 > 1) {
            //Random straight = new Random();
            //int whichStraight = straight.nextInt(2);
            //if (whichStraight == 0 && x2 - x1 > 1) {
            if (x2 - x1 > y2 - y1 && x2 - x1 > 1) {
                Random whereDraw = new Random();
                if (y2 > y1) {
                    int where = whereDraw.nextInt(x2 - x1) + x1;
                    while (where % 2 != 0) {
                        where = whereDraw.nextInt(x2 - x1) + x1;
                    }
                    drawVertical(y1, y2, where);
                    divide(x1, where, y1, y2);
                    divide(where + 1, x2, y1, y2);
                }
                //} else if (whichStraight == 1 && y2 - y1 > 1) {
            } else if (y2 - y1 > x2 - x1 && y2 - y1 > 1) {
                Random whereDraw = new Random();
                if (x2 > x1) {
                    int where = whereDraw.nextInt(y2 - y1) + y1;
                    while (where % 2 != 0) {
                        where = whereDraw.nextInt(y2 - y1) + y1;
                    }
                    drawHorizontal(x1, x2, where);
                    divide(x1, x2, y1, where);
                    divide(x1, x2, where + 1, y2);
                }
            } else {
                Random straight = new Random();
                int whichStraight = straight.nextInt(2);
                if (whichStraight == 0 && x2 - x1 > 1) {
                    Random whereDraw = new Random();
                    if (y2 > y1) {
                        int where = whereDraw.nextInt(x2 - x1) + x1;
                        while (where % 2 != 0) {
                            where = whereDraw.nextInt(x2 - x1) + x1;
                        }
                        drawVertical(y1, y2, where);
                        divide(x1, where, y1, y2);
                        divide(where + 1, x2, y1, y2);
                    }
                } else if (whichStraight == 1 && y2 - y1 > 1) {
                    Random whereDraw = new Random();
                    if (x2 > x1) {
                        int where = whereDraw.nextInt(y2 - y1) + y1;
                        while (where % 2 != 0) {
                            where = whereDraw.nextInt(y2 - y1) + y1;
                        }
                        drawHorizontal(x1, x2, where);
                        divide(x1, x2, y1, where);
                        divide(x1, x2, where + 1, y2);

                    }

                }
            }
        }
    }

    public void drawWalls() {
        for (int i = 1; i < 2 * height + 1; i++) {
            for (int j = 1; j < 2 * width + 1; j++) {
                maze[i][j] = Cell.FIELD;
            }
        }

        for (int i = 0; i < 2 * height + 1; i++) {
            maze[i][0] = Cell.WALLL;
            maze[i][2 * width] = Cell.WALLL;
        }

        for (int j = 1; j < 2 * width + 1; j++) {
            maze[0][j] = Cell.WALLL;
            maze[2 * height][j] = Cell.WALLL;
        }
    }

    public void drawHorizontal(int x1, int x2, int y) {
        for (int i = x1; i < x2 + 1; i++) {
            maze[y][i] = Cell.WALLL;
        }

        int gap = random(x1, x2);
        while (gap % 2 == 0 && gap > x1 && gap < x2) {
            gap = random(x1, x2);
        }
        maze[y][gap] = Cell.FIELD;
    }

    public void drawVertical(int y1, int y2, int x) {
        for (int i = y1; i < y2 + 1; i++) {
            maze[i][x] = Cell.WALLL;
        }
        int gap = random(y1, y2);
        while (gap % 2 == 0 && gap > y1 && gap < y2) {
            gap = random(y1, y2);
        }
        maze[gap][x] = Cell.FIELD;
    }

    public int random(int a, int b) {
        Random randomGap = new Random();
        int gap = randomGap.nextInt(b - a) + a;
        return gap;
    }

    public void drawMaze() {
        for (int i = 0; i < 2 * height + 1; i++) {
            for (int j = 0; j < 2 * width + 1; j++) {
                if (maze[i][j] == Cell.FIELD) {
                    System.out.print("0 ");
                } else if (maze[i][j] == Cell.WALLL) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int i, int j) {
        return maze[i][j];

    }

    public void writeMaze(String fileName) throws IOException {
        WriteFile wf = new WriteFile(fileName, this);
        wf.writeMatrix();
    }

}
