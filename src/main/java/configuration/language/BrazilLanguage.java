package configuration.language;

import javax.swing.ImageIcon;

import configuration.Configuration;
import utils.IconManager;
import utils.Token;

public class BrazilLanguage implements Language {

	private static final long serialVersionUID = 1L;

	@Override
	public Languages getId() {
		return Languages.BRAZIL;
	}
	
	@Override
	public String getFileName() {
		return Token.FILE_BRAZIL;
	}

	@Override
	public String getTitle() {
		return Token.BRAZIL;
	}

	@Override
	public ImageIcon getIcon() {
		return IconManager.getInstance().getIcon("flags/brazil.gif");
	}

	@Override
	public boolean isVisible() {
		return Configuration.getInstance().isVisibleTab( this.getId() );
	}

}
