/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;

/**
 *
 * @author bwbluebaugh0
 */
public class SLList {
    private int size;
    private SLListNode head;//first SLL node
    private SLListNode tail;//last SLL node
    public SLList(){
	head = tail = null;//refer to null since nothing is in the list
	}
	public void append(String element){
		if(head == null)
                	head = tail = new SLListNode(element, null);
		else
			tail = tail.next = new SLListNode(element, null);
	}
	public String toString(){
		String out = "the SLList contains:\n";
		SLListNode ref = head;
		if(head == null)
			return out + "0 nodes.";
		else
			out += "head -> \t";
		while(ref.next != null)//can also ref.next != tail
                {
			out += ref.data + "\t -> \t";
			ref = ref.next;
		}
                out += ref.data + "\t -> null";
		return out;
        }
        public boolean remove(Object element){
	if(head == null)//check for no items
		return false;
	if(((Comparable)(head.data)).compareTo(element) == 0){
		if(head == tail){
			head = tail = null;
			return true;
		}
		head = head.next;
		return true;
	}
	if(head == tail)
		return false;
	SLListNode ref = head;
	while(ref.next != tail)//stop at the element before tail need to deal with tail specially
	{
		if(((Comparable)(ref.next.data)).compareTo(element) == 0){
			ref.next = ref.next.next;
			return true;
		}
		ref = ref.next;
	}
	if(((Comparable)(tail.data)).compareTo(element) == 0){
		ref.next = null;
		tail = ref;
		return true;
	}
	return false;//did not find what we were looking for
        }
        public void insert(String element){
		SLListNode newNode = new SLListNode(element, null);
		if(size == 0){
			head = tail = newNode;
			size++;
			return;
		}
		SLListNode ref = head;
		if(((Comparable)(ref.data)).compareTo(element) >= 0){
			newNode.next = ref;//could also use head
			head = newNode;
			++size;
			return;
		}
		while(ref.next != null){
			if(((Comparable)(ref.next.data)).compareTo(element) >= 0){
				newNode.next = ref.next;
				ref.next = newNode;
				++size;
				return;
			}
			ref = ref.next;
		}
		tail.next = newNode;
		tail = tail.next;
		++size;
	}
        public String get(int n){
            int size = 1;
            SLListNode holder = head;
            if(size <= 0){
                return null;
            }
            for(int i = 0; i < n; i++){
                holder = holder.next;
            }
            return holder.data;
        }
        public int size(){
            return size;
        }
}
