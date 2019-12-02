/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import algorithms.BFS;
import algorithms.Tremaux;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class CompareTimeTest {

    private BFS bfs;
    private Tremaux tremaux;
    private Maze maze;
    private long[] bfsTime;
    private long[] tremauxTime;

    public CompareTimeTest() {

    }

    public void compareTime(int repeat, int executeOneMaze) {
        bfsTime = new long[repeat];
        tremauxTime = new long[repeat];

        for (int i = 1; i < repeat + 1; i++) {
            maze = new Maze(10 * i, 10 * i);
            maze.generate();
            long tmpBFS = 0;
            long tmpTremaux = 0;
            for (int j = 0; j < executeOneMaze; j++) {
                bfs = new BFS(maze);
                tremaux = new Tremaux(maze);
                long startBFS = System.nanoTime();
                bfs.createGraph();
                bfs.searchWay();
                long stopBFS = System.nanoTime();

                long startTremaux = System.nanoTime();
                tremaux.solveMaze();
                long stopTremaux = System.nanoTime();

                tmpBFS = tmpBFS + (stopBFS - startBFS);
                tmpTremaux = tmpTremaux + (stopTremaux - startTremaux);
            }
            bfsTime[i - 1] = tmpBFS / executeOneMaze;
            tremauxTime[i - 1] = tmpTremaux / executeOneMaze;
        }
    }

    public void displayBFSTime() {
        System.out.println("Time BFS:");
        for (int i = 0; i < bfsTime.length; i++) {
            System.out.println(bfsTime[i]);
        }
    }

    public void displayTremauxTime() {
        System.out.println("Time Tremaux:");
        for (int i = 0; i < tremauxTime.length; i++) {
            System.out.println(tremauxTime[i]);
        }
    }
}
