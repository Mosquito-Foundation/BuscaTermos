package utils;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class UsersLogger implements Runnable {

	private final String logFile = "\\\\des352\\C$\\BuscaTermos\\users.log";
	
	@Override
	public void run() {
		this.saveLog();
	}

	private void saveLog() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Path file = Paths.get( this.logFile );
			Files.write( file, Arrays.asList( format.format( Calendar.getInstance().getTime() ) + " - " + System.getProperty("user.name") ), Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND );
		} catch (Throwable e) {
			
		}
	}
	
}
