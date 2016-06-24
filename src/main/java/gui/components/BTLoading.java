package gui.components;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Token;

public class BTLoading extends JDialog  {

	private static final long serialVersionUID = 1L;

	public BTLoading( final Window owner ) {
		super( owner, ModalityType.APPLICATION_MODAL );

		this.init();
	}
	
	private void init() {
		this.createComponents();

		this.setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
		this.pack();
		this.setLocationRelativeTo( this.getOwner() );
	}
	
	private void createComponents() {
		this.setLayout( new BorderLayout() );
		
		final JPanel container = new JPanel( new BorderLayout() );
		container.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		
		container.add( new JLabel( Token.LOADING ) );
		
		this.add( container );
	}

}
