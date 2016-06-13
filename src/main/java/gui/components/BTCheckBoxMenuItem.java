package gui.components;

import java.awt.Color;

import javax.swing.JCheckBoxMenuItem;

import configuration.Configuration;

public class BTCheckBoxMenuItem extends JCheckBoxMenuItem {

	private static final long serialVersionUID = 1L;

	public BTCheckBoxMenuItem( final String label ) {
		super( label );
		this.init();
	}
	
	public BTCheckBoxMenuItem( final String label, final boolean isChecked ) {
		super( label, isChecked );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
}
