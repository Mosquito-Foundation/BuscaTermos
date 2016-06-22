package gui.components;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;

import utils.IconManager;

public class BTLoading extends JDialog  {

	private static final long serialVersionUID = 1L;

	public BTLoading( final Window owner ) {
		super( owner, ModalityType.APPLICATION_MODAL );

		this.init();
	}
	
	private void init() {
		this.createComponents();

		this.setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
		this.setUndecorated( true );
		this.pack();
		this.setLocationRelativeTo( null );
	}
	
	private void createComponents() {
		this.setLayout( new BorderLayout() );
		this.add( new JLabel( IconManager.getInstance().getIcon( "loading/loading.gif" ) ) );
	}

}
