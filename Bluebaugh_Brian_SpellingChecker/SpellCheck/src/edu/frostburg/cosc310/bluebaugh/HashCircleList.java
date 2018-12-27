/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
/**
 *
 * @author brian
 */
public class HashCircleList {
    static HashNode root;
    static HashNode current;
    static HashNode temp;
    static HashNode head;
    static File file;
    int size;
    
    public void addHashNode(String wordToBeHashed){
        HashNode node = new HashNode(wordToBeHashed);
        if(root == null){
            head = new HashNode(wordToBeHashed);
            root = node;
            root.nextNode = root;
            size++;
        }
        else{
            HashNode newNode = new HashNode(wordToBeHashed);
            newNode.nextNode = head.nextNode;
            head.nextNode = newNode;

            size++;
        }
    }
    
    public void insertHashNode(String wordToBeHashed, int after){
        int ithHashNode = 1;
        HashNode node = new HashNode(wordToBeHashed);
        current = root;
        while(ithHashNode != after){
            current = current.nextNode;
            ithHashNode++;
        }
        temp = current.nextNode;
        current.nextNode = node;
        node.nextNode = temp;
    }
    public void deleteHashNode(int nodeNumber){
        int ithHashNode = 1;
        current = root;
        if(nodeNumber == 1){
            temp = root.nextNode;
            while(temp.nextNode != root){
                temp = temp.nextNode;
            }
            temp.nextNode = temp.nextNode.nextNode;
            root = current.nextNode;
            size--;
        }
        else{
            while(ithHashNode != nodeNumber - 1){
                current = current.nextNode;
                ithHashNode++;
            }
            current.nextNode = current.nextNode.nextNode;
        }
        --HashNode.noOfLinkedList;
    }
    
    public void rotate(){
        this.current.nextNode = this.current;
        
    }
    
    public int size(){
        return size;
    }
    public String get(String thing){
        for(int i = 0; i < this.size; i++){
            if (this.current.word.equals(thing)){
                return this.current.word;
            } else {
                this.rotate();
            }
        }
        return null;
    }
}
