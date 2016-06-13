package gui.components;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JMenuItem;

import configuration.Configuration;

public class BTMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public BTMenuItem( final String label ) {
		super( label );
		this.init();
	}
	
	public BTMenuItem( final String label, final Icon icon ) {
		super( label, icon );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
}
