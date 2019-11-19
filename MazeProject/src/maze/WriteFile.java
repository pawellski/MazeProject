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
    private FileWriter fw;
    private PrintWriter pw;
    private Maze maze;

    public WriteFile(String fileName, Maze maze) throws IOException {
        this.file = new File(fileName);
        this.fw = new FileWriter(file, false);
        this.pw = new PrintWriter(fw);
        this.maze = maze;
    }

    public void writeMatrix() {
        pw.println("maze");

        for (int i = 0; i < (2 * maze.getHeight() + 1); i++) {
            for (int j = 0; j < (2 * maze.getWidth() + 1); j++) {
                if (maze.getCell(i, j) == Cell.ENTRANCE) {
                    pw.print("# ");
                } else if (maze.getCell(i, j) == Cell.EXIT) {
                    pw.print("* ");
                } else if (maze.getCell(i, j) == Cell.WALLL) {
                    pw.print("1 ");
                } else {
                    pw.print("0 ");
                }
            }
            pw.println();
        }
        pw.println("/maze");
        pw.flush();
        pw.close();
    }
}
