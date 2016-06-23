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
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import configuration.Configuration;
import configuration.language.Language;
import gui.MainFrame;
import gui.components.BTMenu;
import gui.components.BTMenuItem;
import utils.IconManager;
import utils.Token;

public class ContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private boolean isBrazilTab;

	protected BTMenuItem getCopyOption() {
		BTMenuItem copy = new BTMenuItem(Token.COPY, IconManager.getInstance().getIcon("icons/copy.png"));
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				copyAction();
			}
		});
		return copy;
	}

	protected BTMenuItem getPasteOption() {
		BTMenuItem paste = new BTMenuItem(Token.PASTE, IconManager.getInstance().getIcon("icons/paste.png"));
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pasteAction();
			}
		});
		return paste;
	}
	
	protected BTMenu getAddTabGroup() {
		BTMenu menu = new BTMenu(Token.ADD);
		
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
	
	protected BTMenu getLanguageGroup() {
		BTMenu menu = new BTMenu(Token.VIEW_ON);
		
		for (Language language : Configuration.getInstance().getLanguages().values()) {
			menu.add( this.getLanguageOption( language ) );
		}
		
		return menu;
	}
	
	protected BTMenuItem getLanguageOption( final Language language ) {
		BTMenuItem item = new BTMenuItem( language.getTitle(), language.getIcon() );
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				languageAction( language );
			}
		});
		return item;
	}
	
	protected BTMenuItem getAddAllOption() {
		BTMenuItem item = new BTMenuItem(Token.ADD_ALL);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAllAction();
			}
		});
		
		return item;
	}
	
	protected BTMenuItem getCloseOption() {
		BTMenuItem item = new BTMenuItem(Token.CLOSE);
		item.setEnabled(!this.isBrazilTab);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAction();
			}
		});
		
		return item;
	}

	protected BTMenuItem getCloseOthersOption() {
		BTMenuItem item = new BTMenuItem(Token.CLOSE_OTHERS);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeOthersAction();
			}
		});
		
		return item;
	}
	
	protected BTMenuItem getCloseAllOption() {
		BTMenuItem item = new BTMenuItem(Token.CLOSE_ALL);
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
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents( null );
		boolean hasTransferableText = ( contents != null ) && contents.isDataFlavorSupported( DataFlavor.stringFlavor );
		if ( hasTransferableText ) {
			try {
				result = (String) contents.getTransferData( DataFlavor.stringFlavor );
			} catch ( UnsupportedFlavorException | IOException e ) {
				e.printStackTrace();
			}
		}

		// FIXME quando colar em uma parada q ja ta selecionada deve substituir o q ta selecionado
		( (JTextComponent) this.getInvoker() ).setText( ( (JTextComponent) this.getInvoker() ).getText() + result );
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
