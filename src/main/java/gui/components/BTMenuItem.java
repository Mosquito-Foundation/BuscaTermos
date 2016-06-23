package gui.components;

import javax.swing.Icon;
import javax.swing.JMenuItem;

public class BTMenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;

	public BTMenuItem( final String label ) {
		super( label );
	}
	
	public BTMenuItem( final String label, final Icon icon ) {
		super( label, icon );
	}
}
