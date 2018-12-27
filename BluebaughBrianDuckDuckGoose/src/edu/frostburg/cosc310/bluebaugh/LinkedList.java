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
/*
public class LinkedList {
    static Node current;//pointers not memory locations
    static Node temp;
    static Node root;
    
    public void addNodes(int data){
        Node node = new Node(data);
        if(root == null){
            root = node;
            node.nextNode = null;
        }
        else{
            current = root;
            while(current.nextNode != null){
                current = current.nextNode;
            }
            current.nextNode = node;
            node.nextNode = null;
        }
    }
    
    public void insertNode(int data, int after){
        Node node = new Node(data);
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
            root = current.nextNode;
        }
        else{
            while(ithNode != nodeToBeDeleted - 1){
                current = current.nextNode;
                ithNode++;
            }
            current.nextNode = current.nextNode.nextNode;
        }
    }
    
    public void print(){
        current = root;
        boolean arrow = false;
        while(current != null){
            System.out.print((arrow) ? "-->" + current.data + "|" : "|" + current.data + "|");
            arrow = false;
            current = current.nextNode;
        }
    }
    //public static void main(String[] args){
        //LinkedList list = new LinkedList();
        
        //list.addNode(1);
        //list.addNode(2);
        //list.addNode(3);
        //list.addNode(4);
        //list.addNode(5);
        //list.addNode(6);
        
        //list.insertNode(43, 2);
        
        //list.addNode(7);
        
        //list.print();
        
        //System.out.println();
        //System.out.println("The number of nodes in the Linked List is " + Node.noOfLinkedList);
    //}
            
}
*/
