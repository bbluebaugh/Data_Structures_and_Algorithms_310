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
public class FrequencyTable {
    private int[] frequencies;
    
    
    //makes a frequency table from the specified array of frequencies
    public FrequencyTable(int[] freqs){
        if(freqs == null){
            throw new NullPointerException();
        }
        if(freqs.length < 2){
            throw new IllegalArgumentException("At least 2 symbols needed");
        }
        frequencies = freqs.clone();//defensive copy
        for(int x : frequencies){
            if(x < 0){
                throw new IllegalArgumentException("Negative frequency");
            }
        }
    }
    
    
    //returns number of symbolsin the frequency table, will be at least 2
    public int getSymbolLimit(){
        return frequencies.length;
    }
    
    
    //returns the frequency of the specified symbol in this frequency table.
    //Result will always be non-negative
    public int get(int symbol){
        checkSymbol(symbol);
        return frequencies[symbol];
    }
    
    
    //sets the frequency of the specified symbol in this frequency table to the specified value
    public void set(int symbol, int freq){
        checkSymbol(symbol);
        if(freq < 0){
            throw new IllegalArgumentException("Negative frequency");
        }
        frequencies[symbol]++;
    }
    
    
    //increments the frequency of the specified symbol in the table
    public void increment(int symbol){
        checkSymbol(symbol);
        if(frequencies[symbol] == Integer.MAX_VALUE){
            throw new IllegalStateException("Max frequency reached");
        }
        frequencies[symbol]++;
    }
    
    
    //returns if 0 <= symbol < frequencies.length, otherwise throws an exception
    private void checkSymbol(int symbol){
        if(symbol < 0 || symbol >= frequencies.length){
            throw new IllegalArgumentException("Symbol out of range");
        }
    }
    
    
    //returns a string representation of the table
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < frequencies.length; i++){
            sb.append(String.format("d\t%d%n, i, frequencies[i"));
        }
        return sb.toString();
    }
    
    
    //returns a code tree that is obpimal for the symbol frequencies in the table
    public CodeTree buildCodeTree(){
        //if two nodes have same freq then tie is broken
        //by which tree contains lowest symbol.
        Queue<NodeWithFrequency> pqueue = new PriorityQueue<NodeWithFrequency>();
        
        //add leaves for symbols with non-zero frequency
        for(int i = 0; i < frequencies.length; i++){
            if(frequencies[i] > 0){
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, frequencies[i]));
            }
        }
        //pad with zero-frequency symbols until queue has at least 2 items
        for(int i = 0; i < frequencies.length && pqueue.size() < 2; i++){
            if(frequencies[i] == 0){
                pqueue.add(new NodeWithFrequency(new Leaf(i), i, 0));
            }
        }
        if(pqueue.size() < 2){
            throw new AssertionError();
        }
        //repeatedly tie together two nodes with the lowest frequency
        while(pqueue.size() > 1){
            NodeWithFrequency x = pqueue.remove();
            NodeWithFrequency y = pqueue.remove();
            pqueue.add(new NodeWithFrequency(new InternalNode(x.node, y.node), Math.min(x.lowestSymbol, y.lowestSymbol), x.frequency + y.frequency));
        }
        //returns the remaining node
        return new CodeTree((InternalNode)pqueue.remove().node, frequencies.length);
    }
    
    
    //helpder for buildCodeTree()
    private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {
        public final Node node;
        public final int lowestSymbol;
        public final long frequency;//using long prevents overflow
        
        public NodeWithFrequency(Node nd, int lowSym, long freq){
            node = nd;
            lowestSymbol = lowSym;
            frequency = freq;
        }
        
        //sort by ascending frequency, breaking ties by ascending symbol value
        public int compareTo(NodeWithFrequency other){
            if(frequency < other.frequency){
                return -1;
            }
            else if(frequency > other.frequency){
                return 1;
            }
            else if(lowestSymbol < other.lowestSymbol){
                return -1;
            }
            else if(lowestSymbol > other.lowestSymbol){
                return 1;
            }
            else 
                return 0;
        }
    }
}
