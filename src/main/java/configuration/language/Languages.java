package configuration.language;

import java.io.Serializable;

public enum Languages implements Serializable {

	BRAZIL,	USA, SPAIN,	FRANCE, ITALY, GERMANY, TURKEY, SLOVAKIA, CHINA;
	
	public boolean isDefaultTab() {
		return this.ordinal() < 2;
	}

	public String translationName() {
		switch ( this ) {
		case BRAZIL:
			return "Portuguese (Brazil)";
		case USA:
			return "English (USA)";
		case SPAIN:
			return "Spanish";
		case FRANCE:
			return "French";
		case ITALY:
			return "Italian";
		case GERMANY:
			return "German";
		case TURKEY:
			return "Turkish";
		case SLOVAKIA:
			return "Slovak";
		case CHINA:
			return "Chinese";
		default:
			return "undefined";
		}
	}
	
}
