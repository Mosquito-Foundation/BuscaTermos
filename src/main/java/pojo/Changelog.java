package pojo;

import java.util.ArrayList;
import java.util.List;

public class Changelog {

	private List<String> features;

	private List<String> fixes;

	public Changelog() {
		this.features = new ArrayList<>();
		this.fixes = new ArrayList<>();
	}

	public void addFix( final String fix ) {
		this.fixes.add( fix );
	}

	public void addFeature( final String feature ) {
		this.features.add( feature );
	}

	public List<String> getFeatures() {
		return this.features;
	}
	
	public List<String> getFixes() {
		return this.fixes;
	}

}
