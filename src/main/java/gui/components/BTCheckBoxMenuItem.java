package gui.components;

import javax.swing.JCheckBoxMenuItem;

public class BTCheckBoxMenuItem extends JCheckBoxMenuItem {

	private static final long serialVersionUID = 1L;

	public BTCheckBoxMenuItem( final String label ) {
		super( label );
	}
	
	public BTCheckBoxMenuItem( final String label, final boolean isChecked ) {
		super( label, isChecked );
	}
}
