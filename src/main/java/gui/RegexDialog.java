package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import configuration.Configuration;
import gui.components.BTDialog;
import utils.Token;

public class RegexDialog extends BTDialog {

	private static final long serialVersionUID = 1L;

	public RegexDialog() {
		super();
		super.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		
		this.setLayout( new BorderLayout() );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.getContentPane().setBackground( Color.decode( "#E8F0F7" ) );
		}
		
		this.add( this.getMessage(), BorderLayout.CENTER );
		this.add( this.getButton(), BorderLayout.SOUTH );
		
		this.output();
	}

	private JLabel getMessage() {
		JLabel message = new JLabel( Token.REGULAR_EXPRESSIONS_MESSAGE );
		message.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );
		message.setFont( new Font("Dialog", Font.PLAIN, 13) );
		return message;
	}
	
	private JPanel getButton() {
		JButton btn = new JButton( "OK" );
		btn.setSize(10, 10);
		btn.setFocusable( false );
		btn.setBackground( Color.WHITE );
		btn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getInstance().dispose();
			}
		});
		
		JPanel btnPane = new JPanel();
		if ( Configuration.getInstance().isDefaultTheme() ) {
			btnPane.setBackground( Color.decode( "#E8F0F7" ) );
		}
		btnPane.add( btn );
		
		return btnPane;
	}
	
	private RegexDialog getInstance() {
		return this;
	}
	
}
