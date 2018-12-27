/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.util.*;
/**
 *
 * @author brian
 */
public class SpellCheck {
    String word;
    HashMap hash;
    ArrayList results = new ArrayList();
    
    public SpellCheck(HashMap hm){
        hash = hm;
    }
    
    //possibly missarranged letters so switch each to see if that gives a word I.E. pto ->pot
    public ArrayList dyslexic(String word){
        
        int size = word.length();
        for(int i = 0; i < size; i++){
            for(int j = 97; j <= 122; j++){
                char c = (char)j;
                //System.out.println(word.replace(word.charAt(i), c));
                if (hash.get(word.replace(word.charAt(i), c)) != null){
                    
                    results.add(word.replace(word.charAt(i), c));
                }
            }
        }
        return results;
    }
    //possibly missing a letter so add a letter in between letters already in the word I.E. pts ->pets/pots
    public ArrayList addLetter(String word){
        int size = word.length();
        for(int i = 0; i < size + 1; i++){
            for(int j = 97; j <= 122; j++){
                char c = (char)j;
                String str = word.substring(0,i) + c + word.substring(i, size);
                if (hash.get(str) != null){
                    results.add(str);
                }
            }
        }
        return results;
    }
    //possibly too many letters so try taking out each and checking it I.E. potse -> pots
    public ArrayList removeLetter(String word){
        int size = word.length();
        for(int i = 0; i < size; i++){
            String temp = word;
            if(i == 0){
                temp = temp.substring(1,size);
            }
            if (i == size){
                temp = temp.substring(0,size-1);
            }
            else if(i == size - 1){
                temp = temp.substring(0, i) + temp.substring(size-1, size-1);
            }
            else if (i == size - 2){
                temp = temp.substring(0, i) + temp.substring(size-2, size-1);
            }
            else{
                temp = temp.substring(0,i) + temp.substring(i+2, size-1);
            }
            
            if(hash.get(temp) != null){
                results.add(temp);
            }
        }
        return results;
    }
}
