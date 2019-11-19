/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import maze.Maze;

/**
 *
 * @author Paweł
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Maze maze1 = new Maze(10, 10);
        maze1.generate();
        maze1.drawMaze();
        //maze1.writeMaze(fileName);
        //Maze maze2 = new Maze(fileName);
        //maze2.drawMaze();
    }

}
