package gui.components;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import control.TabController;

public class BTTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public BTTabbedPane() {
		super();
		this.init();
	}
	
	private void init() {
		this.addMouseListener( new TabController( this ) );
		this.setFocusable( false );
		this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );
	}
}
