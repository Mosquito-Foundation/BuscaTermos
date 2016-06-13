package configuration.language;

import javax.swing.ImageIcon;

import configuration.Configuration;
import utils.IconManager;
import utils.Token;

public class ItalyLanguage implements Language {

	private static final long serialVersionUID = 1L;

	@Override
	public Languages getId() {
		return Languages.ITALY;
	}
	
	@Override
	public String getFileName() {
		return Token.FILE_ITALY;
	}

	@Override
	public String getTitle() {
		return Token.ITALY;
	}

	@Override
	public ImageIcon getIcon() {
		return IconManager.getInstance().getIcon("flags/italy.gif");
	}

	@Override
	public boolean isVisible() {
		return Configuration.getInstance().isVisibleTab( this.getId() );
	}

}
