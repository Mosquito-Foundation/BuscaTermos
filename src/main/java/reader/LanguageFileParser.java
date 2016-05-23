package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import conf.Configuration;
import pojo.Term;

/**
 * Classe para leitura dos arquivos de termos
 * @author giovane.oliveira
 */
public class LanguageFileParser {

	/**
	 * Nome do arquivo que vai ser aberto
	 */
	private final String fileName;
	
	/**
	 * Configurações do aplicativo
	 */
	private final Configuration configuration;
	
	/**
	 * Lista de termos contidos no arquivo
	 */
	private final List<Object> terms;

	/**
	 * Construtor
	 * @param fileName Nome do arquivo que vai ser lido
	 * @param configuration Configurações atuais do aplicativo
	 */
	public LanguageFileParser(String fileName, Configuration configuration) {
		this.fileName = fileName;
		this.configuration = configuration;
		this.terms = new ArrayList<>();
	}

	/**
	 * Monta e retorna lista de termos do arquivo para uma grid
	 * @return terms
	 */
	public List<Object> getTermsList() {
		
		File file = new File(this.configuration.getPath().replace("\\", "/") + "sesuite." + fileName + ".utf-8.inc");
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(fileReader);
			String data = null;

			do {
				data = buffer.readLine();

				if (data != null && "$".equals(Character.toString(data.charAt(0)))) {
					Term term = new Term();
					term.setId(data.substring(5, 11));
					term.setText(data.substring(14, data.length() - 2));
					this.terms.add(term);
				}

			} while (data != null);
			
			fileReader.close();
			
			return this.terms;

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro no processamento das linhas.");
			e.printStackTrace();
		}
		
		return null;
	}
}
