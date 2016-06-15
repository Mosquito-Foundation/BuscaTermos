package gui.components;

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
import gui.MainFrame;

public class BTDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private String message;

	public BTDialog( final MainFrame frame , String message) {
		super( frame );
		this.init();
		this.setLayout( new BorderLayout() );
		
		this.output();
	}
	
	public BTDialog() {
		super();
		this.init();
	}
	
	private void init() {
		this.setMessage(message);
		this.add( this.getMessage(), BorderLayout.CENTER );
		this.add( this.getButton(), BorderLayout.SOUTH );
		this.setAlwaysOnTop( MainFrame.getInstance().isAlwaysOnTop() );
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.setResizable( false );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.getContentPane().setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
	
	private JLabel getMessage() {
		JLabel message = new JLabel(this.message);
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
				dispose();
			}
		});
		
		JPanel btnPane = new JPanel();
		if ( Configuration.getInstance().isDefaultTheme() ) {
			btnPane.setBackground( Color.decode( "#E8F0F7" ) );
		}
		btnPane.add( btn );
		
		return btnPane;
	}

	public void output() {
		this.pack();
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
}
