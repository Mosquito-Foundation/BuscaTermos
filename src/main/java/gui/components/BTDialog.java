package gui.components;

import javax.swing.JDialog;

import gui.MainFrame;

public class BTDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public BTDialog( final MainFrame frame ) {
		super( frame );
		this.init();
	}
	
	public BTDialog() {
		super();
		this.init();
	}
	
	private void init() {
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.setResizable( false );
	}
	
	public void output() {
		this.pack();
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}
}
