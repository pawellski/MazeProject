/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Paweł
 */
public class WriteFile {

    private File file;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public WriteFile(String fileName) throws IOException {
        this.file = new File(fileName);
        this.fileWriter = new FileWriter(this.file, false);
        this.printWriter = new PrintWriter(this.fileWriter);
    }

    public void writeMatrix(Maze maze) {
        printWriter.println("maze");

        for (int i = 0; i < (2 * maze.getHeight() + 1); i++) {
            for (int j = 0; j < (2 * maze.getWidth() + 1); j++) {
                if (maze.getCell(i, j) == Cell.ENTRANCE) {
                    printWriter.print("# ");
                } else if (maze.getCell(i, j) == Cell.EXIT) {
                    printWriter.print("* ");
                } else if (maze.getCell(i, j) == Cell.WALLL) {
                    printWriter.print("+ ");
                } else {
                    printWriter.print("0 ");
                }
            }
            printWriter.println();
        }
        printWriter.println("/maze");
        printWriter.flush();
        printWriter.close();
    }
}
