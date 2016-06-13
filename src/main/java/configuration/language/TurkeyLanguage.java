package configuration.language;

import javax.swing.ImageIcon;

import configuration.Configuration;
import utils.IconManager;
import utils.Token;

public class TurkeyLanguage implements Language {

	private static final long serialVersionUID = 1L;

	@Override
	public Languages getId() {
		return Languages.TURKEY;
	}
	
	@Override
	public String getFileName() {
		return Token.FILE_TURKEY;
	}

	@Override
	public String getTitle() {
		return Token.TURKEY;
	}

	@Override
	public ImageIcon getIcon() {
		return IconManager.getInstance().getIcon("flags/turkey.gif");
	}

	@Override
	public boolean isVisible() {
		return Configuration.getInstance().isVisibleTab( this.getId() );
	}

}
