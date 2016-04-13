package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

import conf.Configuration;
import gui.components.BTCheckBoxMenuItem;
import gui.components.BTMenu;
import gui.components.BTMenuBar;
import gui.components.BTMenuItem;
import gui.components.BTTabbedPane;
import language.Language;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Armazena as configura��es do aplicativo
	 */
	private final Configuration configuration = Configuration.getInstance();
	
	/**
	 * Menubar
	 */
	private BTMenuBar toolbar;
	
	/**
	 * Op��es da menubar
	 */
	private BTMenu displayMenu, optionsMenu, themeMenu, helpMenu;
	
	/**
	 * Itens de menu da op��o Exibir
	 */
	private BTCheckBoxMenuItem viewUSAItem, viewSpainItem, viewFranceItem, viewItalyItem, viewGermanyItem;
	
	/**
	 * Itens de menu da op��o Op��es
	 */
	private BTCheckBoxMenuItem alwaysOnTopItem;
	
	/**
	 * Op��es de temas
	 */
	private BTCheckBoxMenuItem themeDefaultItem, themeWindowsItem, themeMacItem;
	
	/**
	 * Itens de menu
	 */
	private BTMenuItem changePathItem, saveConfigurationItem, regexItem, aboutItem;
	
	/**
	 * Paineis de pesquisa de cada idioma
	 */
	private SearchPanel searchPanelBrazil, searchPanelUSA, searchPanelSpain, searchPanelFrance, searchPanelItaly, searchPanelGermany;
	
	/**
	 * Abas
	 */
	private BTTabbedPane tabbedPane;
	
	/**
	 * Lista de paineis de idioma
	 */
	private List<SearchPanel> searchPanelList;
	
	/**
	 * Mensagem contendo as express�es regulares
	 */
	private RegexDialog regexDialog;
	
	/**
	 * Construtor
	 */
	public MainFrame() {
		// Valida��o para o path, se o usu�rio informar um path inv�lido e n�o quiser mais procurar um v�lido, sai da aplica��o
		if( !this.getConfiguration().isPathValid() ) {
			if( !this.getConfiguration().createInitialPath() ) {
				System.exit( 0 );
			}
		}

		// Cria menubar
		this.setJMenuBar( this.getToolBar() );
		
		// Cria abas
		this.add( this.getTabbedPane() );
		
		// Seta atributos da janela e exibe
		this.createAndShowWindow();
	}
	
	/**
	 * Monta e retorna menubar
	 * @return Barra de menus da janela
	 */
	private BTMenuBar getToolBar() {
		if( this.toolbar == null ) {
			this.toolbar = new BTMenuBar();
			this.toolbar.add( this.getViewMenu() );
			this.toolbar.add( this.getOptionsMenu() );
			this.toolbar.add( this.getHelpMenu() );
		}

		return this.toolbar;
	}
	
	/**
	 * Monta e retorna menu Exibir
	 * @return Menu Exibir
	 */
	private BTMenu getViewMenu() {
		if( this.displayMenu == null ) {
			this.displayMenu = new BTMenu( Language.DISPLAY );
			this.displayMenu.add( this.getViewUSAItem() );
			this.displayMenu.add( this.getViewSpainItem() );
			this.displayMenu.add( this.getViewFranceItem() );
			this.displayMenu.add( this.getViewItalyItem() );
			this.displayMenu.add( this.getViewGermanyItem() );
		}
		
		return this.displayMenu;
	}

	/**
	 * Monta e retorna item de menu Ingl�s(USA)
	 * @return item de menu para idioma Ingl�s(USA)
	 */
	protected BTCheckBoxMenuItem getViewUSAItem() {
		if( this.viewUSAItem == null ) {
			this.viewUSAItem = new BTCheckBoxMenuItem( Language.USA );
			this.viewUSAItem.setSelected( this.getConfiguration().isTabUsa() );
			this.viewUSAItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addRemoveTabs( Language.USA, "usa", searchPanelUSA );
				}
			});
		}
		
		return this.viewUSAItem;
	}
	
	/**
	 * Monta e retorna item de menu Espanhol
	 * @return item de menu para idioma Espanhol
	 */
	protected BTCheckBoxMenuItem getViewSpainItem() {
		if( this.viewSpainItem == null ) {
			this.viewSpainItem = new BTCheckBoxMenuItem( Language.SPAIN );
			this.viewSpainItem.setSelected( this.configuration.isTabSpain() );
			this.viewSpainItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addRemoveTabs( Language.SPAIN, "spain", searchPanelSpain );
				}
			});
		}
		
		return this.viewSpainItem;
	}
	
	/**
	 * Monta e retorna item de menu Franc�s
	 * @return item de menu para o idioma Franc�s
	 */
	protected BTCheckBoxMenuItem getViewFranceItem() {
		if( this.viewFranceItem == null ) {
			this.viewFranceItem = new BTCheckBoxMenuItem( Language.FRANCE );
			this.viewFranceItem.setSelected( this.configuration.isTabFrance() );
			this.viewFranceItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addRemoveTabs( Language.FRANCE, "france", searchPanelFrance );
				}
			});
		}
		
		return this.viewFranceItem;
	}
	
	/**
	 * Monta e retorna item de menu Italiano
	 * @return item de menu para o idioma Italiano
	 */
	protected BTCheckBoxMenuItem getViewItalyItem() {
		if( this.viewItalyItem == null ) {
			this.viewItalyItem = new BTCheckBoxMenuItem( Language.ITALY );
			this.viewItalyItem.setSelected( this.configuration.isTabItaly() );
			this.viewItalyItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addRemoveTabs( Language.ITALY, "italy", searchPanelItaly );
				}
			});
		}
		
		return this.viewItalyItem;
	}
	
	/**
	 * Monta e retorna item de menu Alem�o
	 * @return item de menu para o idioma Alem�o
	 */
	protected BTCheckBoxMenuItem getViewGermanyItem() {
		if( this.viewGermanyItem == null ) {
			this.viewGermanyItem = new BTCheckBoxMenuItem( Language.GERMANY );
			this.viewGermanyItem.setSelected( this.configuration.isTabGermany() );
			this.viewGermanyItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addRemoveTabs( Language.GERMANY, "germany", searchPanelGermany );
				}
			});
		}
		
		return this.viewGermanyItem;
	}
	
	/**
	 * Monta e retorna menu Op��es
	 * @return menu Op��es
	 */
	private BTMenu getOptionsMenu() {
		if(this.optionsMenu == null) {
			this.optionsMenu = new BTMenu( Language.OPTIONS );
			this.optionsMenu.add( this.getAlwaysOnTopItem() );
			this.optionsMenu.addSeparator();
			this.optionsMenu.add( this.getChangePathItem() );
			this.optionsMenu.add( this.getThemeMenu() );
			this.optionsMenu.addSeparator();
			this.optionsMenu.add( this.getSaveConfigurationItem() );
		}
		
		return this.optionsMenu;
	}
	
	/**
	 * Monta e retorna item de menu Sempre no topo
	 * @return item de menu Sempre no topo
	 */
	protected BTCheckBoxMenuItem getAlwaysOnTopItem() {
		if(this.alwaysOnTopItem == null) {
			this.alwaysOnTopItem = new BTCheckBoxMenuItem(Language.ALWAYS_ON_TOP);
			this.alwaysOnTopItem.setSelected(this.configuration.isAlwaysOnTop());
			this.alwaysOnTopItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					alwaysOnTopToggle();
				}
			});
		}
		
		return this.alwaysOnTopItem;
	}

	/**
	 * Monta e retorna item de menu Alterar caminho
	 * @return item de menu Alterar caminho
	 */
	private BTMenuItem getChangePathItem() {
		if( this.changePathItem == null ) {
			this.changePathItem = new BTMenuItem( Language.CHANGE_PATH );
			this.changePathItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					if( getConfiguration().changePath() ) {
							saveCurrentConfiguration( true, true );
					}
				}
			} );
		}
		
		return this.changePathItem;
	}
	
	/**
	 * MOnta e retorna menu com temas
	 * @return
	 */
	private BTMenu getThemeMenu() {
		if ( this.themeMenu == null ) {
			this.themeMenu = new BTMenu( Language.THEMES );
			this.themeMenu.add( this.getThemeDefaultItem() );
			this.themeMenu.add( this.getThemeWindowsItem() );
//			this.themeMenu.add( this.getThemeMacItem() );
		}
		return this.themeMenu;
	}
	
	/**
	 * Monta e retorna item de menu com tema padrao
	 * @return
 	 */
	private BTCheckBoxMenuItem getThemeDefaultItem() {
		if ( this.themeDefaultItem == null ) {
			this.themeDefaultItem = new BTCheckBoxMenuItem( Language.THEME_DEFAULT );
			this.themeDefaultItem.setSelected( this.getConfiguration().getTheme().equals( Configuration.THEME_DEFAULT ) );
			this.themeDefaultItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					clearThemeSelection( Configuration.THEME_DEFAULT );
					getConfiguration().changeTheme( Configuration.THEME_DEFAULT );
				}
			} );
		}
		return this.themeDefaultItem;
	}
	
	/**
	 * Monta e retorna item de menu com tema Windows
	 * @return
 	 */
	private BTCheckBoxMenuItem getThemeWindowsItem() {
		if ( this.themeWindowsItem == null ) {
			this.themeWindowsItem = new BTCheckBoxMenuItem( Language.THEME_WINDOWS );
			this.themeWindowsItem.setSelected( this.getConfiguration().getTheme().equals( Configuration.THEME_WINDOWS ) );
			this.themeWindowsItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					clearThemeSelection( Configuration.THEME_WINDOWS );
					getConfiguration().changeTheme( Configuration.THEME_WINDOWS );
				}
			} );
		}
		return this.themeWindowsItem;
	}
	
	/**
	 * Monta e retorna item de menu com tema MacOS
	 * @return
 	 */
	private BTCheckBoxMenuItem getThemeMacItem() {
		if ( this.themeMacItem == null ) {
			this.themeMacItem = new BTCheckBoxMenuItem( Language.THEME_MAC );
			this.themeMacItem.setSelected( this.getConfiguration().getTheme().equals( Configuration.THEME_MAC ) );
			this.themeMacItem.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					clearThemeSelection( Configuration.THEME_MAC );
					getConfiguration().changeTheme( Configuration.THEME_MAC );
				}
			} );
		}
		return this.themeMacItem;
	}
	
	/**
	 * Monta e retorna item de menu Salvar configura��es
	 * @return item de menu Salvar configura��es
	 */
	private BTMenuItem getSaveConfigurationItem() {
		if(this.saveConfigurationItem == null) {
			this.saveConfigurationItem = new BTMenuItem(Language.SAVE_CONFIGURATION);
			this.saveConfigurationItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					saveCurrentConfiguration( false, false );
				}
			});
		}
		
		return this.saveConfigurationItem;
	}
	
	/**
	 * Monta e retorna menu Ajuda
	 * @return
	 */
	private BTMenu getHelpMenu() {
		if(this.helpMenu == null) {
			this.helpMenu = new BTMenu(Language.HELP);
			this.helpMenu.add(this.getRegexItem());
			this.helpMenu.add(this.getAboutItem());
		}
		
		return this.helpMenu;
	}
	
	/**
	 * Monta e retorna item de menu Expressoes regulares
	 * @return item de menu Expressoes regulares
	 */
	private BTMenuItem getRegexItem() {
		if ( this.regexItem == null ) {
			this.regexItem = new BTMenuItem( Language.REGULAR_EXPRESSIONS );
			this.regexItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showRegexDialog();
				}
			});
		}
		
		return this.regexItem;
	}
	
	/**
	 * Monta e retorna item de menu Sobre
	 * @return item de menu Sobre
	 */
	private BTMenuItem getAboutItem() {
		if(this.aboutItem == null) {
			this.aboutItem = new BTMenuItem(Language.ABOUT);
			this.aboutItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(getInstance(), Language.ABOUT_INFO, Language.ABOUT, JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		
		return this.aboutItem;
	}

	/**
	 * Alterna op��o da janela entre sempre no topo
	 */
	public void alwaysOnTopToggle() {
		this.setAlwaysOnTop(alwaysOnTopItem.isSelected());
	}

	/**
	 * Monta e retorna abas
	 * @return abas
	 */
	protected BTTabbedPane getTabbedPane() {
		if(this.tabbedPane == null) {
			this.searchPanelBrazil = new SearchPanel( Language.FILE_BRAZIL, this, this.getConfiguration().isTabBrazilSplit() );
			this.searchPanelUSA = new SearchPanel( Language.FILE_USA, this, this.getConfiguration().isTabUsaSplit() );
			this.searchPanelSpain = new SearchPanel( Language.FILE_SPAIN, this, this.getConfiguration().isTabSpainSplit() );
			this.searchPanelFrance = new SearchPanel( Language.FILE_FRANCE, this, this.getConfiguration().isTabFranceSplit() );
			this.searchPanelItaly = new SearchPanel( Language.FILE_ITALY, this, this.getConfiguration().isTabItalySplit() );
			this.searchPanelGermany = new SearchPanel( Language.FILE_GERMANY, this, this.getConfiguration().isTabGermanySplit() );
			
			this.tabbedPane = new BTTabbedPane();
			this.showInitialTabs();
		}
		
		return this.tabbedPane;
	}
	
	/**
	 * Seta abas vis�veis conforme configura��o salva
	 */
	private void showInitialTabs() {
		
		this.tabbedPane.addMouseListener( new TabController( this.tabbedPane, this ) );
		
		if(this.configuration.isTabBrazil()) {
			this.tabbedPane.addTab(Language.BRAZIL, this.getIcon("brazil.jpg"), this.searchPanelBrazil);
		}
		
		if(this.configuration.isTabUsa()) {
			this.tabbedPane.addTab(Language.USA, this.getIcon("usa.jpg"), this.searchPanelUSA);
		}
		
		if(this.configuration.isTabSpain()) {
			this.tabbedPane.addTab(Language.SPAIN, this.getIcon("spain.jpg"), this.searchPanelSpain);
		}
		
		if(this.configuration.isTabFrance()) {
			this.tabbedPane.addTab(Language.FRANCE, this.getIcon("france.jpg"), this.searchPanelFrance);
		}
		
		if(this.configuration.isTabItaly()) {
			this.tabbedPane.addTab(Language.ITALY, this.getIcon("italy.jpg"), this.searchPanelItaly);
		}
		
		if(this.configuration.isTabGermany()) {
			this.tabbedPane.addTab(Language.GERMANY, this.getIcon("germany.jpg"), this.searchPanelGermany);
		}
		
	}
	
	/**
	 * Seta atributos da janela e a torna vis�vel
	 */
	private void createAndShowWindow() {
		this.setLayout( new GridLayout(1, 2) );
		this.setTitle( Language.TITLE );
		this.setAlwaysOnTop( this.getAlwaysOnTopItem().isSelected() );
		this.setIconImage( this.getIcon("icon3.png").getImage() );
		
		if( this.getConfiguration().isDefaultTheme() ) {
			this.getContentPane().setBackground( Color.decode("#E8F0F7") );
		}
		
		// Altera estilo da barra de titulo
		if ( this.getConfiguration().isMacTheme() ) {
			this.setUndecorated( true );
			this.getRootPane().setWindowDecorationStyle( JRootPane.FRAME );
			
			ComponentResizer cr = new ComponentResizer();
			cr.registerComponent( this );
			cr.setSnapSize(new Dimension(10, 10));
		}
			
		// Salva configura��es ao fechar app
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing( final WindowEvent windowEvent ) {
		        saveCurrentConfiguration( false, true );
		        System.exit( 0 );
		    }
		});
		
		this.setSize( this.getConfiguration().getWidth(), this.getConfiguration().getHeight() );
		if ( this.getConfiguration().isMaximized() ) {
			this.setExtendedState( JFrame.MAXIMIZED_BOTH );
		}
		
		this.setLocationRelativeTo( null );
		this.setVisible( true );
	}
	
	/**
	 * Adiciona ou remove uma aba
	 * @param title T�tulo da aba
	 * @param icon �cone do idioma da aba
	 * @param panel Panel do idioma da aba
	 */
	public void addRemoveTabs(String title, String icon, SearchPanel panel) {
		boolean hasRemoved = false;
		
		for (int i = 0; i < this.tabbedPane.getTabCount(); i++) {
			if(this.tabbedPane.getTitleAt(i) == title) {
				this.tabbedPane.remove(i);
				hasRemoved = true;
			}
		}
		
		if(!hasRemoved) {
			this.tabbedPane.addTab(title, this.getIcon(icon + ".jpg"), panel);
			this.tabbedPane.setSelectedIndex(this.tabbedPane.getTabCount()-1);
			panel.getSearchField().setText("");
			panel.doGridSearch();
			panel.getSearchField().requestFocus();
		}
	}
	
	/**
	 * Salva op��es atuais
	 * @param isChanging Indica se � altera��o de caminho
	 */
	public void saveCurrentConfiguration( final boolean isChanging, final boolean isClosing ) {

		// Seta configura��es personalizadas
		this.getConfiguration().setMaximized( this.getExtendedState() == JFrame.MAXIMIZED_BOTH );
		if ( !this.getConfiguration().isMaximized() ) { 
			this.getConfiguration().setWidth( this.getWidth() );
			this.getConfiguration().setHeight( this.getHeight() );
		}
		this.getConfiguration().setAlwaysOnTop( this.getAlwaysOnTopItem().isSelected() );
		
		this.getConfiguration().setTabUsa( this.getViewUSAItem().isSelected() );
		this.getConfiguration().setTabSpain( this.getViewSpainItem().isSelected() );
		this.getConfiguration().setTabFrance( this.getViewFranceItem().isSelected() );
		this.getConfiguration().setTabItaly( this.getViewItalyItem().isSelected() );
		this.getConfiguration().setTabGermany( this.getViewGermanyItem().isSelected() );
		
		final boolean saved = this.getConfiguration().save();
		
		if( !isClosing ) {
			if(saved) {
				JOptionPane.showMessageDialog(this, Language.CONFIGURATION_SAVE_SUCCESS, "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, Language.CONFIGURATION_SAVE_ERROR, "", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if( isChanging ) {
			if ( this.regexDialog != null ) {
				this.regexDialog.dispose();
			}
			this.reinitialize();
		}
	}
	
	/**
	 * Limpa sele��es dos checkboxes dos temas
	 */
	private void clearThemeSelection( final String selected ) {
		this.getThemeDefaultItem().setSelected( selected.equals( Configuration.THEME_DEFAULT ) );
		this.getThemeWindowsItem().setSelected( selected.equals( Configuration.THEME_WINDOWS ) );
		this.getThemeMacItem().setSelected( selected.equals( Configuration.THEME_MAC ) );
	}
	
	/**
	 * Exibe janela com expressoes regulares
	 */
	public void showRegexDialog() {
		if ( this.regexDialog == null ) {
			this.regexDialog = new RegexDialog();
			this.regexDialog.output();
		}

		this.regexDialog.setVisible( true );
		this.regexDialog.requestFocus();
	}
	
	/**
	 * Recarrega grids de cada aba
	 */
	public void refreshGrids() {
		this.searchPanelBrazil.doGridSearch();
		this.searchPanelUSA.doGridSearch();
		this.searchPanelSpain.doGridSearch();
		this.searchPanelFrance.doGridSearch();
		this.searchPanelItaly.doGridSearch();
		this.searchPanelGermany.doGridSearch();
	}

	/**
	 * Monta e retorna lista com os paineis de idiomas
	 * @return
	 */
	public List<SearchPanel> getSearchPanelList() {
		if( this.searchPanelList == null ) {
			this.searchPanelList = new ArrayList<>();
			this.searchPanelList.add( this.searchPanelBrazil );
			this.searchPanelList.add( this.searchPanelUSA );
			this.searchPanelList.add( this.searchPanelSpain);
			this.searchPanelList.add( this.searchPanelFrance );
			this.searchPanelList.add( this.searchPanelItaly );
			this.searchPanelList.add( this.searchPanelGermany );
		}
		return this.searchPanelList;
	}
	
	/**
	 * Reinicializa a aplica��o
	 */
	public void reinitialize() {
		new MainFrame();
		this.dispose();
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
	 * Retorna inst�ncia da janela
	 * @return Inst�ncia da janela
	 */
	public MainFrame getInstance() {
		return this;
	}
	
	/**
	 * Retorna painel do idioma Portugu�s(Brasil)
	 * @return painel do idioma Portugu�s(Brasil)
	 */
	public SearchPanel getSearchPanelBrazil() {
		return this.searchPanelBrazil;
	}

	/**
	 * Retorna painel do idioma Ingl�s(EUA)
	 * @return painel do idioma Ingl�s(EUA)
	 */
	public SearchPanel getSearchPanelUSA() {
		return this.searchPanelUSA;
	}

	/**
	 * Retorna painel do idioma Espanhol
	 * @return painel do idioma Espanhol
	 */
	public SearchPanel getSearchPanelSpain() {
		return this.searchPanelSpain;
	}

	/**
	 * Retorna painel do idioma Franc�s
	 * @return painel do idioma Franc�s
	 */
	public SearchPanel getSearchPanelFrance() {
		return this.searchPanelFrance;
	}

	/**
	 * Retorna painel do idioma Italiano
	 * @return painel do idioma Italiano
	 */
	public SearchPanel getSearchPanelItaly() {
		return this.searchPanelItaly;
	}

	/**
	 * Retorna painel do idioma Alem�o
	 * @return painel do idioma Alem�o
	 */
	public SearchPanel getSearchPanelGermany() {
		return this.searchPanelGermany;
	}

	/**
	 * Retorna configura��es atuais
	 * @return configura��es atuais do aplicativo
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}

}