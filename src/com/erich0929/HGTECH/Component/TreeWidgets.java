package com.erich0929.HGTECH.Component;

import com.erich0929.HGTECH.Data.*;

public interface TreeWidgets {
	public void display (TreeWidgets.Exporter exporter);
	public void load (TreeWidgets.Importer importer);
	
	public interface Exporter {
		public void initiate ();
		public void display (HashTree tree);
		public void end ();
	}
	
	public interface Importer {
		
	}
}