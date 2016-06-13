package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import configuration.Configuration;
import pojo.Term;

public class LanguageFileParser {

	public static List<Object> getTerms(final String fileName) {
		final File file = new File(Configuration.getInstance().getPath().replace("\\", "/") + "sesuite." + fileName + ".utf-8.inc");
		final List<Object> terms = new ArrayList<>();
		
		try {
			final FileReader fr = new FileReader(file);
			final BufferedReader br = new BufferedReader(fr);
			
			String data = null;
			
			do {
				data = br.readLine();
				if(data != null && data.charAt(0) == '$') {
					Term term = new Term();
					term.setId(data.substring(5, 11));
					term.setText(data.substring(14, data.length() - 2));
					terms.add(term);
				}
			} while(data != null);
			
			fr.close();
			br.close();
			
			return terms;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return terms;
		
	}
	
}
