package gui.components;

import java.awt.Color;

import javax.swing.JMenu;

import conf.Configuration;

public class BTMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	public BTMenu( final String label ) {
		super( label );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
			this.setOpaque( true );
		}
	}

}
