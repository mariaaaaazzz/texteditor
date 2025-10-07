package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {

    @Test
    public void InitialState() {
        GapBuffer buf = new GapBuffer();
        assertEquals(0, buf.getSize());
        assertEquals(0, buf.getCursorPosition());
        assertEquals("", buf.toString());
    }

    @Test
    public void InsertSingleCharacter() {
        GapBuffer buf = new GapBuffer();
        buf.insert('A');
        assertEquals(1, buf.getSize());
        assertEquals(1, buf.getCursorPosition());
        assertEquals("A", buf.toString());
    }



    @Test
    public void InsertMultipleCharacters() {
        GapBuffer buf = new GapBuffer();
        buf.insert('H');
        buf.insert('i');
        buf.insert('!');
        assertEquals(3, buf.getSize());
        assertEquals(3, buf.getCursorPosition());
        assertEquals("Hi!", buf.toString());
    }


    @Property
    void insertingStringShouldReproduceOriginal(@ForAll String text) {
        GapBuffer buf = new GapBuffer();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            buf.insert(c);
        }

        assertEquals(text, buf.toString());
        assertEquals(text.length(), buf.getSize());
    }

}
