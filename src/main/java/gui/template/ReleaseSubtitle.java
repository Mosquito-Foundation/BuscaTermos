package gui.template;

import java.awt.Font;

import javax.swing.JLabel;

class ReleaseSubtitle extends JLabel {

	private static final long serialVersionUID = 1L;

	public ReleaseSubtitle( final String subtitle ) {
		super( subtitle );
		this.setFont( new Font( "Verdana", Font.BOLD, 12 ) );
	}
}
