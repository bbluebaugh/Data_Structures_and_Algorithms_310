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
public class AdaptiveHuffmanCompress {
    public static void main(String[] args) throws IOException{
        //command line arguments
        if(args.length != 2){
            System.err.println("Usage: java adaptiveHuffmanCompress InputFile OutputFile");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        
        InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        try{
            compress(in, out);
        }finally{
            out.close();
            in.close();
        }
    }
    
    //allow unit testing
    static void compress(InputStream in, BitOutputStream out) throws IOException{
        int[] initFreqs = new int[257];
        Arrays.fill(initFreqs, 1);
        
        FrequencyTable freqs = new FrequencyTable(initFreqs);
        HuffmanEncoder enc = new HuffmanEncoder(out);
        enc.codeTree = freqs.buildCodeTree();//do not need canonical code becasue do not transmit the code tree
        int count = 0;//number of bytes read from the input file
        while(true){
            //read and encode one byte
            int symbol = in.read();
            if(symbol == -1){
                break;
            }
            enc.write(symbol);
            count++;
            
            //update the frequency table and possibly the code tree
            freqs.increment(symbol);
            //update code tree
            if(count < 262144 && isPowerOf2(count) || count % 262144 == 0){
                enc.codeTree = freqs.buildCodeTree();
            }
            //reset frequency table
            if(count % 262144 == 0){
                freqs = new FrequencyTable(initFreqs);
            }
        }
        enc.write(256);//EOF
    }
    
    private static boolean isPowerOf2(int x){
        return x > 0 && Integer.bitCount(x) == 1;
    }
}
