package gui.components;

import java.awt.BorderLayout;

import javax.swing.JLabel;



public class BTLabelTextField extends BTPanel {

	private static final long serialVersionUID = 1L;

	private BTTextField textField;
	
	private String label;
	
	public BTLabelTextField( final String label, final BTTextField textField ) {
		super( new BorderLayout() );
	
		this.label = label;
		this.textField = textField;
		
		this.add( new JLabel( label ), BorderLayout.NORTH );
		this.add( textField );
	}
	
	public BTTextField getTextField() {
		return this.textField;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
