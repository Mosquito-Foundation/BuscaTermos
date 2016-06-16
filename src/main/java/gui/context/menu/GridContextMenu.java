package gui.context.menu;

import configuration.language.Language;
import gui.MainFrame;
import gui.components.BTTable;

public class GridContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	private final BTTable table;
	
	public GridContextMenu( final BTTable table ) {
		super();

		this.table = table;
		
		this.createOptions();
	}

	private void createOptions() {
		this.add( this.getCopyOption() );
		this.add( this.getSeparator() );
		this.add( this.getLanguageGroup() );
		this.add( this.getSeparator() );
	}
	
	@Override
	protected void languageAction( final Language language ) {
		this.switchTabAndFind( language );
	}
	
	private void switchTabAndFind( final Language language ) {
		final String token = (String) this.table.getValueAt( this.table.getSelectedRow(), 0 );
		final int languageTabIndex = MainFrame.getInstance().getTabIndexByTitle( language.getTitle() );
		
		if ( languageTabIndex > -1 ) {
			MainFrame.getInstance().getLanguageTabs().setSelectedIndex( languageTabIndex );
		} else {
			MainFrame.getInstance().getJMenuBar().getMenu( 0 ).getItem( language.getId().ordinal() ).setSelected( true );
			MainFrame.getInstance().showTab( language );
			MainFrame.getInstance().getLanguageTabs().setSelectedIndex( MainFrame.getInstance().getLanguageTabs().getTabCount() - 1 );
		}
		
		MainFrame.getInstance().getLanguagePanelMap().get( language.getId() ).getFilterField().setText( token );
	}
	
}
