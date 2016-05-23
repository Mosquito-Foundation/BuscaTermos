package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManager {

	public enum Flags {
		
	}
	
	/**
	 * Instância única
	 */
	private static IconManager INSTANCE;
	
	/**
	 * Retorna instância única
	 * @return
	 */
	public static IconManager getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new IconManager();
		}
		return INSTANCE;
	}
	
	/**
	 * Retorna ícone redimensionado
	 * @param name Nome do ícone
	 * @param width Largura do ícone
	 * @param height Altura do ícone
	 * @return
	 */
	public ImageIcon getIcon( final String name, final int width, final int height ) {
		ImageIcon icon = this.getIcon( name );
		icon = this.resizeIcon( icon, width, height );
		return icon;
	}
	
	/**
	 * Retorna ícone em seu tamanho normal
	 * @param name Nome do ícone
	 * @return
	 */
	public ImageIcon getIcon( final String name ) {
		return new ImageIcon( getClass().getClassLoader().getResource( "images/" + name ) );
	}
	
	/**
	 * Redimensiona icones
	 * @param icon Icone para ser redimensionado
	 * @param width Nova largura
	 * @param height Nova altura
	 * @return Icone redimensionado
	 */
	private ImageIcon resizeIcon( ImageIcon icon, final int width, final int height ) {
		Image img = icon.getImage();
		img = img.getScaledInstance( width, height, Image.SCALE_SMOOTH );
		icon = new ImageIcon( img );
		return icon;
	}
}
