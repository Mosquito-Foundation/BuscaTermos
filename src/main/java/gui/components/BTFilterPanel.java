package gui.components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import utils.Token;

public class BTFilterPanel extends BTPanel {

	private static final long serialVersionUID = 1L;

	private final BTTextField field;
	
	private final BTButton button;
	
	public BTFilterPanel( final BTTextField field, final BTButton button ) {
		super( new BorderLayout(), BorderFactory.createEmptyBorder(0, 0, 10, 0) );
		this.field = field;
		this.button = button;
		this.init();
	}

	private void init() {
		this.add( new JLabel( Token.SEARCH ), BorderLayout.NORTH );
		
		final BTPanel fieldAndButtonContainer = new BTPanel( new BorderLayout() );
		fieldAndButtonContainer.add( this.field );
		fieldAndButtonContainer.add( this.button, BorderLayout.EAST );
		
		this.add( fieldAndButtonContainer );
	}
	
}
