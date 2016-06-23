package gui.template;

import java.awt.Font;

import javax.swing.JTextArea;

class ReleaseChange extends JTextArea {

	private static final long serialVersionUID = 1L;

	public ReleaseChange( final String change ) {
		super( "• " + change );
		this.setLineWrap( true );
		this.setWrapStyleWord( true );
		this.setEditable( false );
		this.setFocusable( false );
		this.setFont( new Font( "Verdana", Font.PLAIN, this.getFont().getSize() ) );
		this.setOpaque(false);
	}
	
}
