/*
 * Node class used for the linked list to point to data I.E its not a variable
 * 
 * 
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
/**
 *
 * @author bbluebaugh
 */
public class Node {
    static int noOfLinkedList = 0;
    int age;//age of kid
    String name;//name of kid
    double speed;//how fast (probably Sanic(sonic) fast)
    String gender;//which gender biologically assuming gender associated with names so no BM
    Node nextNode;//next node to be looked at
    String line;
    
    Node(String myName, int myAge, String myGender){//String myLine){//String myName, int myAge, char myGender){
        name = myName;
        age = myAge;
        gender = myGender;
        speed = age * 10.3;
        noOfLinkedList++;
        //line = myLine;
    }
    
}
