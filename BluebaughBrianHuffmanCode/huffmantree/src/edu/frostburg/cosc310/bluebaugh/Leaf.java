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
public class Leaf extends Node{
    public int symbol;
    
    public Leaf(int sym){
        if(sym < 0){
            throw new IllegalArgumentException("Symbol value must be non-negativ");
        }
        symbol = sym;
    }
    
}
