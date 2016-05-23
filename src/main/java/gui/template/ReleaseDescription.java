package gui.template;

import java.awt.Font;

import javax.swing.JLabel;

class ReleaseDescription extends JLabel {

	private static final long serialVersionUID = 1L;

	public ReleaseDescription( final String description ) {
		super( description );
		this.setFont( new Font( "Verdana", Font.PLAIN, 12 ) );
	}
	
}
