package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import conf.Configuration;
import gui.LanguageFieldsPanel;
import gui.SearchPanel;
import language.Language;
import model.Grid;

public class BTSplitter extends BTPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Painel do idioma
	 */
	private SearchPanel searchPanel;
	
	/**
	 * Botão que expande e contrai o item inferior
	 */
	private BTButton splitterButton;
	
	/**
	 * Componente superior
	 */
	private Grid grid;
	
	/**
	 * Componente inferior
	 */
	private LanguageFieldsPanel languagePanel;
	
	/**
	 * Indica se o painel inferior est� sendo exibido
	 */
	private boolean bottomPaneExpanded;
	
	/**
	 * Construtor
	 * @param topComponent Componente superior
	 * @param bottomComponent Componente inferior
	 */
	public BTSplitter( final Grid topComponent, final SearchPanel searchPanel, final boolean isExpanded ) {
		super( new BorderLayout() );
		
		this.grid = topComponent;
		this.searchPanel = searchPanel;
		this.bottomPaneExpanded = isExpanded;
		
		this.createSplitterPanel();
	}
	
	/**
	 * Monta componentes do splitter
	 */
	private void createSplitterPanel() {
		this.add( this.grid, BorderLayout.CENTER );
		this.add( this.getBottomPanel(), BorderLayout.SOUTH );
	}
	
	/**
	 * Monta e retorna painel inferior
	 * @return Painel inferior do splitter
	 */
	private BTPanel getBottomPanel() {
		BTPanel bottomContainer = new BTPanel( new BorderLayout() );
		bottomContainer.add( this.getLanguagePane(), BorderLayout.CENTER );
		bottomContainer.add( this.getSplitterButton(), BorderLayout.NORTH );
		
		return bottomContainer;
	}
	
	/**
	 * Monta e retorna o painel inferior
	 * @return painel inferior
	 */
	@SuppressWarnings("serial")
	public LanguageFieldsPanel getLanguagePane() {
		if( this.languagePanel == null ) {
			this.languagePanel = new LanguageFieldsPanel( this.searchPanel ) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(this.getWidth(), isBottomPaneVisible() ? (int) super.getPreferredSize().getHeight() : 0);
				}
			};
		}
		return this.languagePanel;
	}
	
	/**
	 * Monta e retorna o botão do splitter
	 * @return Botão do splitter
	 */
	private BTButton getSplitterButton() {
		if( this.splitterButton == null ) {
			this.splitterButton = new BTButton( this.getButtonIconName(), 12, 12 );
			this.splitterButton.setToolTipText( this.getButtonTooltip() );
			this.splitterButton.setBackground( Color.decode("#EEEEEE") );
			if ( Configuration.getInstance().isWindowsTheme() ) {
				this.splitterButton.setPreferredSize( new Dimension( this.getWidth(), 16) );
			}
			if ( Configuration.getInstance().isDefaultTheme() ) {
				this.splitterButton.setBorder( BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY) );
			}
			this.splitterButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					showHideBottomPane();
				}
			});
		}
		return this.splitterButton;
	}
	
	/**
	 * Retorna o nome do icone para o botao
	 * @return Nome do icone para o botao Expandir/Contrair
	 */
	private String getButtonIconName() {
		return this.isBottomPaneVisible() ? "collapse.png" : "expand.png";
	}
	
	/**
	 * Retorna o hint do botao
	 * @return Hint para o botao Expandir/Contrair
	 */
	private String getButtonTooltip() {
		return this.isBottomPaneVisible() ? Language.COLLAPSE : Language.EXPAND;
	}
	
	/**
	 * Contrai ou expande o painel inferior
	 */
	public void showHideBottomPane() {
		this.bottomPaneExpanded = !this.bottomPaneExpanded;
		this.searchPanel.fireBottomPaneTrigger( this.bottomPaneExpanded );
		this.splitterButton.setIcon( this.getButtonIconName() );
		this.splitterButton.setToolTipText( this.getButtonTooltip() );
		this.splitterButton.revalidate();
	}
	
	/**
	 * Retorna se o painel inferior está visivel
	 * @return
	 */
	private boolean isBottomPaneVisible() {
		return this.bottomPaneExpanded;
	}
	
}
