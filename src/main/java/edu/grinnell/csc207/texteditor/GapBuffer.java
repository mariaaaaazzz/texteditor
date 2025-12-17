
package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private int cursor; // gapStart

    private int gapEnd;

    private char[] data;

    /**
     * Creates an empty gap buffer with an initial capacity of 10 characters.
     */
    public GapBuffer() {
        data = new char[10];
        cursor = 0;
        gapEnd = data.length;
    }

    /**
     * Inserts a character at the current cursor position.
     * The cursor then moves one position to the right.
     *
     * @param ch the character to insert
     */
    public void insert(char ch) {
        ensureGap(1);
        data[cursor] = ch;
        cursor++;
    }

    /**
     * Deletes the character to the left of the cursor, if there is one.
     * The cursor then moves one position to the left.
     */
    public void delete() {
        if (cursor == 0) {
            return;
        }
        cursor--;
        data[cursor] = '\0';
    }

    /**
     * Returns the current cursor position.
     *
     * @return the index where the next character will be inserted
     */
    public int getCursorPosition() {
        return cursor;
    }

    /**
     * Moves the cursor one position to the left.
     */
    public void moveLeft() {
        if (cursor == 0) {
            return;
        }
        
        data[gapEnd - 1] = data[cursor - 1];
      
        data[cursor - 1] = '\0';

        cursor--;
        gapEnd--;
    }

    /**
     * Moves the cursor one position to the right.
     */
    public void moveRight() {
        if (gapEnd == data.length) {
            return;
        }
      
        data[cursor] = data[gapEnd];
       
        data[gapEnd] = '\0';

        cursor++;
        gapEnd++;
    }

    /**
     * Returns the number of characters currently stored in the buffer.
     *
     * @return the number of characters in the buffer.
     */
    public int getSize() {
        int size = data.length - (gapEnd - cursor);
        return size;
    }

    /**
     * Returns the character at the given index in the buffer.
     *
     * @param i the index of the character to retrieve
     * @return the character at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public char getChar(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException();
        }
        if (i < cursor) {
            return data[i];
        } else {
            return data[i - cursor + gapEnd];
        }
  
    }


    /**
     * Returns the contents of the buffer as a String.
     *
     * @return the buffer's contents as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(getSize());

        for (int i = 0; i < cursor; i++) {
            sb.append(data[i]);
        }

        for (int i = gapEnd; i < data.length; i++) {
            sb.append(data[i]);
        }
        
        return sb.toString();
    }

    /**
     * Ensures that the gap has enough space for the given number of characters.
     * If not, the array is resized and the right chunk is moved to the end.
     *
     * @param gapNeed the minimum gap size required
     */
    private void ensureGap(int gapNeed) {
        int gapSize = gapEnd - cursor;
        if (gapSize >= gapNeed) {
            return;
        }

        int newLen = Math.max(data.length * 2, data.length + gapNeed);
        char[] newData = new char[newLen];

     
        System.arraycopy(data, 0, newData, 0, cursor);

        int rightSize = data.length - gapEnd;
        int newGapEnd = newLen - rightSize;
        System.arraycopy(data, gapEnd, newData, newGapEnd, rightSize);

        data = newData;
        gapEnd = newGapEnd;
    }
}
