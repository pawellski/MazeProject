/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import maze.Maze;
import tests.BFSSolvedTest;
import tests.CompareAlgorithms;
import tests.GenerateMazeTest;
import tests.ReadMazeTest;
import tests.TremauxSolvedTest;
import tests.WriteMazeTest;

/**
 *
 * @author Paweł
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("GENERATE MAZE TEST");
        GenerateMazeTest gmt = new GenerateMazeTest(10, 10);
        System.out.println("The maze with one option solution.");
        gmt.genrateAndDisplayMaze();
        System.out.println();

        System.out.println("The maze with a few solutions");
        gmt.generateAndDisplayMazeWithMoreGaps();
        System.out.println();

        System.out.println("READ MAZE TEST");
        ReadMazeTest rmt = new ReadMazeTest("src/resources/mazeToRead.txt");
        System.out.println("The maze from file.");
        rmt.drawMaze();
        System.out.println();

        System.out.println("WRITE MAZE TEST");
        WriteMazeTest wmt = new WriteMazeTest("src/resources/savedMaze.txt", gmt.getMaze());
        System.out.println();
        
        System.out.println("BFS SOLVED TEST");
        BFSSolvedTest bfsst = new BFSSolvedTest();
        bfsst.solveMaze();
        System.out.println();

        System.out.println("TREMAUX SOLVED TEST");
        TremauxSolvedTest tst = new TremauxSolvedTest();
        tst.solveMaze();
        System.out.println();

        System.out.println("COMPARE ALGORITHMS TEST");
        System.out.println("The maze to solve:");
        Maze maze = new Maze(30, 30);
        maze.generate();
        maze.drawMaze();
        CompareAlgorithms ca = new CompareAlgorithms(maze);
        ca.compareTime();
    }

}
