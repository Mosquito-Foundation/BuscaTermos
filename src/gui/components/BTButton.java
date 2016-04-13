package gui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import conf.Configuration;

public class BTButton extends JButton {

	private static final long serialVersionUID = 1L;

	private String iconName;
	
	private int iconWidth = 0;
	
	private int iconHeight = 0;
	
	public BTButton( String iconName ) {
		super();
		this.iconName = iconName;
		this.init();
	}
	
	public BTButton( String iconName, int iconWidth, int iconHeight ) {
		super();
		this.iconName = iconName;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
		this.init();
	}
	
	private void init() {
		this.setIcon( this.getButtonIcon() );
		this.setFocusable( false );
		this.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
		if ( Configuration.getInstance().isDefaultTheme() ) {
			this.setBackground( Color.WHITE );
			this.setBorder( BorderFactory.createMatteBorder( 1, 0, 1, 1, Color.GRAY ) );
		}
	}
	
	public void setIcon( final String iconName ) {
		this.iconName = iconName;
		super.setIcon( this.getButtonIcon() );
	}
	
	private ImageIcon getButtonIcon() {
		ImageIcon icon;
		try {
			icon = new ImageIcon(getClass().getResource("/" + this.getIconName()));
		} catch(NullPointerException e) {
			icon = new ImageIcon("images/" + this.getIconName());
		}
		
		if( this.iconWidth > 0 && this.iconHeight > 0 ) {
			icon = this.resizeIcon( icon, this.iconWidth, this.iconHeight );
		}
		
		return icon;
	}

	public String getIconName() {
		return iconName;
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
