/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;

/**
 *
 * @author brian
 */
public class HashNode {
    static int noOfLinkedList =- 1;
    public String word;
    HashNode nextNode;
    HashNode(String wordToBeHashed){
        word = wordToBeHashed;
        noOfLinkedList = noOfLinkedList + 1;
    }
}
