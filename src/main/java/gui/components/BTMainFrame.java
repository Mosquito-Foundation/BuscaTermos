package gui.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import configuration.Configuration;
import gui.ChangelogDialog;
import utils.IconManager;
import utils.Token;
import version.VersionControl;

public class BTMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public BTMainFrame() {
		super();
		this.init();
	}
	
	private void init() {
		this.setLayout( new GridLayout(1, 2) );
		this.setTitle( Token.TITLE );
		this.setIconImage( IconManager.getInstance().getIcon( "icons/icon3.png" ).getImage() );
		this.setSize( Configuration.getInstance().getDimension() );
		
		if ( Configuration.getInstance().isMaximized() ) {
			this.setExtendedState( JFrame.MAXIMIZED_BOTH );
		}
		
		if( Configuration.getInstance().isDefaultTheme() ) {
			this.getContentPane().setBackground( Color.decode( "#E8F0F7" ) );
		}
		
		this.setLocationRelativeTo( null );
		this.setAlwaysOnTop( Configuration.getInstance().isAlwaysOnTop() );
		
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing( final WindowEvent windowEvent ) {
		    	Configuration.getInstance().save();
		        System.exit( 0 );
		    }
		});
	}

	public boolean isMaximized() {
		return this.getExtendedState() == JFrame.MAXIMIZED_BOTH;
	}

	@Override
	public void setVisible( final boolean b ) {
		super.setVisible( b );
		if ( VersionControl.getInstance().isNewerVersion() ) {
			new ChangelogDialog( this );
		}
	}
	
}
