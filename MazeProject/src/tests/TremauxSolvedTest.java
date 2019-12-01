/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import algorithms.Tremaux;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class TremauxSolvedTest {

    private Tremaux tremaux;

    public TremauxSolvedTest(Maze maze) {
        tremaux = new Tremaux(maze);
    }

    public TremauxSolvedTest() {
        Maze maze = new Maze(10, 10);
        maze.generate();
        tremaux = new Tremaux(maze);
    }

    public void solveMaze() {
        tremaux.solveMaze();
        System.out.println("Generated and solved maze");
        tremaux.displaySolved();
    }

}
