/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.IOException;
import maze.Maze;
import maze.WriteFile;

/**
 *
 * @author Paweł
 */
public class WriteMazeTest {

    private WriteFile wf;

    public WriteMazeTest(String filename, Maze maze) {
        try {
            wf = new WriteFile(filename);
        } catch (IOException e) {
            System.out.println("Bad file name!");
        }
        wf.writeMatrix(maze);
    }
}
