package gui.components;

import java.awt.BorderLayout;

import javax.swing.JLabel;



public class BTLabelTextField extends BTPanel {

	private static final long serialVersionUID = 1L;

	public BTLabelTextField( final String label, final BTTextField textField ) {
		super( new BorderLayout() );
		
		JLabel lbl = new JLabel( label );
	
		this.add( lbl, BorderLayout.NORTH );
		this.add( textField );
	}
}
