/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Paweł
 */
public class ReadFile {

    private File file;
    private Scanner scanner;
    private Scanner scannerColumns;
    private Scanner scannerRows;
    private int numberOfColumns;
    private int numberOfRows;

    public ReadFile(String fileName) throws FileNotFoundException {
        this.file = new File(fileName);
        this.scanner = new Scanner(this.file);
        this.scannerColumns = new Scanner(this.file);
        this.scannerRows = new Scanner(this.file);
    }

    public int countNumberOfColumns() throws NoSuchElementException {
        scannerColumns.nextLine();
        numberOfColumns = scannerColumns.nextLine().replace(" ", "").length();
        int line;
        while (!(scannerColumns.hasNext("/maze"))) {
            line = scannerColumns.nextLine().replaceAll(" ", "").length();
            if (line != numberOfColumns) {
                throw new NoSuchElementException("Wrong file format!");
            }
        }
        return numberOfColumns;
    }

    public int countNubmberOfRows() {
        scannerRows.nextLine();
        while (!(scannerRows.hasNext("/maze"))) {
            numberOfRows++;
            scannerRows.nextLine();
        }
        return numberOfRows;
    }

    public void setMaze(Maze maze) {
        scanner.nextLine();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                switch(scanner.next()){
                    case "#":
                        maze.setCell(i, j, Cell.ENTRANCE);
                        break;
                    case "*":
                        maze.setCell(i, j, Cell.EXIT);
                        break;
                    case "+":
                        maze.setCell(i, j, Cell.WALLL);
                        break;
                    case "0":
                        maze.setCell(i, j, Cell.FIELD);
                }
            }
        }
    }
}
