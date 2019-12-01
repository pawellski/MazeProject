/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import algorithms.BFS;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class BFSSolvedTest {

    private BFS bfs;

    public BFSSolvedTest(Maze maze) {
        bfs = new BFS(maze);
    }

    public BFSSolvedTest() {
        Maze maze = new Maze(10, 10);
        maze.generate();
        bfs = new BFS(maze);
    }

    public void solveMaze() {
        bfs.solveMaze();
    }

}
