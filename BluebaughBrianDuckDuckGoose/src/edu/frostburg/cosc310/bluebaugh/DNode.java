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
public class DNode {
    static int noOfLinkedList = 0;
    int data;
    DNode previousNode;
    DNode nextNode;
    DNode(int data){
        this.data = data;
        noOfLinkedList++;
    }
}
