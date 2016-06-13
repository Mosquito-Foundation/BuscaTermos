package gui.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import configuration.Configuration;

public class BTButton extends JButton {

	private static final long serialVersionUID = 1L;

	private final String label;
	
	private ImageIcon icon = null;
	
	public BTButton( final String label ) {
		super();
		this.label = label;
		this.init();
	}
	
	public BTButton( final String label, final ImageIcon icon ) {
		super();
		this.label = label;
		this.icon = icon;
		this.init();
	}
	
	private void init() {
		if ( !this.hasIcon() ) {
			this.setIcon( this.icon );
			this.setToolTipText( this.label );
		} else {
			this.setText( this.label );
		}
		this.setFocusable( false );
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.WHITE );
			this.setBorder( BorderFactory.createMatteBorder( 1, 0, 1, 1, Color.GRAY ) );
		}
	}

	public boolean hasIcon() {
		return this.icon == null;
	}
	
}
