package pojo;

import java.util.Collections;
import java.util.List;

import reader.ReleaseParser;

public class ReleaseManager {

	private static ReleaseManager INSTANCE = null;
	
	private List<Release> releases = null;
	
	private ReleaseManager() {

	}

	public static ReleaseManager getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = ReleaseParser.getReleasesFromJson();
		}
		return INSTANCE;
	}
	
	public List<Release> getReleases() {
		return this.releases;
	}

	public List<Release> getSortedReleases() {
		Collections.sort( this.getReleases(), Collections.reverseOrder() );
		return this.getReleases();
	}
}
