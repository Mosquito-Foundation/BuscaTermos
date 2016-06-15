package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import configuration.Configuration;
import gui.components.BTButton;
import gui.components.BTGridBagConstraints;
import gui.components.BTMainFrame;
import gui.components.BTPanel;
import gui.template.ReleaseView;
import pojo.Release;
import pojo.ReleaseManager;
import utils.Token;
import version.VersionControl;

public class ChangelogDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public ChangelogDialog( final BTMainFrame owner ) {
		super( owner, ModalityType.APPLICATION_MODAL );
		this.setAlwaysOnTop( owner.isAlwaysOnTop() );
		this.add( this.createComponents() );
		this.showDialog();
	}

	private BTPanel createComponents() {
		BTPanel container = new BTPanel( new BorderLayout(), BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );

		container.add( this.getTextPane(), BorderLayout.CENTER );
		container.add( this.getBottomPane(), BorderLayout.SOUTH );
		
		return container;
	}
	
	private BTPanel getTextPane() {
		BTPanel textFieldContainer = new BTPanel( new BorderLayout() );
		
		final JScrollPane textWrapper = new JScrollPane( this.getReleaseContainer() );
		textWrapper.getVerticalScrollBar().setUnitIncrement( 12 );
		textWrapper.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		textWrapper.setBorder( BorderFactory.createLineBorder( Color.GRAY ) );
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				textWrapper.getVerticalScrollBar().setValue(0);
			}
		});
		
		textFieldContainer.add( textWrapper );
		
		return textFieldContainer;
	}
	
	private BTPanel getReleaseContainer() {
		BTGridBagConstraints layout = new BTGridBagConstraints.Builder().weighty( 0d ).insets( new Insets( 0, 10, 0, 10 ) ).build();
		
		BTPanel container = new BTPanel( new GridBagLayout() );
		container.setBackground(Color.WHITE);

		for ( Release release : ReleaseManager.getInstance().getSortedReleases() ) {
			container.add( new ReleaseView.Builder().version( release.getVersion().toString() ).description( release.getDescription() ).features( release.getFeatures() ).fixes( release.getFixes() ).build(), layout );
		}
		
		// Correção para não haver espaço entre os releaseview, caso soma das alturas seja menor que o tamanho da tela
		container.add( new JLabel(), new BTGridBagConstraints.Builder().anchor( BTGridBagConstraints.SOUTH ).insets( new Insets( 10, 0, 0, 0 ) ).build() );
		
		return container;
	}
	
	private BTPanel getBottomPane() {
		BTPanel bottomPane = new BTPanel( new BorderLayout(), BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );
		bottomPane.add( this.getOkButton() );
		return bottomPane;
	}
	
	private BTButton getOkButton() {
		BTButton okButton = new BTButton( Token.OK );
		okButton.setPreferredSize( new Dimension( 100, 30) );
		okButton.setBorder( BorderFactory.createLineBorder( Color.GRAY ) );
		okButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
		return okButton;
	}
	
	private void showDialog() {
		this.setTitle( Token.CHANGELOG );
		this.setResizable( false );
		this.setSize( 500, 500 );
		this.setLocationRelativeTo( null );
		this.setVisible( true );

	}
	
	private void closeDialog() {
		Configuration.getInstance().setVersion( VersionControl.getInstance().getCurrentVersion().toString() );
		this.dispose();
	}
}
