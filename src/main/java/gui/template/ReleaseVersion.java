package gui.template;

import java.awt.Font;

import javax.swing.JLabel;

class ReleaseVersion extends JLabel {

	private static final long serialVersionUID = 1L;

	public ReleaseVersion( final String version ) {
		super( version );
		this.setFont( new Font( "Verdana", Font.BOLD, 18 ) );
	}
}
