package gui.components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BTLanguagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BTLanguagePanel() {
		super();
		this.init();
	}
	
	private void init() {
		this.setLayout( new BorderLayout() );
		this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );
	}
	
}
