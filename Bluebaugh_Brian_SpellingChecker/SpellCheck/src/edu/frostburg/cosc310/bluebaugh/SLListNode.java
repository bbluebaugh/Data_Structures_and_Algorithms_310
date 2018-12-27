
package edu.frostburg.cosc310.bluebaugh;

/**
 *
 * @author bwbluebaugh0
 */
public class SLListNode//single linked list
{
	public String data;
	public SLListNode next;
	public SLListNode(String d, SLListNode n){
		data = d;
		next = n;
	}
	private SLListNode head;//first SLL node
	private SLListNode tail;//last SLL node
}
