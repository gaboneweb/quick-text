package com.bulelani.daemon;

public class KeyStrokeBuffer {

    private StringBuilder buffer;

    public KeyStrokeBuffer(){
        buffer = new StringBuilder();
    }

    public void append(char c){
        this.buffer.append(c);
    }

    public void removeLast(){
        if (this.buffer.length() > 0) this.buffer.deleteCharAt(this.buffer.length() - 1);
    }

    public void close(){
        this.buffer.setLength(0);
    }

    public String get(){
        return this.buffer.toString();
    }
}
