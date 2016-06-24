package conf;

import java.io.Serializable;

@Deprecated
public class Configuration implements Serializable {

	private static final long serialVersionUID = 1L;

	private String path;
	
	public Configuration() {
		
	}
	
	public String getPath() {
		return this.path;
	}
	
}
