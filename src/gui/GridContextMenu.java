package gui;


import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;

import language.Language;
import model.Grid;

/**
 * Classe para controle do menu de contexto da grid
 * @author giovane.oliveira
 */
public class GridContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * Grid do painel de idiomas
	 */
	private final Grid grid;
	
	/**
	 * Construtor - Seta atributos e cria op��es do menu de contexto
	 * @param grid
	 */
	public GridContextMenu(Grid grid) {
		super(grid.getSearchPanel().getMainFrame());
		this.grid = grid;
		
		this.createOptions();
	}

	/**
	 * Cria op��es do menu de contexto
	 */
	private void createOptions() {
		this.add(this.getCopyOption());
		this.add(this.getSeparator());
		this.add(this.getLanguageGroup());
		this.add(this.getSeparator());
	}
	
	/**
	 * A��o executada no item de menu Copiar
	 */
	@Override
	protected void copyAction() {
		Action copy = this.grid.getTable().getActionMap().get("copy");
		ActionEvent ae = new ActionEvent(this.grid.getTable(), ActionEvent.ACTION_PERFORMED, "");
		copy.actionPerformed(ae);
	}
	
	/**
	 * A��o executada no item de menu Portugu�s(Brasil)
	 */
	@Override
	protected void brazilAction() {
		switchTabSearch(Language.BRAZIL, null, this.mainFrame.getSearchPanelBrazil(), "brazil");
	}

	/**
	 * A��o executada no item de menu Ingl�s(EUA)
	 */
	@Override
	protected void usaAction() {
		switchTabSearch(Language.USA, this.mainFrame.getViewUSAItem(), this.mainFrame.getSearchPanelUSA(), "usa");
	}
	
	/**
	 * A��o executada no item de menu Espanhol
	 */
	@Override
	protected void spainAction() {
		switchTabSearch(Language.SPAIN, this.mainFrame.getViewSpainItem(), this.mainFrame.getSearchPanelSpain(), "spain");
	}
	
	/**
	 * A��o executada no item de menu Franc�s
	 */
	@Override
	protected void franceAction() {
		switchTabSearch(Language.FRANCE, this.mainFrame.getViewFranceItem(), this.mainFrame.getSearchPanelFrance(), "france");
	}
	
	/**
	 * A��o executada no item de menu Italiano
	 */
	@Override
	protected void italyAction() {
		switchTabSearch(Language.ITALY, this.mainFrame.getViewItalyItem(), this.mainFrame.getSearchPanelItaly(), "italy");
	}
	
	/**
	 * A��o executada no item de menu Alem�o
	 */
	@Override
	protected void germanyAction() {
		switchTabSearch(Language.GERMANY, this.mainFrame.getViewGermanyItem(), this.mainFrame.getSearchPanelGermany(), "germany");
	}
	
	/**
	 * Recupera o index da aba atrav�s do titulo
	 * @param title T�tulo da aba
	 * @return Index da aba selecionada
	 */
	private int getTabIndexByTitle(String title) {
		final JTabbedPane tabs = this.grid.getSearchPanel().getMainFrame().getTabbedPane();
		for (int i = 0; i < tabs.getTabCount(); i++) {
			if(tabs.getTitleAt(i).equals(title)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Alterna aba para a selecionada no item e realiza pesquisa
	 * @param title T�tulo da aba
	 * @param viewItem Idioma selecionado
	 * @param searchPanelItem 
	 * @param iconName
	 */
	private void switchTabSearch(String title, JCheckBoxMenuItem viewItem, SearchPanel searchPanelItem, String iconName) {
		final String term = (String) grid.getTable().getValueAt(grid.getTable().getSelectedRow(), 0);
		final MainFrame frame = this.grid.getSearchPanel().getMainFrame();
		final JTabbedPane tabs = frame.getTabbedPane();
		int index = getTabIndexByTitle(title);
		
		if(index > -1) {
			tabs.setSelectedIndex(index);
		} else {
			viewItem.setSelected(true);
			frame.addRemoveTabs(viewItem.getText(), iconName, searchPanelItem);
			tabs.setSelectedIndex(tabs.getTabCount() - 1);
		}
		
		searchPanelItem.getSearchField().setText(term);
		searchPanelItem.doGridSearch();
	}
}
