/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.util.*;

/**
 *
 * @author bbluebaugh
 */

//A binary tree that represents a mapping between symboles and binary strings
public class CodeTree {
    public final InternalNode root;
    
    //stores the code for each symbol, or null if symbol has no code
    private List<List<Integer>> codes;
    
    //constructs a code tree from the specified tree of nodes and specified symbol limit.
    //each symbol must have a value strictly less than the symbol limit.
    public CodeTree(InternalNode root, int symbolLimit) {
        if(root == null){
            throw new NullPointerException();
        }
        if(symbolLimit < 2){
            throw new IllegalArgumentException("At least 2 symbols are needed");
        }
        this.root = root;
        codes = new ArrayList<List<Integer>>(); //initially all null
        for(int i = 0; i < symbolLimit; i++){
            codes.add(null);
        }
        buildCodeList(root, new ArrayList<Integer>()); //fill "codes" with appropriate data
    }
    
    
    //recursive helper function for the constructor
    private void buildCodeList(Node node, List<Integer> prefix) {
        if(node instanceof InternalNode){
            InternalNode internalNode = (InternalNode)node;
            
            prefix.add(0);
            buildCodeList(internalNode.leftChild , prefix);
            prefix.remove(prefix.size() - 1);
            
            prefix.add(1);
            buildCodeList(internalNode.rightChild, prefix);
            prefix.remove(prefix.size() - 1);
        }else if(node instanceof Leaf) {
            Leaf leaf = (Leaf)node;
            if(leaf.symbol >= codes.size()){
                throw new IllegalArgumentException("Symbol exceeds symbol limit");
            }
            if(codes.get(leaf.symbol) != null){
                throw new IllegalArgumentException("Symbol has more than one code");
            }
            codes.set(leaf.symbol, new ArrayList<Integer>(prefix));
        }else{
            throw new AssertionError("Illegal node type");
        }
    }
    
    
    //returns the huffman code for the specified symbol, which is a list of "1"s and "0"s
    public List<Integer> getCode(int symbol) {
        if(symbol < 0){
            throw new IllegalArgumentException("Illegal symbol");
        }
        else if(codes.get(symbol) == null){
            throw new IllegalArgumentException("No code for given symbol");
        }
        else{
            return codes.get(symbol);
        }
    }
    
    
    //returns a string representation of this code tree
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString("", root, sb);
        return sb.toString();
    }
    
    //recursive helper function for toString()
    private static void toString(String prefix, Node node, StringBuilder sb){
        if(node instanceof InternalNode){
            InternalNode internalNode = (InternalNode)node;
            toString(prefix + "0", internalNode.leftChild, sb);
            toString(prefix + "1", internalNode.rightChild, sb);
        }else if(node instanceof Leaf){
            sb.append(String.format("Code %s: Sybmol %d%n", prefix, ((Leaf)node).symbol));
        }
    }
}
