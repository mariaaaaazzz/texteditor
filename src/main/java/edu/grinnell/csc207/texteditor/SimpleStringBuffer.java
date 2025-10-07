package edu.grinnell.csc207.texteditor;

import java.util.Arrays;


/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    private int cursor;
    private int size; //size of string data
    private char[] data;

    SimpleStringBuffer(int cursor, int size){
        this.cursor = 0;
        this.size = 0;
        data = new char[size];
    }

    public void insert(char ch) {
        ensureCapacity();
        int i = cursor;
        while(i<size){
            data[i+1] = data[i];
            i++;
        }
        data[cursor]=ch;
        size++;
        cursor++;
    }

    public void delete() {
        ensureCapacity();
        int i = cursor;
        while (i<size){
            data[i+1]=data[i];
            i++;
        }
        size--;
        cursor--;
    }

    public int getCursorPosition() {
        ensureCapacity();
        return cursor;
    }

    public void moveLeft() {
        ensureCapacity();
        if (cursor != 0){
            cursor--;
        }
    }

    public void moveRight() {
        ensureCapacity();
        if (cursor != size){
            cursor++;
        }
    }

    public int getSize() {
        ensureCapacity();
        return size;
    }

    public char getChar(int i) {
        ensureCapacity();
        if (i<0 || i>=size){
            throw new IndexOutOfBoundsException();
        }
        return data[i];
    }

    private void ensureCapacity(){
        if(size==data.length){
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    @Override
    public String toString() {
        ensureCapacity();
        int i = 0;
        String output = "";
        while(i<size){
            output = output + data[i];
            i++;
        }
        return output;
    }
}
