/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import maze.Maze;

/**
 *
 * @author Paweł
 */
public class GenerateMazeTest {
    private Maze maze;
    private int height;
    private int width;
    
    public GenerateMazeTest(int height, int width){
        this.height = height;
        this.width = width;
    }
    
    public void genrateAndDisplayMaze(){
        maze = new Maze(height, width);
        maze.generate();
        maze.drawMaze();
    }
    
    public void generateAndDisplayMazeWithMoreGaps(){
        maze = new Maze(height, width);
        maze.generateWithTwoGaps();
        maze.drawMaze();
    }
    
}
