package configuration.language;

import javax.swing.ImageIcon;

import configuration.Configuration;
import utils.IconManager;
import utils.Token;

public class SlovakiaLanguage implements Language {

	private static final long serialVersionUID = 1L;

	@Override
	public Languages getId() {
		return Languages.SLOVAKIA;
	}
	
	@Override
	public String getFileName() {
		return Token.FILE_SLOVAKIA;
	}

	@Override
	public String getTitle() {
		return Token.SLOVAKIA;
	}

	@Override
	public ImageIcon getIcon() {
		return IconManager.getInstance().getIcon("flags/slovakia.gif");
	}

	@Override
	public boolean isVisible() {
		return Configuration.getInstance().isVisibleTab( this.getId() );
	}

}
