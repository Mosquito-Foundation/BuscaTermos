package gui.context.menu;

import gui.MainFrame;
import gui.components.BTTabbedPane;

public class TabContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	private final BTTabbedPane tabbedPane;
	
	private int selectedTab;
	
	public TabContextMenu( final BTTabbedPane tabbedPane, final int index ) {
		super();

		this.tabbedPane = tabbedPane;
		this.selectedTab = index;

		this.setIsBrazilTab(index == 0);
		this.createOptions();
	}

	private void createOptions() {
		this.add( this.getAddTabGroup() );
		this.add( this.getSeparator() );
		this.add( this.getCloseOption() );
		this.add( this.getCloseOthersOption() );
		this.add( this.getCloseAllOption() );
	}

	protected void closeAction() {
		this.removeTab(this.selectedTab);
	}
	
	protected void closeOthersAction() {
		int tabIndex = 1;
		while( tabIndex < this.tabbedPane.getTabCount() ) {
			if( tabIndex != this.selectedTab ) {
				this.removeTab( tabIndex );
			} else {
				tabIndex++;
			}
			
			if( tabIndex < this.selectedTab ) {
				this.selectedTab--;
			}
		}
		
		this.tabbedPane.setSelectedIndex( this.selectedTab );
	}
	
	protected void closeAllAction() {
		this.selectedTab = 0;
		closeOthersAction();
	}

	private void removeTab( final int index ) {
		MainFrame.getInstance().removeTabByIndex( index );
	}
	
}
