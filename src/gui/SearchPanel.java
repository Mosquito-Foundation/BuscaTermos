package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import conf.Configuration;
import gui.components.BTButton;
import gui.components.BTPanel;
import gui.components.BTSplitter;
import gui.components.BTTextField;
import language.Language;
import reader.LanguageFileParser;

public class SearchPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instância da janela
	 */
	private final MainFrame mainFrame;
	
	/**
	 * Instancia das configurações
	 */
	private final Configuration configuration = Configuration.getInstance();
	
	/**
	 * Nome do arquivo de idioma
	 */
	private String fileLanguage;
	
	/**
	 * Campo texto para pesquisa
	 */
	private BTTextField searchField;
	
	/**
	 * Listagem
	 */
	private Grid grid;
	
	/**
	 * Dados da listagem
	 */
	private List<Object> gridData;

	/**
	 * Painel com split
	 */
	private BTSplitter splitter;
	
	/**
	 * Indica se o painel inferior estará expandido
	 */
	private boolean bottomPaneExpanded;
	
	/**
	 * Construtor
	 * @param fileLanguage Nome do arquivo do idioma
	 * @param mainFrame Instância da janela
	 */
	public SearchPanel( final String fileLanguage, final MainFrame mainFrame, final boolean bottomPaneExpanded ) {
		this.mainFrame = mainFrame;
		this.fileLanguage = fileLanguage;
		this.bottomPaneExpanded = bottomPaneExpanded;
		
		this.setLayout( new BorderLayout() );
		
		this.setBorder( BorderFactory.createEmptyBorder(5, 5, 5, 5) );
		
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.decode( "#C8DDF2" ) );
		}
		
		new Thread( this ).start();

		// FIXME Gambiarra 
		if( "pt-br".equals( fileLanguage ) ) {
			this.add( this.getSplitter(), BorderLayout.CENTER );
		}
	}

	/**
	 * Monta e retorna painel com os filtros (Campo texto e botão limpar)
	 * @return Painel de filtros
	 */
	private BTPanel getFilterArea() {
		BTPanel filterArea = new BTPanel( new BorderLayout(), BorderFactory.createEmptyBorder(0, 0, 10, 0) );
		filterArea.add( new JLabel( Language.SEARCH ), BorderLayout.NORTH );
		filterArea.add( this.getFieldContainer() );

		return filterArea;
	}

	/**
	 * Monta e retorna container com o campo texto e botão limpar
	 * @return Container com campo texto e botão limpar
	 */
	private BTPanel getFieldContainer() {
		BTPanel fieldContainer = new BTPanel( new BorderLayout() );
		fieldContainer.add( this.getSearchField(), BorderLayout.CENTER );
		fieldContainer.add( this.getClearButton(), BorderLayout.EAST );
		
		return fieldContainer;
	}
	
	/**
	 * Monta e retorna campo texto para filtro
	 * @return Campo texto
	 */
	protected BTTextField getSearchField() {
		if(this.searchField == null) {
			this.searchField = new BTTextField();
			this.searchField.setComponentPopupMenu(new FieldContextMenu(this.getInstance()));
			this.searchField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					doGridSearch();
				}
			});
		}
		
		return this.searchField;
	}
	
	/**
	 * Monta e retorna o botão de limpar
	 * @return Botão que limpa o campo texto
	 */
	private BTButton getClearButton() {
		BTButton clearButton = new BTButton( Language.CLEAR, IconManager.getInstance().getIcon( "clear.png" ) );
		clearButton.setPreferredSize( new Dimension(20, 20) );
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSearchField().setText( "" );
				doGridSearch();
				getSearchField().requestFocus();
			}
		});
		
		return clearButton;
	}

	/**
	 * Monta e retorna painel com splitter
	 * @return Painel com splitter
	 */
	private BTSplitter getSplitter() {
		if( this.splitter == null ) {
			this.splitter = new BTSplitter( this.getGrid(), this, this.isBottomPaneExpanded() );
		}
		return this.splitter;
	}
	
	/**
	 * Monta e retorna listagem
	 * @return Listagem
	 */
	protected Grid getGrid() {
		this.grid = new Grid( this );
		final LanguageFileParser file = new LanguageFileParser( this.fileLanguage, this.getMainFrame().getConfiguration() );
		this.gridData = file.getTermsList();
		this.grid.setData( this.gridData );
		
		this.grid.addColumn( new GridColumn.Builder().width( 50 ).resizable( false ).build() );
		this.grid.addColumn( new GridColumn.Builder().width( 800 ).build() );
		
		return this.grid.output();
	}
	
	/**
	 * Realiza pesquisa
	 */
	public void doGridSearch() {
		this.grid.find( this.searchField.getText() );
	}

	/**
	 * Atualiza valores dos campos inferiores
	 * @param token Codigo do termo
	 */
	public void updateLanguageFields( final String token ) {
		List<SearchPanel> panes = this.getMainFrame().getSearchPanelList();
		List<BTTextField> fields = this.getSplitter().getLanguagePane().getFieldList();
		
		new Thread( new LanguageFieldsUpdater(panes, fields, token) ).start();
	}
	
	/**
	 * Atualiza configurações ao clicar no divisor
	 * @param isExpanded
	 */
	public void fireBottomPaneTrigger( final boolean isExpanded ) {
		switch( this.fileLanguage ) {
		case Language.FILE_BRAZIL:
			this.configuration.setTabBrazilSplit( isExpanded );
			break;
		case Language.FILE_USA:
			this.configuration.setTabUsaSplit( isExpanded );
			break;
		case Language.FILE_SPAIN:
			this.configuration.setTabSpainSplit( isExpanded );
			break;
		case Language.FILE_FRANCE:
			this.configuration.setTabFranceSplit( isExpanded );
			break;
		case Language.FILE_ITALY:
			this.configuration.setTabItalySplit( isExpanded );
			break;
		case Language.FILE_GERMANY:
			this.configuration.setTabGermanySplit( isExpanded );
			break;
		case Language.FILE_TURKEY:
			this.configuration.setTabTurkeySplit( isExpanded );
			break;
		case Language.FILE_SLOVAKIA:
			this.configuration.setTabSlovakia( isExpanded );
			break;
		case Language.FILE_CHINA:
			this.configuration.setTabChina( isExpanded );
			break;
		}
	}
	
	public List<Object> getGridData() {
		return this.gridData;
	}
	
	public SearchPanel getInstance() {
		return this;
	}

	public boolean isBottomPaneExpanded() {
		return this.bottomPaneExpanded;
	}
	
	public MainFrame getMainFrame() {
		return this.mainFrame;
	}
	
	@Override
	public void run() {
		this.add( this.getFilterArea(), BorderLayout.NORTH );
		// FIXME Gambiarra
		if( !this.fileLanguage.equals( "pt-br" ) ) {
			this.add( this.getSplitter(), BorderLayout.CENTER );
		}
	}
	
	@Override
	public String toString() {
		return "SearchPanel -> " + this.fileLanguage;
	}
}
