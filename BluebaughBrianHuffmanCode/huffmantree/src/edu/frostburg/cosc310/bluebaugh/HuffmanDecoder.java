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
public class HuffmanDecoder {
    private BitInputStream input;
    
    public CodeTree codeTree;
    
    public HuffmanDecoder(BitInputStream in){
        if(in == null){
            throw new NullPointerException();
        }
        input = in;
    }
    
    
    //read from the input stream to decode the next huffman-coded symbol
    public int read() throws IOException{
        if(codeTree == null){
            throw new NullPointerException("Code tree is null");
        }
        InternalNode currentNode = codeTree.root;
        while(true){
            int temp = input.readNoEof();
            Node nextNode;
            if(temp == 0){
                nextNode = currentNode.leftChild;
            }else if(temp == 1){
                nextNode = currentNode.rightChild;
            }else{
                throw new AssertionError("Invalid value from readNoEof()");
            }
            if(nextNode instanceof Leaf){
                return((Leaf)nextNode).symbol;
            }else if(nextNode instanceof InternalNode){
                currentNode = (InternalNode)nextNode;
            }else{
                throw new AssertionError("Illegal node type");
            }
        }
    }
}
