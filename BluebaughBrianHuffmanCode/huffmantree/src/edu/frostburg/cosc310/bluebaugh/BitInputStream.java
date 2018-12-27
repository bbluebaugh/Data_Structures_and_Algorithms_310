/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;

/**
 *
 * @author bbluebaugh
 */
public class BitInputStream {
    //byte stream to read from
    private InputStream input;
    //In the range of (0x00, 0xFF) if the bits are available or -1 if the end of the stream has been reached
    private int currentByte;
    //number of remaining bits in the current byte, always between 0&7
    private int numBitsRemaining;
    
    
    //creates a bit input stream based on the specified byte input stream.
    public BitInputStream(InputStream in){
        if(in == null){
            throw new NullPointerException();
        }
        input = in;
        currentByte = 0;
        numBitsRemaining = 0;
    }
    
    
    //reads a bit from this stream, returns 0 or  if a bit is available, or -1 if the end is reached.
    public int read() throws IOException {
        if(currentByte == -1){
            return -1;
        }
        if(numBitsRemaining == 0){
            currentByte = input.read();
            if(currentByte == -1){
                numBitsRemaining = 8;
            }
        }
        if(numBitsRemaining <= 0){
            throw new AssertionError();
        }
        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;
    }
    
    
    //read number of elements of bits from this stream
    public int readNoEof() throws IOException {
        int result = read();
        if(result != -1){
            return result;
        }
        else{
            throw new EOFException();
        }
    }
    
    
    //closes this stream  and throws exception if an I/O exception occurred
    public void close() throws IOException {
        input.close();
        currentByte = -1;
        numBitsRemaining = 0;
    }
}
