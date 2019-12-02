/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class ReadMazeTest {

    private Maze maze;

    public ReadMazeTest(String filename)  {
        try {
            maze = new Maze(filename);
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
        } catch (IOException e){
            System.out.println("Wrong format!");
        }
    }

    public void drawMaze() {
        if (maze != null) {
            maze.drawMaze();
        } else {
            System.out.println("The maze did not create!");
        }
    }

}
