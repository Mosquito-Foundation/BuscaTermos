package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import pojo.ReleaseManager;

public class ReleaseParser {

	private static BufferedReader br = null;
	
	public static ReleaseManager getReleasesFromJson() {
		try {
			Gson gson = new Gson();
			InputStream in = ReleaseManager.class.getClassLoader().getResourceAsStream( "json/releases-min.json" );
			br = new BufferedReader( new InputStreamReader( in ) );
			return gson.fromJson( br.readLine(), ReleaseManager.class );
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
