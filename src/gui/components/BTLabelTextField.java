package gui.components;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;

import conf.Configuration;

public class BTLabelTextField extends BTPanel {

	private static final long serialVersionUID = 1L;

	public BTLabelTextField( final String label, final BTTextField textField ) {
		super( new BorderLayout() );
		
		JLabel lbl = new JLabel( label );

		// Diminuindo tamanho da fonte do label
		if( Configuration.getInstance().isMacTheme() ) {
			Font font = lbl.getFont();
			Font newFont = new Font(font.getName(), font.getStyle(), 12);
			lbl.setFont( newFont );
		}
		
		this.add( lbl, BorderLayout.NORTH );
		this.add( textField );
	}
}
