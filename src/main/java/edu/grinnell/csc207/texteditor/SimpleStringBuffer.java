package edu.grinnell.csc207.texteditor;

import java.util.Arrays;


/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    private int cursor;

    private int size; // size of string data

    private char[] data;

    /**
     * Creates an empty text buffer with a default capacity of 16 characters.
     */
    public SimpleStringBuffer() {
        this.cursor = 0;
        this.size = 0;
        data = new char[16];
    }

    /**
     * Creates a new text buffer with the given initial capacity.
     *
     * @param cursor the initial cursor position (usually 0)
     * @param size   the initial capacity of the buffer
     */
    SimpleStringBuffer(int cursor, int size) {
        this.cursor = 0;
        this.size = 0;
        data = new char[size];
    }

    /**
     * Inserts a character at the current cursor position.
     * All existing characters from the cursor onward are shifted one position to the right.
     * The cursor then moves one position forward.
     *
     * @param ch the character to insert
     */
    public void insert(char ch) {
        ensureCapacity();
        
        for (int i = size - 1; i >= cursor; i--) {
            data[i + 1] = data[i];
        }

        data[cursor] = ch;
        size++;
        cursor++;
    }

    /**
     * Deletes the character to the left of the cursor (backspace behavior).
     * The remaining characters are shifted left to fill the gap.
     * The cursor then moves one position backward.
     * If the cursor is at position 0, nothing happens.
     */
    public void delete() {
        if (cursor == 0) {
            return;
        }
        
        int i = cursor - 1;
        while (i < size - 1) {
            data[i] = data[i + 1];
            i++;
        }
        
        size--;
        cursor--;
    }

    /**
     * Returns the current cursor position in the buffer.
     *
     * @return the index where the next character will be inserted
     */
    public int getCursorPosition() {
        ensureCapacity();
        return cursor;
    }

    /**
     * Moves the cursor one position to the left, if possible.
     * If the cursor is already at the beginning, it does nothing.
     */
    public void moveLeft() {
        ensureCapacity();

        if (cursor != 0) {
            cursor--;
        }
    }

    /**
     * Moves the cursor one position to the right, if possible.
     * If the cursor is already at the end of the buffer, it does nothing.
     */
    public void moveRight() {
        ensureCapacity();

        if (cursor != size) {
            cursor++;
        }
    }


    /**
     * Returns the number of characters currently stored in the buffer.
     *
     * @return the size of the buffer
     */
    public int getSize() {
        ensureCapacity();
        return size;
    }

    /**
     * Returns the character at the specified index.
     *
     * @param i the index of the character to retrieve
     * @return the character at the given position
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public char getChar(int i) {
        ensureCapacity();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[i];
    }

    /**
     * Ensures that the buffer has enough capacity to hold new characters.
     * If the buffer is full, its size is doubled.
     */
    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    /**
     * Returns the contents of the buffer as a String.
     *
     * @return the text stored in the buffer
     */
    public String toString() {
        ensureCapacity();
        int i = 0;
        String output = "";

        while (i < size) {
            output = output + data[i];
            i++;
        }
        return output;
    }
}