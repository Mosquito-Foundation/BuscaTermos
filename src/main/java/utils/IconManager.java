package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManager {
	
	private static IconManager INSTANCE;
	
	public static IconManager getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new IconManager();
		}
		return INSTANCE;
	}
	
	public ImageIcon getIcon( final String name, final int width, final int height ) {
		ImageIcon icon = this.getIcon( name );
		icon = this.resizeIcon( icon, width, height );
		return icon;
	}

	public ImageIcon getIcon( final String name ) {
		return new ImageIcon( getClass().getClassLoader().getResource( "images/" + name ) );
	}
	
	private ImageIcon resizeIcon( ImageIcon icon, final int width, final int height ) {
		Image img = icon.getImage();
		img = img.getScaledInstance( width, height, Image.SCALE_SMOOTH );
		icon = new ImageIcon( img );
		return icon;
	}
	
}
