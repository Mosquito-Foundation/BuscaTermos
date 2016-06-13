package gui.components;

import java.awt.Color;

import javax.swing.JRadioButtonMenuItem;

import configuration.Configuration;

public class BTRadioButtonMenuItem extends JRadioButtonMenuItem {

	private static final long serialVersionUID = 1L;

	public BTRadioButtonMenuItem( final String label, final boolean selected ) {
		super( label, selected );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
	
}
