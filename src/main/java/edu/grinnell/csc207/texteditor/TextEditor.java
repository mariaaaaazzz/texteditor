
package edu.grinnell.csc207.texteditor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;


/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Draws the contents of the buffer to the screen.
     * @param buf the gap buffer to draw.
     * @param screen the screen to draw on.
     * @throws IOException if an I/O error occurs.
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        screen.refresh();
        for (int i = 0; i < buf.getSize(); i++) {
            char ch = buf.getChar(i);
            screen.setCharacter(i, 0, TextCharacter.fromCharacter(ch)[0]); 
        }
        screen.setCursorPosition(new TerminalPosition(buf.getCursorPosition(), 0));
        screen.refresh();
    }

    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }


        String fileName = args[0];
        Path path = Paths.get(fileName);
        GapBuffer buf = new GapBuffer();
        if (Files.exists(path) && Files.isRegularFile(path)) {
            String content = Files.readString(path);
            for (char c : content.toCharArray()) {
                buf.insert(c);
            }
        }


        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        drawBuffer(buf, screen);

        while (true) {
            KeyStroke key = screen.readInput();  
            if (key.getKeyType() == KeyType.Escape) {
                break;
            }
            if (key.getKeyType() == KeyType.Character) {
                buf.insert(key.getCharacter());
            } else if (key.getKeyType() == KeyType.Backspace) {
                buf.delete();                   
            } else if (key.getKeyType() == KeyType.ArrowLeft) {
                buf.moveLeft();
            } else if (key.getKeyType() == KeyType.ArrowRight) {
                buf.moveRight();                
            }
            drawBuffer(buf, screen);
        }
        screen.stopScreen();



        Files.writeString(path, buf.toString());
        System.out.format("Loading %s...\n", path);
    }



}