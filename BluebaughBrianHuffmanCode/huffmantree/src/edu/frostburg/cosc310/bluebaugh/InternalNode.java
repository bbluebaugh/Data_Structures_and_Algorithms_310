/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;

/**
 *
 * @author bbluebaugh
 */


public class InternalNode extends Node {
    
    public Node leftChild;
    public Node rightChild;
    
    public InternalNode(Node left, Node right){
        if(left == null || right == null){
            throw new NullPointerException();
        }
        leftChild = left;
        rightChild = right;
    }    
    
}
