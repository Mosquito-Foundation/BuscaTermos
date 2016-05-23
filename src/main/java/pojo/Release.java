package pojo;

import java.util.List;

import com.github.zafarkhaja.semver.Version;

public class Release implements Comparable<Release> {

	private String version;
	
	private String description;
	
	private Changelog changelog; 

	public Release() {
		this.changelog = new Changelog();
	}

	public void addFeature( final String feature ) {
		this.changelog.addFeature( feature );
	}
	
	public void addFix( final String fix ) {
		this.changelog.addFix( fix );
	}
	
	public void setVersion( final String version) {
		this.version = version;
	}

	public String getVersion() {
		return this.version;
	}

	public void setDescription( final String description ) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<String> getFeatures() {
		return this.changelog.getFeatures();
	}
	
	public List<String> getFixes() {
		return this.changelog.getFixes();
	}
	
	@Override
	public int compareTo( Release release ) {
		return Version.valueOf( this.getVersion() ).compareTo( Version.valueOf( release.getVersion() ) );
	}

}
