package gui.components;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class BTPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BTPanel( LayoutManager layout ) {
		super( layout );

	}
	
	public BTPanel( LayoutManager layout, Border border ) {
		super( layout );
		this.setBorder( border );
	}
}
