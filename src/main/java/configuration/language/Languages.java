package configuration.language;

import java.io.Serializable;

public enum Languages implements Serializable {

	BRAZIL,	USA, SPAIN,	FRANCE, ITALY, GERMANY, TURKEY, SLOVAKIA, CHINA;
	
	public boolean isDefaultTab() {
		return this.ordinal() < 2;
	}

}
