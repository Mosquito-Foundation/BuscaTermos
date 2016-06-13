package gui.components;

import java.awt.Color;

import javax.swing.JDialog;

import configuration.Configuration;
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
		this.setAlwaysOnTop( MainFrame.getInstance().isAlwaysOnTop() );
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.setResizable( false );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.getContentPane().setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
	
	public void output() {
		this.pack();
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}
}
