package edu.grinnell.csc207.texteditor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class SimpleStringBufferTests {

    @Test
    public void InitialState() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer();
        assertEquals(0, myBuffer.getSize());          // size = logical length
        assertEquals(0, myBuffer.getCursorPosition());
        assertEquals("", myBuffer.toString());
    }



    @Test
    public void insertIncreasesSizeAndMovesCursor() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer();
        myBuffer.insert('A');
        assertEquals(1, myBuffer.getSize());
        assertEquals(1, myBuffer.getCursorPosition());
        assertEquals("A", myBuffer.toString());
    }


    @Test
    public void deleteRemovesCharacter() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer();
        myBuffer.insert('A');
        myBuffer.insert('B');
        myBuffer.delete(); // backspace removes 'B'
        assertEquals(1, myBuffer.getSize());
        assertEquals(1, myBuffer.getCursorPosition());
        assertEquals("A", myBuffer.toString());
    }


    @Property
    boolean insertingCharactersShouldPreserveOrder(@ForAll String input) {
        SimpleStringBuffer buf = new SimpleStringBuffer();

        for (int i = 0; i < input.length(); i++) {
            buf.insert(input.charAt(i));
        }

        assertEquals(input.length(), buf.getSize());
        assertEquals(input, buf.toString());
        assertEquals(input.length(), buf.getCursorPosition());

        return true;
    }
}