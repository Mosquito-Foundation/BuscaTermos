package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import configuration.Configuration;
import configuration.language.Language;
import configuration.language.Languages;
import configuration.themes.Themes;
import gui.components.BTCheckBoxMenuItem;
import gui.components.BTMainFrame;
import gui.components.BTMenu;
import gui.components.BTMenuBar;
import gui.components.BTMenuItem;
import gui.components.BTRadioButtonMenuItem;
import gui.components.BTTabbedPane;
import utils.Token;

public class MainFrame extends BTMainFrame {

	private static final long serialVersionUID = 1L;

	private static MainFrame INSTANCE;
	
	private HashMap<Languages, LanguagePanel> languagePanelMap;
	
	private HashMap<Languages, BTCheckBoxMenuItem> languageCheckMap;
	
	private BTTabbedPane tabbedPane;
	
	private MainFrame() {
		// Validação para o path, se o usuário informar um path inválido e não quiser mais procurar um válido, sai da aplicação
		if( !Configuration.getInstance().isPathValid() ) {
			if( !Configuration.getInstance().createInitialPath() ) {
				System.exit( 0 );
			}
		}
		
		this.languagePanelMap = new HashMap<>();
		this.languageCheckMap = new HashMap<>();
		
		this.setJMenuBar( this.getToolbar() );
		this.add( this.getLanguageTabs() );
	}
	
	public static MainFrame getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new MainFrame();
		}
		return INSTANCE;
	}

	private BTMenuBar getToolbar() {
		final BTMenuBar toolbar = new BTMenuBar();
		toolbar.add( this.getDisplayMenu() );
		toolbar.add( this.getOptionsMenu() );
		toolbar.add( this.getHelpMenu() );
		return toolbar;
	}
	
	private BTMenu getDisplayMenu() {
		final BTMenu displayMenu = new BTMenu( Token.DISPLAY );
		for ( Language language : Configuration.getInstance().getLanguages().values() ) {
			displayMenu.add( this.getLanguageItem( language ) );
		}
		return displayMenu;
	}
	
	private BTCheckBoxMenuItem getLanguageItem( final Language language ) {
		final BTCheckBoxMenuItem languageItem = new BTCheckBoxMenuItem( language.getTitle(), language.isVisible() );
		if ( language.getId().equals( Languages.BRAZIL ) ) {
			languageItem.setEnabled( false );
		}
		languageItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showHideTab( language );
			}
		});
		
		this.languageCheckMap.put( language.getId(), languageItem );
		
		return languageItem;
	}
	
	private BTMenu getOptionsMenu() {
		final BTMenu optionsMenu = new BTMenu( Token.OPTIONS );
		optionsMenu.add( this.getAlwaysOnTopItem() );
		optionsMenu.addSeparator();
		optionsMenu.add( this.getChangePathItem() );
		optionsMenu.add( this.getThemeMenu() );
		optionsMenu.addSeparator();
		optionsMenu.add( this.getSaveConfigurationItem() );
		return optionsMenu;
	}
	
	private BTCheckBoxMenuItem getAlwaysOnTopItem() {
		final BTCheckBoxMenuItem alwaysOnTopItem = new BTCheckBoxMenuItem( Token.ALWAYS_ON_TOP, Configuration.getInstance().isAlwaysOnTop() );
		alwaysOnTopItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().setAlwaysOnTop( ((BTCheckBoxMenuItem) e.getSource()).isSelected() );
			}
		});
		return alwaysOnTopItem;
	}
	
	private BTMenuItem getChangePathItem() {
		final BTMenuItem changePathItem = new BTMenuItem( Token.CHANGE_PATH );
		changePathItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().setAlwaysOnTop( false );
				
				if ( Configuration.getInstance().changePath() ) {
					Configuration.getInstance().save();
				}
				
				MainFrame.getInstance().setAlwaysOnTop( getAlwaysOnTopItem().isSelected() );
			}
		});
		return changePathItem;
	}
	
	private BTMenu getThemeMenu() {
		final BTMenu themeMenu = new BTMenu( Token.THEMES );
		themeMenu.add( this.getDefaultThemeItem() );
		themeMenu.add( this.getSystemThemeItem() );
		return themeMenu;
	}
	
	// FIXME Fazer criação das opções de tema dinamicamente
	private BTRadioButtonMenuItem getDefaultThemeItem() {
		final BTRadioButtonMenuItem defaultTheme = new BTRadioButtonMenuItem( Token.THEME_DEFAULT, Configuration.getInstance().isDefaultTheme() );
		defaultTheme.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Configuration.getInstance().changeTheme( Themes.DEFAULT );
			}
		});
		return defaultTheme;
	}
	
	private BTRadioButtonMenuItem getSystemThemeItem() {
		final BTRadioButtonMenuItem systemTheme = new BTRadioButtonMenuItem( Token.THEME_SYSTEM, Configuration.getInstance().isSystemTheme() );
		systemTheme.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Configuration.getInstance().changeTheme( Themes.SYSTEM );
			}
		});
		return systemTheme;
	}
	
	private BTMenuItem getSaveConfigurationItem() {
		final BTMenuItem saveItem = new BTMenuItem( Token.SAVE_CONFIGURATION );
		saveItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Configuration.getInstance().save();
			}
		});
		return saveItem;
	}
	
	private BTMenu getHelpMenu() {
		final BTMenu helpMenu = new BTMenu( Token.HELP );
		helpMenu.add( this.getRegexItem() );
		helpMenu.add( this.getAboutItem() );
		return helpMenu;
	}
	
	private BTMenuItem getRegexItem() {
		final BTMenuItem regexItem = new BTMenuItem( Token.REGULAR_EXPRESSIONS );
		regexItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegexDialog();
			}
		});
		return regexItem;
	}
	
	private BTMenuItem getAboutItem() {
		final BTMenuItem aboutItem = new BTMenuItem( Token.ABOUT );
		aboutItem.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog( MainFrame.getInstance(), Token.ABOUT_INFO, Token.ABOUT, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		return aboutItem;
	}

	public BTTabbedPane getLanguageTabs() {
		if ( this.tabbedPane == null ) {
			this.tabbedPane = new BTTabbedPane();
			for ( Language language : Configuration.getInstance().getLanguages().values() ) {
				this.languagePanelMap.put( language.getId(), new LanguagePanel( language ) );
				if ( language.isVisible() ) {
					this.showTab( language );
				}
			}
		}
		return this.tabbedPane;
	}
	
	private void showHideTab( final Language language ) {
		final boolean hasRemoved = this.hideTab( language );
		if ( !hasRemoved ) {
			this.showTab( language );
			this.getLanguageTabs().setSelectedIndex( this.getLanguageTabs().getTabCount() - 1 );
			this.getLanguagePanelMap().get( language.getId() ).getFilterField().setText( "" );
			this.getLanguagePanelMap().get( language.getId() ).getFilterField().requestFocus();
		}
	}
	
	public void showTab( final Language language ) {
		this.getLanguageTabs().addTab( language.getTitle(), language.getIcon(), this.languagePanelMap.get( language.getId() ) );
		Configuration.getInstance().setTabState( language.getId(), true );
	}
	
	private boolean hideTab( final Language language ) {
		for ( int i = 0; i < this.getLanguageTabs().getTabCount(); i++ ) {
			if ( this.getLanguageTabs().getTitleAt( i ).equals( language.getTitle() ) ) {
				this.getLanguageTabs().remove( i );
				Configuration.getInstance().setTabState( language.getId(), false );
				return true;
			}
		}
		return false;
	}
	
	public void removeTabByIndex( final int index ) {
		for (Languages language : Languages.values()) {
			if ( this.getLanguagePanelMap().get( language ).equals( this.getLanguageTabs().getComponentAt( index ) ) ) {
				this.getLanguageTabs().removeTabAt( index );
				this.getLanguageCheckMap().get( language ).setSelected( false );
				Configuration.getInstance().setTabState( language, false );
				return;
			}
		}
	}
	
	public int getTabIndexByTitle( final String title ) {
		for ( int i = 0; i < this.getLanguageTabs().getTabCount(); i++ ) {
			if( this.getLanguageTabs().getTitleAt( i ).equals( title ) ) {
				return i;
			}
		}
		return -1;
	}
	
	public HashMap<Languages, LanguagePanel> getLanguagePanelMap() {
		return this.languagePanelMap;
	}

	private HashMap<Languages, BTCheckBoxMenuItem> getLanguageCheckMap() {
		return this.languageCheckMap;
	}
	
}
