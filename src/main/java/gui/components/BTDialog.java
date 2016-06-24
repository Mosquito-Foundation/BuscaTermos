package gui.components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.MainFrame;
import gui.shortcut.ShortcutFactory;
import utils.Token;

public class BTDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private final String message;
	
	public BTDialog( final Window owner, final String message ) {
		this( owner, message, "" );
	}
	
	public BTDialog( final Window owner, final String message, final String title ) {
		super( owner, ModalityType.APPLICATION_MODAL );
		super.setTitle( title );

		this.message = message;
		this.init();
		this.addKeyListener( ShortcutFactory.createDisposeWindowShortcut( this ) );
		this.output();
	}
	
	private void init() {
		this.setLayout( new BorderLayout() );
		this.add( this.getMessage(), BorderLayout.CENTER );
		this.add( this.getButton(), BorderLayout.SOUTH );
		this.setAlwaysOnTop( MainFrame.getInstance().isAlwaysOnTop() );
		this.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		this.setResizable( false );
	}
	
	private JLabel getMessage() {
		JLabel message = new JLabel( this.message );
		message.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );
		message.setFont( new Font("Dialog", Font.PLAIN, 13) );
		return message;
	}
	
	private JPanel getButton() {
		BTButton btn = new BTButton(Token.OK);
		btn.setSize(10, 10);
		btn.setFocusable( true );
		btn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JPanel btnPane = new JPanel();
		btnPane.add( btn );
		
		return btnPane;
	}

	public void output() {
		this.pack();
		this.setLocationRelativeTo( this.getOwner() );
		this.setVisible( true );
	}
}
