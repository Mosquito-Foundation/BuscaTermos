package gui.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import conf.Configuration;

public class BTTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	public BTTextField() {
		super();
		this.init();
	}
	
	public BTTextField( final boolean isEditable ) {
		super();
		this.setEditable( isEditable );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBorder( BorderFactory.createLineBorder( Color.GRAY ) );
		}
	}
}
