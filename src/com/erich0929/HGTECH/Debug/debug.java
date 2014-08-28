package com.erich0929.HGTECH.Debug;

import com.erich0929.HGTECH.Data.*;
import com.erich0929.HGTECH.Data.HashTree;
import com.erich0929.HGTECH.Component.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class debug {
	
	/*
	 * ------------------------------------- 
	 * The following lines is used to debug.
	 * -------------------------------------
	 */
	
	public static void main (String [] args) {
		//NodeTest ();
		//testTree ();
		//testTreeComp ();
		testTraverse ();
	}
	
	public static void testTraverse () {
		HashTree tree = new HashTree ("I'm a root!");
		tree.append("", "class1", "Im'm a class1.");
		tree.append("", "class2", "I'm a class2.");
		tree.append("", "class3", "I'm a class3");
		tree.append("class1", "class1-1", "I'm a class 1-1.");
		tree.append("class1", "class1-2", "I'm a class 1-2.");
		tree.append("class2", "class2-1", "I'm a class 2-1.");
		tree.append("class2 class2-1", "class2-1-1", "I'm a class 2-1-1.");
		tree.append("class3", "class3-1", "I'm a class 3-1.");
		tree.traverse(new HashTree.Advice () {
			public void advice (String id, Object item) {
				System.out.println ("<id=\"" + id + "\" value=\"" + (String) item + "\">");
			}
		}, new HashTree.Advice () {
			public void advice (String id, Object item) {
				System.out.println ("</id=\"" + id + "\">");
			}
		});
		
	}
	
	public static void testTreeComp () {
		//TreeComp comp = new TreeComp (); // throw UnsupportedOperationException
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("SWT Tree Test");
		
		Tree root;
		TreeItem brunch;
		TreeItem item;
		
		TreeComp <Widget> comp = new TreeComp (new Tree (shell, SWT.CHECK | SWT.V_SCROLL | SWT.H_SCROLL));
		root = (Tree) comp.getComponent ("");
		
		item = (TreeItem) comp.addComponent ("", "class1", new TreeItem ((Tree) root, SWT.NONE));
		item.setText("class1");
		brunch = (TreeItem) comp.getComponent("class1");
		item = (TreeItem) comp.addComponent ("class1", "class1-1", new TreeItem (brunch, SWT.NONE));
		item.setText("class1-1");
		item = (TreeItem) comp.addComponent ("class1", "class1-2", new TreeItem (brunch, SWT.NONE));
		item.setText("class1-2");
		
		item = (TreeItem) comp.addComponent ("", "class2", new TreeItem ((Tree) root, SWT.NONE));
		item.setText("class2");
		brunch = (TreeItem) comp.getComponent("class2");
		item = (TreeItem) comp.addComponent ("class2", "class2-1", new TreeItem (brunch, SWT.NONE));
		item.setText("class2-1");
		item = (TreeItem) comp.addComponent ("class1", "class2-2", new TreeItem (brunch, SWT.NONE));
		item.setText("class2-2");
		
		item = (TreeItem) comp.addComponent ("", "class3", new TreeItem ((Tree) root, SWT.NONE));
		item.setText("class3");
		brunch = (TreeItem) comp.getComponent("class3");
		item = (TreeItem) comp.addComponent ("class3", "class3-1", new TreeItem (brunch, SWT.NONE));
		item.setText("class3-1");
		item = (TreeItem) comp.addComponent ("class3", "class3-2", new TreeItem (brunch, SWT.NONE));
		item.setText("class3-2");
		
		shell.setSize(240, 120);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
	
	public static void testTree () {
		HashTree tree = new HashTree ("I'm a root!");
		tree.append("", "class1", "Im'm a class1.");
		tree.append("", "class2", "I'm a class2.");
		tree.append("", "class3", "I'm a class3");
		tree.append("class1", "class1-1", "I'm a class 1-1.");
		tree.append("class1", "class1-2", "I'm a class 1-2.");
		tree.append("class2", "class2-1", "I'm a class 2-1.");
		tree.append("class2 class2-1", "class2-1-1", "I'm a class 2-1-1.");
		tree.append("class3", "class3-1", "I'm a class 3-1.");
		tree.append("noclass", "failclass", "It will not work."); //It's invalid selector.
		//String res = (String) tree.get("");
		//String res = (String) tree.get ("class1");
		//String res = (String) tree.get ("class2 class2-1 class2-1-1");
		//String res = (String) tree.get ("noclass failclass"); // return null
		String res = (String) tree.remove("class3 class3-1");
		System.out.println (res);
		System.out.println ((String) tree.get("class3")); // "I'm a class3"
		System.out.println ((String) tree.get("class3 class3-1")); // null
	}
	
	/* tested at fist time.
	public static void NodeTest () {
		Node root = new Node ("I truly hope my family's happiness.");
		root.add("my wish", "my family's happiness.");
		String res = (String) root.get ("my wish");
		if (res != null) {
			System.out.println (res);
		} else {
			System.out.println ("null pointer.");
		}
	}
	*/
}
	