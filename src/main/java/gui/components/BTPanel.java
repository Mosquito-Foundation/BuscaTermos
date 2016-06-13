package gui.components;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.Border;

import configuration.Configuration;

public class BTPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BTPanel( LayoutManager layout ) {
		super( layout );
		this.init();
	}
	
	public BTPanel( LayoutManager layout, Border border ) {
		super( layout );
		this.setBorder( border );
		this.init();
	}
	
	private void init() {
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#C8DDF2" ) );
		}
	}
}
