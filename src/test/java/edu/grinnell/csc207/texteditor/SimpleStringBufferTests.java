package edu.grinnell.csc207.texteditor;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class SimpleStringBufferTests {

    @Test
    public void InitialState() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer(0, 10);
        assertEquals(0, myBuffer.getSize());          // size = logical length
        assertEquals(0, myBuffer.getCursorPosition());
        assertEquals("", myBuffer.toString());
    }



    @Test
    public void insertIncreasesSizeAndMovesCursor() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer(0, 10);
        myBuffer.insert('A');
        assertEquals(1, myBuffer.getSize());
        assertEquals(1, myBuffer.getCursorPosition());
        assertEquals("A", myBuffer.toString());
    }


    @Test
    public void deleteRemovesCharacter() {
        SimpleStringBuffer myBuffer = new SimpleStringBuffer(0, 10);
        myBuffer.insert('A');
        myBuffer.insert('B');
        myBuffer.delete(); // backspace removes 'B'
        assertEquals(1, myBuffer.getSize());
        assertEquals(1, myBuffer.getCursorPosition());
        assertEquals("A", myBuffer.toString());
    }


    @Property
    boolean insertingCharactersShouldPreserveOrder(@ForAll String input) {
        SimpleStringBuffer buf = new SimpleStringBuffer(0, Math.max(10, input.length() + 1));

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            buf.insert(c);
        }
        assertEquals(input.length(), buf.getSize());
        assertEquals(input, buf.toString());
        assertEquals(input.length(), buf.getCursorPosition());

        return true;
    }
}

