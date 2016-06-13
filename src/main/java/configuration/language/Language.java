package configuration.language;

import java.io.Serializable;

import javax.swing.ImageIcon;

public interface Language extends Serializable{

	public Languages getId();
	
	public String getFileName();
	
	public String getTitle();
	
	public ImageIcon getIcon();
	
	public boolean isVisible();
	
}
