package gui.components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Token;

public class BTFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JTextField field;
	
	private final BTButton button;
	
	public BTFilterPanel( final JTextField field, final BTButton button ) {
		super(new BorderLayout());
		this.setBorder( BorderFactory.createEmptyBorder(0, 0, 10, 0));
		this.field = field;
		this.button = button;
		this.init();
	}

	private void init() {
		this.add( new JLabel( Token.SEARCH ), BorderLayout.NORTH );
		
		final JPanel fieldAndButtonContainer = new JPanel( new BorderLayout() );
		fieldAndButtonContainer.add( this.field );
		fieldAndButtonContainer.add( this.button, BorderLayout.EAST );
		
		this.add( fieldAndButtonContainer );
	}
	
}
