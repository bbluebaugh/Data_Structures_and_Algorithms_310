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
public class BitOutputStream {
    //the byte stream to write to
    private OutputStream output;
    //the accumulated bits for the current byte, between (0x00, 0xFF)
    private int currentByte;
    //number of accumulated bits in the current byte, between 0&7
    private int numBitsFilled;
    
    //creates a bit output stream based on the specified byte output stream.
    public BitOutputStream(OutputStream out){
        if(out == null){
            throw new NullPointerException();
        }
        output = out;
        currentByte = 0;
        numBitsFilled = 0;
    }
    
    
    //Writes a bit to the stream. Specified bit must be 0 or 1.
    public void write(int b) throws IOException {
        if(b != 0 && b !=1){
            throw new IllegalArgumentException("Argument must be a 0 or 1");
        }
        currentByte = (currentByte << 1) | b;
        numBitsFilled++;
        if(numBitsFilled == 8){
            output.write(currentByte);
            currentByte = 0;
            numBitsFilled = 0;
        }
    }
    
    
    //closes the stream, and if called when not at a byte boundary
    //then it will be padded "0" bits to reach the next byte boundary
    public void close() throws IOException {
        while(numBitsFilled !=0){
            write(0);
        }
        output.close();
    }
    
}
