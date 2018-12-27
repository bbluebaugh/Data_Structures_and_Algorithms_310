/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
import java.util.*;

/**
 *
 * @author bbluebaugh
 */
public class AdaptiveHuffmanDecompress {
    public static void main(String[] args) throws IOException{
        if(args.length != 2){
            System.err.println("Usage: java AdaptiveHuffmanDecompress InputFile OutPutFile");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        
        //perform file decompression
        BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
        try{
            decompress(in, out);
        }finally{
            out.close();
            in.close();
        }
    }
    
    
    //to allow unit testing, this method is package-private instead of private
    static void decompress(BitInputStream in, OutputStream out) throws IOException{
        int[] initFreqs = new int[257];
        Arrays.fill(initFreqs, 1);
        
        FrequencyTable freqs = new FrequencyTable(initFreqs);
        HuffmanDecoder dec = new HuffmanDecoder(in);
        dec.codeTree = freqs.buildCodeTree();//use the same algorithm as the compressor
        int count = 0; //number of bytes written to the output file
        while(true){
            //decode and write 1 byte
            int symbol = dec.read();
            if(symbol == 256){ //EOF symbol
                break;
            }
            out.write(symbol);
            count++;
            
            //update the frequency table and code tree if necessary
            freqs.increment(symbol);
            if(count < 262144 && isPowerOf2(count) || count % 262144 == 0){ //update code tree
                dec.codeTree = freqs.buildCodeTree();
            }
            if(count % 262144 == 0){ //Reset frequency table
                freqs = new FrequencyTable(initFreqs);
            }
        }
    }
    
    private static boolean isPowerOf2(int x){
        return x > 0 && Integer.bitCount(x) ==1;
    }
}
