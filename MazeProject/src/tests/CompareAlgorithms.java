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
public class CompareAlgorithms {

    private BFS bfs;
    private Tremaux treamux;

    public CompareAlgorithms(Maze maze) {
        bfs = new BFS(maze);
        treamux = new Tremaux(maze);
    }

    public void compareTime() {
        long startBFS;
        long stopBFS;
        long startTremaux;
        long stopTremaux;

        startBFS = System.nanoTime();
        bfs.createGraph();
        bfs.searchWay();
        stopBFS = System.nanoTime();
        System.out.println();
        System.out.println("Solutions BFS:");
        bfs.showWay();
        
        startTremaux = System.nanoTime();
        treamux.solveMaze();
        stopTremaux = System.nanoTime();
        System.out.println();
        System.out.println("Solution Treamux:");
        treamux.displaySolved();
        
        System.out.println("Czas dzialania algorytmu BFS = " + (stopBFS - startBFS));
        System.out.println("Czas dzialania algorytmu Tremaux = " + (stopTremaux - startTremaux));

        if( (stopBFS - startBFS) > (stopTremaux - startTremaux)){
            System.out.println("Algorytm Treamux znalazl droge szybciej o " + ((stopBFS - startBFS) - (stopTremaux - startTremaux)));
        } else {
            System.out.println("Algorytm BFS znalazl droge szybciej o " + ((stopTremaux - startTremaux) - (stopBFS - startBFS)));
        }
    }
}
