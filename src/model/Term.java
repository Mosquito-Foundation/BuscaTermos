package model;

/**
 * Classe que representa um termo
 * @author giovane.oliveira
 */
public class Term {

	/**
	 * Número do termo
	 */
	private String id;
	
	/**
	 * Texto do termo
	 * 
	 */
	private String text;

	/**
	 * Retorna número do termo
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Seta número do termo
	 * @param id
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * Retorna texto do termo
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Seta texto do termo
	 * @param text
	 */
	public void setText(final String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "[" + this.id + "] = \"" + this.text + "\"";
	}

}
