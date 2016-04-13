package gui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import conf.Configuration;

public class BTTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public BTTabbedPane() {
		super();
		this.init();
	}
	
	private void init() {
		this.setFocusable( false );
		this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
}
