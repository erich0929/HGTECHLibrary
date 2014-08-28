package com.erich0929.HGTECH.Data;

import java.util.*;
import com.erich0929.HGTECH.tools.*;

/* class : HashTree 
 * It's used for a data structure to be wrapped by the class : HashTreeComp.
 */

public class HashTree {

	/* class : Node
	 * 
	 */
	private static class Node {
		private String key;
		private Object item;
		private Hashtable <String, Node> table = new Hashtable <String, Node> ();
		
		/*
		 * need to be refactoried.
		 */
		public Node () {
			this.key = null;
			this.item = null;
		}
		
		public Node (String key, Object item) {
			this.key = key;
			this.item = item;
		}
		
		public Object add (String key, Object item) {
			Node res = new Node (key, item);
			table.put (key, res);
			return (res != null) ? res.item : null;
		}
		
		public Object remove (String key) {
			Node res = table.remove (key);
			return (res != null) ? res.item : null;
		}
		
		public Node find (String key) {
			Node res = table.get (key);
			return (res != null) ? res : null;
		}
		
		public Object get (String key) {
			Node res = table.get (key);
			return (res != null) ? res.item : null;
		}
	}
	
	/*
	 * Interface : Advise
	 */
	
	public interface Advice {
		public void advice (String id, Object item);
	}
	
	private Node root = null;
	
	public HashTree () {
		root = new Node ("root", null);
	}
	
	public HashTree (Object rootItem) {
		root = new Node ("root", rootItem);
	}
	
	public HashTree (String rootId, Object rootItem) {
		root = new Node (rootId, rootItem);
	}
	
	/*
	 * function : add 
	 * note : 'first argument : selector' is a key sequence like this : 'fooClass barItem'
	 * note : You can specify a root's ID by calling the constructor : HashTree (String rootId, Object rootItem), but
	 * 		you should give selector argument which not include root's ID to the function : append
	 */
	
	public Object append (String selector, String id, Object item) {
		Node cursor = root;
		if (selector != "") {
			String [] keys = selector.split("\\s+");
			Iterator iter = new ArrayIterator (keys);
			
			while (iter.hasNext () && (cursor != null)) {
				String key = (String) iter.next ();
				cursor = cursor.find (key);
			}
		}
		return (cursor != null) ? cursor.add(id, item) : null;
	}
	
	public Object get (String selector) {
		Node cursor = root;
		if (selector != "") {
			String [] keys = selector.split("\\s+");
			Iterator iter = new ArrayIterator (keys);
			
			while (iter.hasNext () && (cursor != null)) {
				String key = (String) iter.next ();
				cursor = cursor.find (key);
			}
		}
		return (cursor != null) ? cursor.item : null;
	}
	
	public Object remove (String selector) {
		Node cursor = root;
		String [] keys = null;
		String key = null;
		int i = 0;
		if (selector != "") {
			keys = selector.split("\\s+");
			//Iterator iter = new ArrayIterator (keys);
			;
			for (i = 0; i < keys.length -1 ; i++) {
				key = keys [i];
				cursor = cursor.find(key);
			}
		}
		return (cursor != null) ? cursor.remove(keys [i++]) : null;
	}
	
	public void traverse (Advice before, Advice after) {
		traverseInOrder (root, before, after);
	}
	
	public void traverseInOrder (Node current, Advice before, Advice after) {
		if (current == null) {
			return;
		}
		
		before.advice (current.key, current.item);
		ArrayList <String> arrkey = new ArrayList (current.table.keySet());
		Collections.reverse(arrkey);
		Iterator <String> iter = arrkey.iterator ();
		
		while (iter.hasNext ()) {
			traverseInOrder (current.table.get(iter.next ()), before, after);
		}
		after.advice (current.key, current.item);
	}

}