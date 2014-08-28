package com.erich0929.HGTECH.Component;

import com.erich0929.HGTECH.Data.*;

public class TreeComp <Component> implements TreeWidgets {
	private HashTree tree = null;
	
	public TreeComp () {
		throw new UnsupportedOperationException (
				"You shoud initiate a instance using your own UI Component");
	}
	
	public TreeComp (Component rootItem) {
		tree = new HashTree (rootItem);
	}
	
	public Component addComponent (String selector, String id, Component comp) {
		return (Component) tree.append(selector, id, comp);
	}
	
	public Component getComponent (String selector) {
		return (Component) tree.get(selector);
	}
	
	public void display (TreeWidgets.Exporter exporter) {
		exporter.initiate ();
		exporter.display (tree);
		exporter.end ();
	}
	public void load (TreeWidgets.Importer importer) {
		
	}

}