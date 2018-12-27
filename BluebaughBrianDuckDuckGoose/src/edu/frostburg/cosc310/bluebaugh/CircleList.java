/*
 * Circle List class for the circularly linked list
 * Link List came from 241 decided i could reuse it here  
 * I converted the code I used from the linked list which is why
 * there are comments for some of the stuff so it doesn't interfere
 * @Version 9/21/16
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
/**
 *
 * @author Brian Bluebaugh
 */
public class CircleList {
    static Node current;//pointers not memory locations
    static Node temp;
    static Node root;
    static Node head;
    static File file;
    public void addNode(String name, int age, String gender){//String line){//String name, int age, char gender){
        Node node = new Node(name, age, gender);//name, age, gender);
        if(root == null){
            head = new Node(name, age, gender);//name, age, gender);
            root = node;
            //node.nextNode = null;
            root.nextNode = root;//switch from null to root to make circular
        }
        else{
            current = root;
            //while(current.nextNode != null){
                //current = current.nextNode;
            //}
            while(current.nextNode != root){
                current = current.nextNode;
            }
            current.nextNode = node;
            //node.nextNode = null;
            node.nextNode = root;
        }
    }
    
    public void insertNode(String name, int age, String gender, int after){//String line, int after){//String name, int age, char gender, int after){
        Node node = new Node(name, age, gender);//name, age, gender);
        int ithNode = 1;
        current = root;
        while(after != ithNode){
            current = current.nextNode;
            ithNode++;
        }
        temp = current.nextNode;
        current.nextNode = node;
        node.nextNode = temp;
    }
    
    public void deleteNode(int nodeToBeDeleted){
        int ithNode = 1;
        current = root;
        if(nodeToBeDeleted == 1){
            //root = current.nextNode;
            temp = root.nextNode;
            while(temp.nextNode != root){
                temp = temp.nextNode;
            }
            temp.nextNode = temp.nextNode.nextNode;
            root = current.nextNode;
        }
        else{
            while(ithNode != nodeToBeDeleted - 1){
                current = current.nextNode;
                ithNode++;
            }
            current.nextNode = current.nextNode.nextNode;
        }
        Node.noOfLinkedList--;
    }
    
    public void print(){
        current = root;
        boolean arrow = true;
        for(int i = 0; i < 15; i++){
            
            System.out.print((arrow) ? "|" + current.name + " " + current.age + " " + current.gender + " " + current.speed + "|" : "-->" + "|" + current.name + " " + current.age + " " + current.gender + " " + current.speed + "|");
            arrow = false;
            current = current.nextNode;
        }
        System.out.println();
        System.out.println("The head node is: " + head.name);
        System.out.println("The root node is: " + root.name);
    }
    //used for initial testing
    //public static void main(String[] args){
        //CircleList list = new CircleList();
        //list.addNode("joe", 4, 'm');
        //list.addNode("cameron", 15, 'm');
        //list.addNode("jane", 10, 'f');
        //list.addNode("bonny", 8, 'f');
        //list.print();
    //}
    
}
