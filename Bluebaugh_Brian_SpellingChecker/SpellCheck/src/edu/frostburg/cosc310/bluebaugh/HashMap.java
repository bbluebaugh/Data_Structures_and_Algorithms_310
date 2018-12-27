/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.util.*;
import edu.frostburg.cosc310.bluebaugh.*;

/**
 *
 * @author brian
 */
public class HashMap implements BrianADT {
    int mapLength = 75079;//length of the hashmap is a prime number
    SLList[] array = new SLList[mapLength];
    SLList list = new SLList();//loop through array to make a new hash circle list for each spot
    
    public HashMap(){
        for(int i = 0; i < array.length; i++){
            array[i] = new SLList();
        }
    }
    //find where the index is
    @Override
    public String get(String wordInput) {
       int wordHash = this.hash(wordInput);
       if (array[wordHash].size() == 0){
           return null;
       }
       for(int i = 0; i < array[wordHash].size(); ++i){
          if(array[wordHash].get(i).equals( wordInput)){
              return wordInput;
          }
       }
       return null;
    }
    //put the string in the map and bucket
    @Override
    public void put(String wordInput) {
        int wordHash = this.hash(wordInput);
        array[wordHash].insert(wordInput);
    }
    
    @Override//This was giving me problems so i decided to comment it out and look at it later to try and fix it
    public boolean remove(String wordInput) {
        /*
        int wordHash = this.hash(wordInput);
        for(int i = 0; i < list.size(); i++){
            if(array[wordHash].get() == wordInput){
                array[wordHash].deleteHashNode(wordHash);
                return true;
            }
        }
        return false;
        */
        return false;
    }
    
    @Override
    public int size() {
        return mapLength;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void entrySet() {
        
    }

    @Override
    public void keySet() {
        
    }

    @Override
    public void values() {
        
    }
    //hash the word with the key and use absolute value to keep positive
    public int hash(String word){
       int key;
       key = Math.abs(word.hashCode());
       return key % mapLength;
    }
}
