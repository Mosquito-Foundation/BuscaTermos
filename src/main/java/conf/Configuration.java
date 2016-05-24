package conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import language.Language;

public final class Configuration implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CONF_PATH = "C:/Users/" + System.getProperty("user.name") + "/Documents/buscaTermos.config";
	
	public static final String THEME_DEFAULT = "default";
	
	public static final String THEME_WINDOWS = "windows";
	
	
	/**
	 * Unica instância da classe
	 */
	private static Configuration INSTANCE;
	
	/**
	 * Caminho dos arquivos
	 */
	private String path;
	
	/**
	 * Largura e altura da janela
	 */
	private int width, height;
	
	/**
	 * Indica se janela deve estar sempre no topo
	 */
	private boolean alwaysOnTop;
	
	/**
	 * Indica se o programa abre maximizado
	 */
	private boolean maximized;
	
	/**
	 * Indicadores de visibilidade das abas
	 */
	private boolean tabBrazil, tabUsa, tabSpain, tabFrance, tabItaly, tabGermany, tabTurkey, tabSlovakia, tabChina;
	
	/**
	 * Indicadores de visibilidade dos painéis inferiores das abas
	 */
	private boolean tabBrazilSplit, tabUsaSplit, tabSpainSplit, tabFranceSplit, tabItalySplit, tabGermanySplit, tabTurkeySplit, tabSlovakiaSplit, tabChinaSplit; 
	
	/**
	 * Tema utilizado
	 */
	private String theme;

	/**
	 * Última versão aberta
	 */
	private String version;
	
	/**
	 * Construtor
	 */
	private Configuration() {
		this.createDefaultConfiguration();
	}
	
	/**
	 * Retorna instância única da classe
	 * @return
	 */
	public static Configuration getInstance() {
		if( INSTANCE == null ) {
			try {
				ObjectInputStream in = new ObjectInputStream( new FileInputStream( CONF_PATH ) );
				INSTANCE = (Configuration) in.readObject();
				in.close();
			} catch ( IOException | ClassNotFoundException e ) {
				INSTANCE = new Configuration();
			}
			loadTheme();
		}
		return INSTANCE;
	}

	/**
	 * Carrega o tema
	 */
	private static void loadTheme() {
		if ( INSTANCE.getTheme() == null ) {
			INSTANCE.setTheme( Configuration.THEME_DEFAULT );
		}
		
		try {
			switch ( INSTANCE.getTheme() ) {
			case Configuration.THEME_WINDOWS:
				INSTANCE.setTheme( Configuration.THEME_WINDOWS );
				UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
				break;
			default:
				INSTANCE.setTheme( Configuration.THEME_DEFAULT );
				UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
				break;
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Salva configurações
	 * @return Indica se foi salvo corretamente
	 */
	public boolean save() {
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
		this.setWidth( 600 );
		this.setHeight( 500 );
		this.setAlwaysOnTop( false );
		this.setTheme( Configuration.THEME_DEFAULT );
		this.setVersion( "1.0.0" );
		
		// Abas
		this.setTabBrazil( true );
		this.setTabUsa( true );
		this.setTabSpain( true );
		this.setTabFrance( false );
		this.setTabItaly( false );	
		this.setTabGermany( false );
		this.setTabTurkey( false );
		this.setTabSlovakia( false );
		this.setTabChina( false );
		
		// Splits
		this.setTabBrazilSplit( true );
		this.setTabUsaSplit( true );
		this.setTabSpainSplit( true );
		this.setTabFranceSplit( true );
		this.setTabItalySplit( true );
		this.setTabGermanySplit( true );
		this.setTabTurkeySplit( true );
		this.setTabSlovakiaSplit( true );
		this.setTabChinaSplit( true );
	}
	
	/**
	 * Retorna se o path é válido
	 * @return
	 */
	public boolean isPathValid() {
		File file = new File(this.getPath() + "sesuite." + Language.FILE_BRAZIL + ".utf-8.inc");
		return file.exists() && !file.isDirectory();
	}
	
	/**
	 * Inicializa processo de criação do path
	 * @return Retorna se path é válido
	 */
	public boolean createInitialPath() {
		return this.createPath( true, false );
	}
	
	/**
	 * Inicializa processo de alteração de path
	 * @return Retorna se path é valido
	 */
	public boolean changePath() {
		return this.createPath( false, true );
	}
	
	/**
	 * Valida configurações de path dos arquivos
	 * @param isInitializing Indica se o aplicativo está inicalizando
	 * @param isChanging Indica se é alteração de path
	 * @return Retorna se path é válido
	 */
	public boolean createPath( final boolean isInitializing, final boolean isChanging ) {
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
	
	/**
	 * Exibe janela para busca do novo path
	 * @param showNotification Indica se é para exibir mensagem de path não encontrado
	 * @return Novo path
	 */
	private String findPath( final boolean showNotification ) {
		if( showNotification ) {
			JOptionPane.showMessageDialog( null, Language.FILE_NOT_FOUND_INFO, "", JOptionPane.WARNING_MESSAGE );
		}
		
		final JFileChooser chooser = new JFileChooser( this.getPath() );
		chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		
		final int chooserResponse = chooser.showOpenDialog( chooser );
		
		if(chooserResponse == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getPath(); 
		}
		
		return "";
	}
	
	/**
	 * Retorna se o usuário não deseja procurar outro caminho para os arquivos dos termos
	 * @return
	 */
	private boolean confirmExit() {
		final int choice = JOptionPane.showConfirmDialog( null, Language.FILE_NOT_FOUND_QUESTION, "", JOptionPane.YES_NO_OPTION );
		return choice != JOptionPane.YES_OPTION;
	}
	
	/**
	 * Altera tema e mostra mensagem
	 * @param newTheme Novo tema
	 */
	public void changeTheme( final String newTheme ) {
		this.setTheme( newTheme );
		JOptionPane.showMessageDialog(null, Language.CHANGE_THEME_WARNING, "", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Retorna se é o tema default
	 * @return É tema default
	 */
	public boolean isDefaultTheme() {
		return this.getTheme().equals( Configuration.THEME_DEFAULT );
	}
	
	/**
	 * Retorna se é o tema do windows
	 * @return É tema do windows
	 */
	public boolean isWindowsTheme() {
		return this.getTheme().equals( Configuration.THEME_WINDOWS );
	}
		
	public String getPath() {
		return this.path;
	}
	
	public void setPath( final String path ) {
		this.path = path;
	}

	public int getWidth() {
		return this.width;
	}
	
	public void setWidth( final int width ) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public void setHeight( final int height ) {
		this.height = height;
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
	
	public void setMaximized( final boolean bool ) {
		this.maximized = bool;
	}
	
	public boolean isTabBrazil() {
		return this.tabBrazil;
	}

	public void setTabBrazil( final boolean tabBrazil ) {
		this.tabBrazil = tabBrazil;
	}

	public boolean isTabUsa() {
		return this.tabUsa;
	}

	public void setTabUsa( final boolean tabUsa ) {
		this.tabUsa = tabUsa;
	}

	public boolean isTabSpain() {
		return this.tabSpain;
	}

	public void setTabSpain( final boolean tabSpain ) {
		this.tabSpain = tabSpain;
	}

	public boolean isTabFrance() {
		return this.tabFrance;
	}

	public void setTabFrance( final boolean tabFrance ) {
		this.tabFrance = tabFrance;
	}

	public boolean isTabItaly() {
		return this.tabItaly;
	}

	public void setTabItaly( final boolean tabItaly ) {
		this.tabItaly = tabItaly;
	}

	public boolean isTabGermany() {
		return this.tabGermany;
	}

	public void setTabGermany( final boolean tabGermany ) {
		this.tabGermany = tabGermany;
	}

	public boolean isTabTurkey() {
		return this.tabTurkey;
	}

	public void setTabTurkey( final boolean tabTurkey ) {
		this.tabTurkey = tabTurkey;
	}
	
	public boolean isTabSlovakia() {
		return this.tabSlovakia;
	}

	public void setTabSlovakia( final boolean tabSlovakia ) {
		this.tabSlovakia = tabSlovakia;
	}
	
	public boolean isTabChina() {
		return this.tabChina;
	}

	public void setTabChina( final boolean tabChina ) {
		this.tabChina = tabChina;
	}
	
	public boolean isTabBrazilSplit() {
		return this.tabBrazilSplit;
	}

	public void setTabBrazilSplit( final boolean tabBrazilSplit ) {
		this.tabBrazilSplit = tabBrazilSplit;
	}

	public boolean isTabUsaSplit() {
		return this.tabUsaSplit;
	}

	public void setTabUsaSplit( final boolean tabUsaSplit ) {
		this.tabUsaSplit = tabUsaSplit;
	}

	public boolean isTabSpainSplit() {
		return this.tabSpainSplit;
	}

	public void setTabSpainSplit( final boolean tabSpainSplit ) {
		this.tabSpainSplit = tabSpainSplit;
	}

	public boolean isTabFranceSplit() {
		return this.tabFranceSplit;
	}

	public void setTabFranceSplit( final boolean tabFranceSplit ) {
		this.tabFranceSplit = tabFranceSplit;
	}

	public boolean isTabItalySplit() {
		return this.tabItalySplit;
	}

	public void setTabItalySplit( final boolean tabItalySplit ) {
		this.tabItalySplit = tabItalySplit;
	}

	public boolean isTabGermanySplit() {
		return this.tabGermanySplit;
	}

	public void setTabGermanySplit( final boolean tabGermanySplit ) {
		this.tabGermanySplit = tabGermanySplit;
	}
	
	public boolean isTabTurkeySplit() {
		return this.tabTurkeySplit;
	}

	public void setTabTurkeySplit( final boolean tabTurkeySplit ) {
		this.tabTurkeySplit = tabTurkeySplit;
	}
	
	public boolean isTabSlovakiaSplit() {
		return this.tabSlovakiaSplit;
	}

	public void setTabSlovakiaSplit( final boolean tabSlovakiaSplit ) {
		this.tabSlovakiaSplit = tabSlovakiaSplit;
	}
	
	public boolean isTabChinaSplit() {
		return this.tabChinaSplit;
	}

	public void setTabChinaSplit( final boolean tabChinaSplit ) {
		this.tabChinaSplit = tabChinaSplit;
	}

 	public String getTheme() {
		return this.theme;
	}
	
	public void setTheme( final String theme ) {
		this.theme = theme;
	}
	
	public String getVersion() {
		return this.version == null ? "2.1.0" : this.version;
	}
	
	public void setVersion( final String version ) {
		this.version = version;
	}

}
