package version;

import java.util.Collections;

import com.github.zafarkhaja.semver.Version;

import configuration.Configuration;
import pojo.ReleaseManager;

public class VersionControl {

	private static VersionControl INSTANCE;
	
	private Version currentVersion = null;

	private ReleaseManager releaseManager = ReleaseManager.getInstance();
	
	private VersionControl() {
		
	}

	public static VersionControl getInstance() {
		if ( INSTANCE == null ) {
			INSTANCE = new VersionControl();
		}
		return INSTANCE;
	}
	
	public Version getCurrentVersion() {
		if ( this.currentVersion == null ) {
			this.currentVersion = Version.valueOf( Collections.max( this.releaseManager.getReleases() ).getVersion() );
		}
		return this.currentVersion;
	}

	public boolean isNewerVersion() {
		return Version.valueOf( Configuration.getInstance().getVersion() ).compareTo( this.getCurrentVersion() ) < 0;
	}
	
}
