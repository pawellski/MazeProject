/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import maze.Maze;

/**
 *
 * @author Paweł
 */
public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);
        maze.generate();
        maze.drawMaze();
    }

}
