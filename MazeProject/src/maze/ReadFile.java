/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Paweł
 */
public class ReadFile {

    private File file;
    private Scanner scanner;
    private FileReader frRows;
    private FileReader frColumns;
    private BufferedReader brRows;
    private BufferedReader brColumns;
    private int numberOfColumns;
    private int numberOfRows;

    public ReadFile(String fileName) throws FileNotFoundException {
        this.file = new File(fileName);
        this.scanner = new Scanner(this.file);
        this.frRows = new FileReader(file);
        this.frColumns = new FileReader(file);
        this.brRows = new BufferedReader(frRows);
        this.brColumns = new BufferedReader(frColumns);
    }

    public int countNumberOfRows() throws FileNotFoundException, IOException {
        numberOfRows = 0;
        while (brRows.readLine() != null) {
            numberOfRows++;
        }
        return numberOfRows;
    }

    public int countNumberOfColumns() throws FileNotFoundException, IOException {
        numberOfColumns = brColumns.readLine().length();
        int line;
        while (brColumns.readLine() != null) {
            line = brColumns.readLine().length();
            if (line != numberOfColumns) {
                throw new NoSuchElementException("Wrong file format!");
            }
        }
        return numberOfColumns;
    }

    public void setMaze(Maze maze) {
        for (int i = 0; i < numberOfRows; i++) {
            String line = scanner.next();
            for (int j = 0; j < numberOfColumns; j++) {
                switch (line.charAt(j)) {
                    case '#':
                        maze.setCell(i, j, Cell.ENTRANCE);
                        break;
                    case '*':
                        maze.setCell(i, j, Cell.EXIT);
                        break;
                    case '+':
                        maze.setCell(i, j, Cell.WALLL);
                        break;
                    case '0':
                        maze.setCell(i, j, Cell.FIELD);
                        break;
                }
            }
        }
    }
}