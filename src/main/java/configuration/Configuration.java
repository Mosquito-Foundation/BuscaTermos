package configuration;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SkinInfo;
import org.pushingpixels.substance.internal.fonts.FontPolicies;

import configuration.language.BrazilLanguage;
import configuration.language.ChinaLanguage;
import configuration.language.FranceLanguage;
import configuration.language.GermanyLanguage;
import configuration.language.ItalyLanguage;
import configuration.language.Language;
import configuration.language.Languages;
import configuration.language.SlovakiaLanguage;
import configuration.language.SpainLanguage;
import configuration.language.TurkeyLanguage;
import configuration.language.UsaLanguage;
import gui.MainFrame;
import utils.Token;
import version.VersionControl;

public final class Configuration implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CONF_PATH = "C:/Users/" + System.getProperty("user.name") + "/Documents/buscaTermos.config";
	
	private static Configuration INSTANCE;
	
	private String path;
	
	private Dimension dimension;

	private boolean alwaysOnTop;
	
	private boolean maximized;
	
	private HashMap<Languages, Boolean> visibleTabs;
	
	private String theme;

	private String version;
	
	private LinkedHashMap<Languages, Language> languages;
	
	private Configuration() {
		this.createDefaultConfiguration();
	}
	
	public static Configuration getInstance() {
		if( INSTANCE == null ) {
			try {
				ObjectInputStream in = new ObjectInputStream( new FileInputStream( Configuration.CONF_PATH ) );
				INSTANCE = (Configuration) in.readObject();
				in.close();
			} catch ( IOException | ClassNotFoundException e ) {
				INSTANCE = new Configuration();
			}
			loadLanguages();
		}
		return INSTANCE;
	}

	public void loadTheme() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SubstanceLookAndFeel.setSkin(INSTANCE.getTheme());	
				SubstanceLookAndFeel.setFontPolicy(FontPolicies.getLogicalFontsPolicy());
			}
		});
	}
	
	// FIXME Instanciar automaticamente classes do package configuration.language
	private static void loadLanguages() {
		INSTANCE.languages = new LinkedHashMap<>();
		INSTANCE.languages.put( Languages.BRAZIL, new BrazilLanguage() );
		INSTANCE.languages.put( Languages.USA, new UsaLanguage() );
		INSTANCE.languages.put( Languages.SPAIN, new SpainLanguage() );
		INSTANCE.languages.put( Languages.FRANCE, new FranceLanguage() );
		INSTANCE.languages.put( Languages.ITALY, new ItalyLanguage() );
		INSTANCE.languages.put( Languages.GERMANY, new GermanyLanguage() );
		INSTANCE.languages.put( Languages.TURKEY, new TurkeyLanguage() );
		INSTANCE.languages.put( Languages.SLOVAKIA, new SlovakiaLanguage() );
		INSTANCE.languages.put( Languages.CHINA, new ChinaLanguage() );
	}
	
	public boolean save() {
		this.setAlwaysOnTop( MainFrame.getInstance().isAlwaysOnTop() );
		this.setMaximized( MainFrame.getInstance().isMaximized() );
		if ( !this.isMaximized() ) { 
			this.setDimension( MainFrame.getInstance().getSize() );
		}
		
		try {
			final ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( Configuration.CONF_PATH ) );
			out.writeObject( this );
			out.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void createDefaultConfiguration() {
		// Configurações
		this.setPath( "C:\\" );
		this.setDimension( new Dimension(600, 500) );
		this.setAlwaysOnTop( false );
		this.setVersion( "1.0.0" );
		
		// Abas
		this.visibleTabs = new HashMap<>();
		for (Languages languageId : Languages.values()) {
			this.visibleTabs.put(languageId, languageId.isDefaultTab());
		}
		
		// Tema
		this.setTheme("org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin");
	}
	
	public boolean isPathValid() {
		File file = new File(this.getPath() + "sesuite." + Token.FILE_BRAZIL + ".utf-8.inc");
		return file.exists() && !file.isDirectory();
	}
	
	public boolean createInitialPath() {
		return this.createPath( true, false );
	}
	
	public boolean changePath() {
		return this.createPath( false, true );
	}
	
	private boolean createPath( final boolean isInitializing, final boolean isChanging ) {
		if( this.isPathValid() && !isChanging ) {
			return true;
		} else {
			String oldPath = this.getPath();
			
			// Se vai exibir "arquivo nao encontrado"
			boolean showNotification = isInitializing;
			
			String newPath = this.findPath( showNotification );
			
			if( !newPath.isEmpty() ) {
				this.setPath( newPath + "\\" ); 
			}
			
			if( this.isPathValid() ) {
				return !this.getPath().equals( oldPath );
			} else {
				if( this.confirmExit() ) {
					if( isChanging ) {
						this.setPath( oldPath );
					} else {
						System.exit( 0 );
					}
				} else {
					return this.createPath( isInitializing, isChanging );
				}
			}
			
			return false;
		}
	}
	
	private String findPath( final boolean showNotification ) {
		if( showNotification ) {
			JOptionPane.showMessageDialog( null, Token.FILE_NOT_FOUND_INFO, "", JOptionPane.WARNING_MESSAGE );
		}
		
		final JFileChooser chooser = new JFileChooser( this.getPath() );
		chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		
		final int chooserResponse = chooser.showOpenDialog( chooser );
		
		if(chooserResponse == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getPath(); 
		}
		
		return "";
	}
	
	private boolean confirmExit() {
		final int choice = JOptionPane.showConfirmDialog( null, Token.FILE_NOT_FOUND_QUESTION, "", JOptionPane.YES_NO_OPTION );
		return choice != JOptionPane.YES_OPTION;
	}
	
	public void changeTheme(SkinInfo skin ) {
		INSTANCE.setTheme(skin.getClassName());
		INSTANCE.loadTheme();
	}
		
	public String getPath() {
		return this.path;
	}
	
	private void setPath( final String path ) {
		this.path = path;
	}

	public Dimension getDimension() {
		return this.dimension;
	}
	
	private void setDimension( final Dimension dimension ) {
		this.dimension = dimension;
	}
	
	public boolean isAlwaysOnTop() {
		return this.alwaysOnTop;
	}
	
	public void setAlwaysOnTop( final boolean bool ) {
		this.alwaysOnTop = bool;
	}
	
	public boolean isMaximized() {
		return this.maximized;
	}
	
	private void setMaximized( final boolean bool ) {
		this.maximized = bool;
	}
	
	public boolean isVisibleTab( final Languages languageId ) {
		return this.visibleTabs.get( languageId );
	}
	
	public void setTabState( final Languages tabLanguageId, final boolean visible ) {
		this.visibleTabs.put( tabLanguageId, visible );
	}
	
 	public String getTheme() {
		return this.theme;
	}
	
	private void setTheme( final String theme ) {
		this.theme = theme;
	}
	
	public String getVersion() {
		return this.version == null ? VersionControl.getInstance().getCurrentVersion().toString() : this.version;
	}
	
	public void setVersion( final String version ) {
		this.version = version;
	}

	public HashMap<Languages, Language> getLanguages() {
		return this.languages;
	}

	@Override
	public String toString() {
		String instance = "Configuration\n" +
				"\n\tPath: " + this.path +
				"\n\tDimension: " + this.dimension.width + "w " + this.dimension.height + "h" +
				"\n\tMaximized: " + ( this.maximized ? "true" : "false" ) +
				"\n\tTop: " + ( this.alwaysOnTop ? "true" : "false" ) +
				"\n\tVersion: " + this.version +
				"\n\tTabs: ";
		for (Language language : this.getLanguages().values()) {
			instance += "\n\t\t-" + language.getTitle() + ": " + ( this.visibleTabs.get( language.getId() ) ? "true" : "false" );
		}
		return instance;
	}
	
}
