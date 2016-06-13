package configuration.themes;

import java.io.Serializable;

import utils.Token;

public enum Themes implements Serializable {

	DEFAULT, SYSTEM;

	public static String name( final Themes theme ) {
		switch ( theme ) {
		case SYSTEM:
			return Token.THEME_SYSTEM;
		default:
			return Token.THEME_DEFAULT;
		}
	}
	
}
