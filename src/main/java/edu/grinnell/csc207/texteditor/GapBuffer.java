package edu.grinnell.csc207.texteditor;
import java.util.Arrays;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private int cursor;//gapStart
    private int gapEnd;
    private char[] data;
    //private int size;//data.length - (gapEnd-cursor)


    public GapBuffer(){
        data = new char[10];
        cursor = 0;
        gapEnd = data.length;
    }
    public void insert(char ch) {
        ensureGap(1);
        data[cursor]=ch;
        cursor++;
    }

    public void delete() {
        if (cursor == 0){
            return;
        }
        cursor--;
    }

    public int getCursorPosition() {
        return cursor;
    }

    public void moveLeft() {
        if (cursor > 0){
            data[cursor-1]=data[gapEnd-1];
            cursor--;
            gapEnd--;
        }
    }

    public void moveRight() {
        if (cursor < data.length){
            data[cursor]=data[gapEnd];
            cursor++;
            gapEnd++;
        }
    }

    public int getSize() {
        int size = data.length - (gapEnd-cursor);
        return size;
    }

    public char getChar(int i) {
        if (i<0 || i > getSize()){
            throw new IndexOutOfBoundsException();
        }
        if (i<cursor){
            return data[i];
        }else {
            return data[i+cursor-gapEnd];
        }
  
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < cursor; i++) {
            result += data[i];
        }
        for (int i = gapEnd; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }

    private void ensureGap(int gapNeed){
       int gapSize = gapEnd - cursor;
       if (gapSize >= gapNeed){
        return;
    }
    int oldLength = data.length;
    char[] newData = Arrays.copyOf(data, oldLength * 2);

    // Move right block to the end of the new array
    int rightSize = oldLength - gapEnd;
    int newGapEnd = oldLength * 2 - rightSize;

    for (int i = 0; i < rightSize; i++) {
        newData[newGapEnd + i] = data[gapEnd + i];
    }

    data = newData;
    gapEnd = newGapEnd;
}
}
