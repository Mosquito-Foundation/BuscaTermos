package configuration.language;

import javax.swing.ImageIcon;

import configuration.Configuration;
import utils.IconManager;
import utils.Token;

public class SpainLanguage implements Language {

	private static final long serialVersionUID = 1L;

	@Override
	public Languages getId() {
		return Languages.SPAIN;
	}
	
	@Override
	public String getFileName() {
		return Token.FILE_SPAIN;
	}

	@Override
	public String getTitle() {
		return Token.SPAIN;
	}

	@Override
	public ImageIcon getIcon() {
		return IconManager.getInstance().getIcon("flags/spain.gif");
	}

	@Override
	public boolean isVisible() {
		return Configuration.getInstance().isVisibleTab( this.getId() );
	}

}
