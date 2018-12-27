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
public class HuffmanCompress {
    public static void main(String[] args) throws IOException{
        if(args.length != 2){
            System.err.println("Usage: java HuffmanCompress InputFile OutputFile");
            System.exit(1);
            return;
        }
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        
        //read input file once to compute symbol frequencies
        //the resulting generated code is optimal for static Huffman coding & canoniccal
        FrequencyTable freqs = getFrequencies(inputFile);
        freqs.increment(256); //EOF symbol gets a frequency of 1
        CodeTree code = freqs.buildCodeTree();
        CanonicalCode canonCode = new CanonicalCode(code, 257);
        //replace code tree with canonical one, for each symbol
        //code value may be change but the code length stays the same
        code = canonCode.toCodeTree();
        
        //read input file again, compress with Huffman coding, and write output file
        InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
        BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        try{
            writeCodeLengthTable(out, canonCode);
            compress(code, in, out);
        }finally{
            out.close();
            in.close();
        }
    }
    
    //returns a frequency table based on the bytes in the given file
    //also contains an extra entry for symbol 256, whose freq is set to 0
    private static FrequencyTable getFrequencies(File file) throws IOException{
        FrequencyTable freqs = new FrequencyTable(new int[257]);
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        try{
            while(true){
                int b = input.read();
                if(b == -1){
                    break;
                }
                freqs.increment(b);
            }
        }finally{
            input.close();
        }
        return freqs;
    }
    
    //to allow unit testing, this method is package private instead of private
    static void writeCodeLengthTable(BitOutputStream out, CanonicalCode canonCode) throws IOException{
        for(int i = 0; i < canonCode.getSymbolLimit(); i++){
            int val = canonCode.getCodeLength(i);
            //for hisfile format, we only support codes up to 255 bits long
            if(val >= 256){
                throw new RuntimeException("the code for a symbol is too long");
            }
            for(int j = 7; j >= 0; j--){
                out.write((val >>> j) & 1);
            }
        }
    }
    
    
    //to allow unit testing """"""
    static void compress(CodeTree code, InputStream in, BitOutputStream out) throws IOException{
        HuffmanEncoder enc = new HuffmanEncoder(out);
        enc.codeTree = code;
        while(true){
            int b = in.read();
            if(b == -1){
                break;
            }
            enc.write(b);
        }
        enc.write(256); //EOF
    }
}
