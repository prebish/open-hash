import java.io.*;
import java.util.*;

public class HashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;

	public HashSet( int numBuckets )
	{	
		size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	public boolean add( String key )
	{
		int hash = hashOf(key, bucketArray.length); // get hash of key
		
		if (contains(key)) return false;
		
		bucketArray[hash] = new Node(key, bucketArray[hash]);
		size++; 
		
		if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); 
		return true;
	}

	private void upSize_ReHash_AllKeys() // THIS METHOD IS CALLED IN add() METHOD
	{	
		System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
		size, bucketArray.length, bucketArray.length*2  );
						   
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;
		
		
		for (int i = 0; i < bucketArray.length; i++)
		{
			for (Node curr = bucketArray[i]; curr != null; curr = curr.next)
			{
				int hash = hashOf(curr.data, biggerArray.length);
				biggerArray[hash] = new Node(curr.data, biggerArray[hash]);
			}
		}
		bucketArray = biggerArray;
	} // END OF UPSIZE & REHASH
	
	
	public boolean contains(String key) //hash to find what ll to go to
	{	 
		Node curr = bucketArray[hashOf(key, bucketArray.length)];
		
		while (curr != null)
			if (curr.data.equals(key)) return true;
			else curr = curr.next;			
		return false;
	}
	
	public boolean remove(String key)
	{
		int hash = hashOf(key, bucketArray.length);
		
		Node head = bucketArray[hash];
		if (head == null) return false;
		
		if (head.data.equals(key))
		{
			bucketArray[hash] = head.next;
			--size;
			return true;
		}
		
		Node curr = head;
		Node before = null;
		while (curr != null)
		{
			if (curr.data.equals(key)) 
				before.next = curr.next;
				--size;
			before = curr;
			curr = curr.next;
		}	
		return true;	
	}
	
	public boolean isEmpty()
	{
		return size() == 1;
	}
	
	public void clear()
	{
		for (Node head : bucketArray)
			head = null;
		return;
	}
	
	public int size() // Size of what?
	{
		return size;
	}
	
	// H A S H   C O D E   M E T H O D
	public int hashOf(String key, int numBuckets) 
	{
		int hash = 0;
		for (int i = 0; i < key.length(); i++) 
			hash = 2* hash + key.charAt(i); // 31 * hash + key.charAt(i);
		return Math.abs(hash) % numBuckets;
	}

} //END MyHashSet CLASS

class Node
{	
	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	
		this.data = data;
		this.next = next;
	}
}



