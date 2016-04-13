package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import conf.Configuration;
import gui.components.BTMenu;
import gui.components.BTMenuItem;
import language.Language;

/**
 * Classe para controle dos menus de contexto
 * @author giovane.oliveira
 */
public class ContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * Indica se a aba selecionada � a do idioma Portugu�s(Brasil)
	 */
	private boolean isBrazilTab;
	
	/**
	 * Janela principal
	 */
	protected final MainFrame mainFrame;
	
	/**
	 * Construtor
	 * @param frame Janela principal
	 */
	public ContextMenu(MainFrame frame) {
		this.mainFrame = frame;
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#E8F0F7" ) );
		}
	}
	
	/**
	 * Monta e retorna item de menu Copiar
	 * @return <b>BTMenuItem</b> copy
	 */
	protected BTMenuItem getCopyOption() {
		BTMenuItem copy = new BTMenuItem(Language.COPY, this.getIcon("copy.png"));
		copy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				copyAction();
			}
		});
		return copy;
	}
	
	/**
	 * Monta e retorna item de menu Colar
	 * @return <b>BTMenuItem</b> paste
	 */
	protected BTMenuItem getPasteOption() {
		BTMenuItem paste = new BTMenuItem(Language.PASTE, this.getIcon("paste.png"));
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pasteAction();
			}
		});
		return paste;
	}
	
	/**
	 * Retorna menu com idiomas que ainda n�o est�o vis�veis
	 * @return <b>BTMenu</b> menu
	 */
	protected BTMenu getAddTabGroup() {
		BTMenu menu = new BTMenu(Language.ADD);
		
		if(!this.mainFrame.getViewUSAItem().isSelected()) menu.add(this.getUSAOption());
		if(!this.mainFrame.getViewSpainItem().isSelected()) menu.add(this.getSpainOption());
		if(!this.mainFrame.getViewFranceItem().isSelected()) menu.add(this.getFranceOption());
		if(!this.mainFrame.getViewItalyItem().isSelected()) menu.add(this.getItalyOption());
		if(!this.mainFrame.getViewGermanyItem().isSelected()) menu.add(this.getGermanyOption());
		
		if(this.mainFrame.getTabbedPane().getTabCount() < 6) {
			menu.add(this.getSeparator());
			menu.add(this.getAddAllOption());
		} else {
			menu.setEnabled(false);
		}
		
		return menu;
	}
	
	/**
	 * Monta e retorna menu com os idiomas dispon�veis
	 * @return <b>BTMenu</b> menu
	 */
	protected BTMenu getLanguageGroup() {
		BTMenu menu = new BTMenu(Language.VIEW_ON);
		
		menu.add(this.getBrazilOption());
		menu.add(this.getUSAOption());
		menu.add(this.getSpainOption());
		menu.add(this.getFranceOption());
		menu.add(this.getItalyOption());
		menu.add(this.getGermanyOption());
		
		return menu;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Portugu�s(Brasil)
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getBrazilOption() {
		BTMenuItem item = new BTMenuItem(Language.BRAZIL, this.getIcon("brazil.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brazilAction();
			}
		});
				
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Ingl�s(USA)
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getUSAOption() {
		BTMenuItem item = new BTMenuItem(Language.USA, this.getIcon("usa.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usaAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Espanhol
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getSpainOption() {
		BTMenuItem item = new BTMenuItem(Language.SPAIN, this.getIcon("spain.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spainAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Franc�s
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getFranceOption() {
		BTMenuItem item = new BTMenuItem(Language.FRANCE, this.getIcon("france.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				franceAction();
			}
		});
		
		return item;
	}

	/**
	 * Monta e retorna item de menu com os idiomas Italiano
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getItalyOption() {
		BTMenuItem item = new BTMenuItem(Language.ITALY, this.getIcon("italy.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				italyAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Alem�o
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getGermanyOption() {
		BTMenuItem item = new BTMenuItem(Language.GERMANY, this.getIcon("germany.jpg"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				germanyAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu que adiciona todos os idiomas
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getAddAllOption() {
		BTMenuItem item = new BTMenuItem(Language.ADD_ALL);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAllAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu Fechar
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getCloseOption() {
		BTMenuItem item = new BTMenuItem(Language.CLOSE);
		item.setEnabled(!this.isBrazilTab);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAction();
			}
		});
		
		return item;
	}

	/**
	 * Monta e retorna item de menu Fechar outras abas
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getCloseOthersOption() {
		BTMenuItem item = new BTMenuItem(Language.CLOSE_OTHERS);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeOthersAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu Fechar todas as abas
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getCloseAllOption() {
		BTMenuItem item = new BTMenuItem(Language.CLOSE_ALL);
		item.setEnabled(!this.isBrazilTab);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeAllAction();
			}
		});
		
		return item;
	}
	
	protected void copyAction() { this.unimplementedMethod(); }
	protected void pasteAction() { this.unimplementedMethod();	}
	protected void brazilAction() { this.unimplementedMethod(); }
	protected void usaAction() { this.unimplementedMethod(); }
	protected void spainAction() { this.unimplementedMethod(); }
	protected void franceAction() { this.unimplementedMethod(); }
	protected void italyAction() { this.unimplementedMethod(); }
	protected void germanyAction() { this.unimplementedMethod(); }
	protected void addAllAction() { this.unimplementedMethod(); }
	protected void closeAction() { this.unimplementedMethod(); }
	protected void closeOthersAction() { this.unimplementedMethod(); }
	protected void closeAllAction() { this.unimplementedMethod(); }

	/**
	 * Retorna separador para o menu de contexto
	 * @return <b>JSeparator</b> separator
	 */
	protected JSeparator getSeparator() {
		JSeparator separator = new JSeparator( SwingConstants.HORIZONTAL );
        separator.setBackground( Color.WHITE );
		return separator;
	}
	
	/**
	 * Retorna um �cone
	 * @param name Nome da imagem
	 * @return �cone
	 */
	protected ImageIcon getIcon(String name) {
		ImageIcon icon;
		try {
			icon = new ImageIcon(getClass().getResource("/" + name));
		} catch(NullPointerException e) {
			icon = new ImageIcon("images/" + name);
		}
		return icon;
	}
	
	/**
	 * Imprime mensagem quando m�todo n�o foi implementado
	 */
	private void unimplementedMethod() {
		System.out.println("Este m�todo deve ser sobrescrito na classe filha!");
	}
	
	/**
	 * Seta se a aba selecionada � a do idioma Portugu�s(Brasil)
	 * @param isBrazilTab
	 */
	protected void setIsBrazilTab(boolean isBrazilTab) {
		this.isBrazilTab = isBrazilTab;
	}
	
}
