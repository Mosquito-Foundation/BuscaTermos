package gui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;

import conf.Configuration;

public class BTMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public BTMenuBar() {
		super();
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
			this.setBorder( BorderFactory.createEmptyBorder() );
		}
	}
	
}
