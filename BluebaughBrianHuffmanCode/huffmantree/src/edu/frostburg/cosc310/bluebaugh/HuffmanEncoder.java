
package edu.frostburg.cosc310.bluebaugh;
import java.io.IOException;
import java.io.*;
import java.util.List;

/**
 *
 * @author bbluebaugh
 */
public class HuffmanEncoder {
    private BitOutputStream output;
    
    public CodeTree codeTree;
    
    public HuffmanEncoder(BitOutputStream out){
        if(out == null){
            throw new NullPointerException();
        }
        output = out;
    }
    
    public void write(int symbol) throws IOException{
        if(codeTree == null){
            throw new NullPointerException("code tree is null");
        }
        List<Integer> bits = codeTree.getCode(symbol);
        for(int b : bits){
            output.write(b);
        }
    }
}
