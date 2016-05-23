package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import conf.Configuration;
import gui.components.BTMenu;
import gui.components.BTMenuItem;
import language.Language;
import utils.IconManager;

/**
 * Classe para controle dos menus de contexto
 * @author giovane.oliveira
 */
public class ContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * Indica se a aba selecionada é a do idioma Português(Brasil)
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
		BTMenuItem copy = new BTMenuItem(Language.COPY, IconManager.getInstance().getIcon("icons/copy.png"));
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
		BTMenuItem paste = new BTMenuItem(Language.PASTE, IconManager.getInstance().getIcon("icons/paste.png"));
		paste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pasteAction();
			}
		});
		return paste;
	}
	
	/**
	 * Retorna menu com idiomas que ainda não estão visíveis
	 * @return <b>BTMenu</b> menu
	 */
	protected BTMenu getAddTabGroup() {
		BTMenu menu = new BTMenu(Language.ADD);
		
		if(!this.mainFrame.getViewUSAItem().isSelected()) menu.add(this.getUSAOption());
		if(!this.mainFrame.getViewSpainItem().isSelected()) menu.add(this.getSpainOption());
		if(!this.mainFrame.getViewFranceItem().isSelected()) menu.add(this.getFranceOption());
		if(!this.mainFrame.getViewItalyItem().isSelected()) menu.add(this.getItalyOption());
		if(!this.mainFrame.getViewGermanyItem().isSelected()) menu.add(this.getGermanyOption());
		if(!this.mainFrame.getViewTurkeyItem().isSelected()) menu.add(this.getTurkeyOption());
		if(!this.mainFrame.getViewSlovakiaItem().isSelected()) menu.add(this.getSlovakiaOption());
		if(!this.mainFrame.getViewChinaItem().isSelected()) menu.add(this.getChinaOption());
		
		if(this.mainFrame.getTabbedPane().getTabCount() < 9) {
			menu.add(this.getSeparator());
			menu.add(this.getAddAllOption());
		} else {
			menu.setEnabled(false);
		}
		
		return menu;
	}
	
	/**
	 * Monta e retorna menu com os idiomas disponíveis
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
		menu.add(this.getTurkeyOption());
		menu.add(this.getSlovakiaOption());
		menu.add(this.getChinaOption());
		
		return menu;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Português(Brasil)
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getBrazilOption() {
		BTMenuItem item = new BTMenuItem(Language.BRAZIL, IconManager.getInstance().getIcon("flags/brazil.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brazilAction();
			}
		});
				
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Inglês(USA)
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getUSAOption() {
		BTMenuItem item = new BTMenuItem(Language.USA, IconManager.getInstance().getIcon("flags/usa.gif"));
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
		BTMenuItem item = new BTMenuItem(Language.SPAIN, IconManager.getInstance().getIcon("flags/spain.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spainAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Francês
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getFranceOption() {
		BTMenuItem item = new BTMenuItem(Language.FRANCE, IconManager.getInstance().getIcon("flags/france.gif"));
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
		BTMenuItem item = new BTMenuItem(Language.ITALY, IconManager.getInstance().getIcon("flags/italy.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				italyAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Alemão
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getGermanyOption() {
		BTMenuItem item = new BTMenuItem(Language.GERMANY, IconManager.getInstance().getIcon("flags/germany.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				germanyAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Turco
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getTurkeyOption() {
		BTMenuItem item = new BTMenuItem(Language.TURKEY, IconManager.getInstance().getIcon("flags/turkey.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turkeyAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Eslovaco
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getSlovakiaOption() {
		BTMenuItem item = new BTMenuItem(Language.SLOVAKIA, IconManager.getInstance().getIcon("flags/slovakia.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				slovakiaAction();
			}
		});
		
		return item;
	}
	
	/**
	 * Monta e retorna item de menu com os idiomas Chinês
	 * @return <b>BTMenuItem</b> item
	 */
	protected BTMenuItem getChinaOption() {
		BTMenuItem item = new BTMenuItem(Language.CHINA, IconManager.getInstance().getIcon("flags/china.gif"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chinaAction();
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
	protected void turkeyAction() { this.unimplementedMethod(); }
	protected void slovakiaAction() { this.unimplementedMethod(); }
	protected void chinaAction() { this.unimplementedMethod(); }
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
	 * Imprime mensagem quando método não foi implementado
	 */
	private void unimplementedMethod() {
		System.out.println("Este método deve ser sobrescrito na classe filha!");
	}
	
	/**
	 * Seta se a aba selecionada é a do idioma Português(Brasil)
	 * @param isBrazilTab
	 */
	protected void setIsBrazilTab(boolean isBrazilTab) {
		this.isBrazilTab = isBrazilTab;
	}
	
}
