package gui.context.menu;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import configuration.Configuration;
import configuration.language.Language;
import gui.MainFrame;
import utils.IconManager;
import utils.Token;

public class ContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private boolean isBrazilTab;

	protected JMenuItem getCopyOption() {
		JMenuItem copy = new JMenuItem(Token.COPY, IconManager.getInstance().getIcon("icons/copy.png"));
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				copyAction();
			}
		});
		return copy;
	}

	protected JMenuItem getPasteOption() {
		JMenuItem paste = new JMenuItem(Token.PASTE, IconManager.getInstance().getIcon("icons/paste.png"));
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pasteAction();
			}
		});
		return paste;
	}
	
	protected JMenu getAddTabGroup() {
		JMenu menu = new JMenu(Token.ADD);
		
		for (Language language : Configuration.getInstance().getLanguages().values()) {
			if ( !MainFrame.getInstance().getJMenuBar().getMenu( 0 ).getItem( language.getId().ordinal() ).isSelected() ) {
				menu.add( this.getLanguageOption( language ) );
			}
		}
		
		if ( menu.getItemCount() < Configuration.getInstance().getLanguages().size() ) {
			menu.add( this.getSeparator() );
			menu.add( this.getAddAllOption() );
		} else {
			menu.setEnabled( false );
		}
		
		return menu;
	}
	
	protected JMenu getLanguageGroup() {
		JMenu menu = new JMenu(Token.VIEW_ON);
		
		for (Language language : Configuration.getInstance().getLanguages().values()) {
			menu.add( this.getLanguageOption( language ) );
		}
		
		return menu;
	}
	
	protected JMenuItem getLanguageOption( final Language language ) {
		JMenuItem item = new JMenuItem( language.getTitle(), language.getIcon() );
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				languageAction( language );
			}
		});
		return item;
	}
	
	protected JMenuItem getAddAllOption() {
		JMenuItem item = new JMenuItem(Token.ADD_ALL);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAllAction();
			}
		});
		
		return item;
	}
	
	protected JMenuItem getCloseOption() {
		JMenuItem item = new JMenuItem(Token.CLOSE);
		item.setEnabled(!this.isBrazilTab);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAction();
			}
		});
		
		return item;
	}

	protected JMenuItem getCloseOthersOption() {
		JMenuItem item = new JMenuItem(Token.CLOSE_OTHERS);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeOthersAction();
			}
		});
		
		return item;
	}
	
	protected JMenuItem getCloseAllOption() {
		JMenuItem item = new JMenuItem(Token.CLOSE_ALL);
		item.setEnabled(!this.isBrazilTab);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAllAction();
			}
		});
		
		return item;
	}
	
	protected void languageAction( final Language language ) {
		MainFrame.getInstance().getJMenuBar().getMenu( 0 ).getItem( language.getId().ordinal() ).setSelected( true );
		MainFrame.getInstance().showTab( language );
	}
	
	protected void addAllAction() {
		for (Language language : Configuration.getInstance().getLanguages().values()) {
			if ( !MainFrame.getInstance().getJMenuBar().getMenu( 0 ).getItem( language.getId().ordinal() ).isSelected() ) {
				languageAction( language );
			}
		}
	}
	
	protected void copyAction() {
		Action copy = ( (JComponent) this.getInvoker() ).getActionMap().get( "copy" );
		ActionEvent ae = new ActionEvent( this.getInvoker(), ActionEvent.ACTION_PERFORMED, "" );
		copy.actionPerformed( ae );
	}
	
	protected void pasteAction() {
		String textToPaste = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents( null );
		boolean hasTransferableText = ( contents != null ) && contents.isDataFlavorSupported( DataFlavor.stringFlavor );
		if ( hasTransferableText ) {
			try {
				textToPaste = (String) contents.getTransferData( DataFlavor.stringFlavor );
			} catch ( UnsupportedFlavorException | IOException e ) {
				e.printStackTrace();
			}
		}

		final JTextComponent field = (JTextComponent) this.getInvoker();
		if ( field.getSelectedText() != null ) {
			field.replaceSelection( textToPaste );
		} else {
			final StringBuilder sb = new StringBuilder( field.getText() );
			sb.insert( field.getCaretPosition(), textToPaste );
			field.setText( sb.toString() );
		}
	}
	
	protected void closeAction() { this.unimplementedMethod(); }
	protected void closeOthersAction() { this.unimplementedMethod(); }
	protected void closeAllAction() { this.unimplementedMethod(); }

	protected JSeparator getSeparator() {
		JSeparator separator = new JSeparator( SwingConstants.HORIZONTAL );
		return separator;
	}
	
	private void unimplementedMethod() {
		System.out.println("Este método deve ser sobrescrito na classe filha!");
	}
	
	protected void setIsBrazilTab(boolean isBrazilTab) {
		this.isBrazilTab = isBrazilTab;
	}
	
}
