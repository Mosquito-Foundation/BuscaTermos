package gui;

import javax.swing.JTabbedPane;

import language.Language;

/**
 * Classe para controle do menu de contexto das abas
 * @author giovane.oliveira
 */
public class TabContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * Index da aba clicada
	 */
	private int selectedTab;
	
	/**
	 * Construtor
	 * @param  mainFrame Janela principal
	 * @param  index     Index da aba clicada
	 */
	public TabContextMenu(MainFrame mainFrame, int index) {
		super(mainFrame);
		this.selectedTab = index;
		this.setIsBrazilTab(index == 0);
		this.createOptions();
	}

	/**
	 * Monta op��es do menu
	 */
	private void createOptions() {
		this.add(this.getAddTabGroup());
		this.add(this.getSeparator());
		this.add(this.getCloseOption());
		this.add(this.getCloseOthersOption());
		this.add(this.getCloseAllOption());
	}

	/**
	 * A��o da op��o Ingl�s(EUA)
	 */
	@Override
	protected void usaAction() {
		this.mainFrame.getViewUSAItem().setSelected(true);
		this.mainFrame.addRemoveTabs(Language.USA, "usa", this.mainFrame.getSearchPanelUSA());
	}
	
	/**
	 * A��o da op��o Espanhol
	 */
	@Override
	protected void spainAction() {
		this.mainFrame.getViewSpainItem().setSelected(true);
		this.mainFrame.addRemoveTabs(Language.SPAIN, "spain", this.mainFrame.getSearchPanelSpain());
	}
	
	/**
	 * A��o da op��o Franc�s
	 */
	@Override
	protected void franceAction() {
		this.mainFrame.getViewFranceItem().setSelected(true);
		this.mainFrame.addRemoveTabs(Language.FRANCE, "france", this.mainFrame.getSearchPanelFrance());
	}
	
	/**
	 * A��o da op��o Italiano
	 */
	@Override
	protected void italyAction() {
		this.mainFrame.getViewItalyItem().setSelected(true);
		this.mainFrame.addRemoveTabs(Language.ITALY, "italy", this.mainFrame.getSearchPanelItaly());
	}
	
	/**
	 * A��o da op��o Alem�o
	 */
	@Override
	protected void germanyAction() {
		this.mainFrame.getViewGermanyItem().setSelected(true);
		this.mainFrame.addRemoveTabs(Language.GERMANY, "germany", this.mainFrame.getSearchPanelGermany());
	}
	
	/**
	 * A��o da op��o Adicionar todos
	 */
	protected void addAllAction() {
		if(!this.mainFrame.getViewUSAItem().isSelected()) this.usaAction();
		if(!this.mainFrame.getViewSpainItem().isSelected()) this.spainAction();
		if(!this.mainFrame.getViewFranceItem().isSelected()) this.franceAction();
		if(!this.mainFrame.getViewItalyItem().isSelected()) this.italyAction();
		if(!this.mainFrame.getViewGermanyItem().isSelected()) this.germanyAction();
	}

	/**
	 * A��o da op��o Fechar
	 */
	protected void closeAction() {
		this.removeTab(this.selectedTab);
	}
	
	/**
	 * A��o da op��o Fechar as outras
	 */
	protected void closeOthersAction() {
		JTabbedPane tabbedPane = this.mainFrame.getTabbedPane(); 
		
		for(int i = 1; i < tabbedPane.getTabCount(); ) {
			if(i != this.selectedTab) {
				removeTab(i);
			} else {
				i++;
			}
			
			if(i < this.selectedTab) {
				this.selectedTab--;
			}
		}
		
		tabbedPane.setSelectedIndex(this.selectedTab);
	}
	
	/**
	 * A��o da op��o Fechar todas
	 */
	protected void closeAllAction() {
		this.selectedTab = 0;
		closeOthersAction();
	}

	/**
	 * Remove uma aba
	 * @param index Index da aba
	 */
	private void removeTab(int index) {
		switch (this.mainFrame.getTabbedPane().getTitleAt(index)) {
		case Language.USA:
			mainFrame.getViewUSAItem().setSelected(false);
			break;
		case Language.SPAIN:
			mainFrame.getViewSpainItem().setSelected(false);
			break;
		case Language.FRANCE:
			mainFrame.getViewFranceItem().setSelected(false);
			break;
		case Language.ITALY:
			mainFrame.getViewItalyItem().setSelected(false);
			break;
		case Language.GERMANY:
			mainFrame.getViewGermanyItem().setSelected(false);
			break;
		}

		this.mainFrame.getTabbedPane().removeTabAt(index);
	}
}
