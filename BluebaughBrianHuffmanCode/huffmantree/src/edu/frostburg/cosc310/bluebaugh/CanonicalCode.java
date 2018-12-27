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
public class CanonicalCode {
    
    private int[] codeLengths;
    
    
    // creates a canonical huffman code from the specified array of symbol code lengths.
    public CanonicalCode(int[] codeLens) {
        //check basic validity
        if(codeLens == null){
            throw new NullPointerException();
        }
        if(codeLens.length < 2){
            throw new IllegalArgumentException("At least 2 symbols needed");
        }
        for(int cl : codeLens) {
            if(cl < 0){
                throw new IllegalArgumentException("Illegal code length");
            }
        }
        //copy once and check for tree validity
        codeLengths = codeLens.clone();
        Arrays.sort(codeLengths);
        int currentLevel = codeLengths[codeLengths.length - 1];
        int numNodesAtLevel = 0;
        for(int i = codeLengths.length - 1; i >= 0 && codeLengths[i] > 0; i--){
            int cl = codeLengths[i];
            while(cl < currentLevel){
                if(numNodesAtLevel % 2 != 0){
                    throw new IllegalArgumentException("Under-full Huffman code tree");
                }
                numNodesAtLevel /= 2;
                currentLevel--;
            }
            numNodesAtLevel++;
        }
        
        while(currentLevel > 0){
            if(numNodesAtLevel % 2 != 0) {
                throw new IllegalArgumentException("Under-full Huffman code tree");
            }
            numNodesAtLevel /= 2;
            currentLevel--;
        }
        if(numNodesAtLevel < 1){
            throw new IllegalArgumentException("Under-full Huffman code tree");
        }
        if(numNodesAtLevel > 1){
            throw new IllegalArgumentException("Over-full Huffman code tree");
        }
        //copy again
        System.arraycopy(codeLens, 0, codeLengths, 0, codeLens.length);
    }
    
    
    //builds a canonical Huffman code from the specified code tree.
    public CanonicalCode(CodeTree tree, int symbolLimit){
        if(tree == null){
            throw new NullPointerException();
        }
        if(symbolLimit < 2){
            throw new IllegalArgumentException("At least 2 symbols needed");
        }
        codeLengths = new int [symbolLimit];
        buildCodeLengths(tree.root, 0);
    }
    
    
    //recursive helper method for the above constructor
    private void buildCodeLengths(Node node, int depth){
        if(node instanceof InternalNode){
            InternalNode internalNode = (InternalNode)node;
            buildCodeLengths(internalNode.leftChild, depth + 1);
            buildCodeLengths(internalNode.rightChild, depth + 1);
        }else if(node instanceof Leaf){
            int symbol = ((Leaf)node).symbol;
            //CodeTree already has a checked constraint that disallows a symbol in multiple leaves
            if(codeLengths[symbol] != 0){
                throw new AssertionError("Symbol has more than one code");
            }
            if(symbol >= codeLengths.length){
                throw new IllegalArgumentException("Symbol exceeds symbol limit");
            }
            codeLengths[symbol] = depth;
        }else{
            throw new AssertionError("Illegal node type");
        }
    }
    
    
    //returns the symbol for this canonical Huffman code
    public int getSymbolLimit(){
        return codeLengths.length;
    }
    
    
    //returns the code length of the specified symbol value
    //result will be 0 if the symbol has node code,
    //otherwise the result is a positive number
    public int getCodeLength(int symbol){
        if(symbol < 0 || symbol >= codeLengths.length){
            throw new IllegalArgumentException("Symbol out of range");
        }
        return codeLengths[symbol];
    }
    
    
    //returns the canonical code tree for this canonical Huffman code
    public CodeTree toCodeTree(){
        List<Node> nodes = new ArrayList<Node>();
        for(int i = max(codeLengths); i >= 0; i--){
            if(nodes.size() % 2 != 0){
                throw new AssertionError("Violation of canonical code invariants");
            }
            List<Node> newNodes = new ArrayList<Node>();
            
            //add leaves for symbols with positive code length i
            if(i > 0){
                for(int j = 0; j < codeLengths.length; j++){
                    if(codeLengths[j] == i){
                        newNodes.add(new Leaf(j));
                    }
                }
            }
            //Merge pairs of nodes from the previous deeper layer
            for(int j = 0; j < nodes.size(); j += 2){
                newNodes.add(new InternalNode(nodes.get(j), nodes.get(j + 1)));
            }
            nodes = newNodes;
        }
        if(nodes.size() != 1){
            throw new AssertionError("Violation of canonical code invariants");
        }
        return new CodeTree((InternalNode)nodes.get(0), codeLengths.length);
    }
    
    
    //returns the maximum value in the given array, which must have at least 1 element
    private static int max(int [] array){
        int result = array[0];
        for(int x : array){
            result = Math.max(x, result);
        }
        return result;
    }
}
