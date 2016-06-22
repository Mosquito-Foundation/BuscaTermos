package gui.components;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BTLabelTextField extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField textField;
	
	private String label;
	
	public BTLabelTextField( final String label, final JTextField textField ) {
		super( new BorderLayout() );
	
		this.label = label;
		this.textField = textField;
		
		this.add( new JLabel( label ), BorderLayout.NORTH );
		this.add( textField );
	}
	
	public JTextField getTextField() {
		return this.textField;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
