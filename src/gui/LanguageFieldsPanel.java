package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import conf.Configuration;
import gui.components.BTLabelTextField;
import gui.components.BTPanel;
import gui.components.BTTextField;
import language.Language;

public class LanguageFieldsPanel extends BTPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Painel do idioma
	 */
	private SearchPanel searchPanel;
	
	/**
	 * Campos dos idiomas
	 */
	private BTTextField brazilField, usaField, spainField, franceField, italyField, germanyField, turkeyField, slovakiaField, chinaField;
	
	/**
	 * Lista de campos do painel
	 */
	private List<BTTextField> fieldList;
	
	/**
	 * Constutor
	 * @param  searchPanel Painel pai deste
	 */
	public LanguageFieldsPanel( final SearchPanel searchPanel ) {
		super( new BorderLayout() );
		
		this.searchPanel = searchPanel;
		
		this.add( this.getFields(), BorderLayout.CENTER );
	}
	
	/**
	 * Monta e retorna painel com campos dos idiomas
	 * @return Painel com campos dos idiomas
	 */
	private BTPanel getFields() {
		GridLayout containerLayout;
		BTPanel container;
		// Corrigir espaçamento
		if ( Configuration.getInstance().isMacTheme() ) {
			containerLayout = new GridLayout( 3, 3, 0, 0 );
			container = new BTPanel( containerLayout );
		} else {
			containerLayout = new GridLayout( 3, 3, 10, 5 );
			container = new BTPanel( containerLayout, BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );
		}
		
		container.add( new BTLabelTextField( Language.BRAZIL, this.getBrazilField() ) );
		container.add( new BTLabelTextField( Language.USA, this.getUsaField() ) );
		container.add( new BTLabelTextField( Language.SPAIN, this.getSpainField() ) );
		container.add( new BTLabelTextField( Language.FRANCE, this.getFranceField() ) );
		container.add( new BTLabelTextField( Language.ITALY, this.getItalyField() ) );
		container.add( new BTLabelTextField( Language.GERMANY, this.getGermanyField() ) );
		container.add( new BTLabelTextField( Language.TURKEY, this.getTurkeyField() ) );
		container.add( new BTLabelTextField( Language.SLOVAKIA, this.getSlovakiaField() ) );
		container.add( new BTLabelTextField( Language.CHINA, this.getChinaField() ) );
		
		return container;
	}
	
	/**
	 * Retorna painel do idioma
	 * @return Painel do idioma
	 */
	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

	/**
	 * Monta e retorna campo referente ao idioma Português (Brasil)
	 * @return Campo referente ao idioma Português (Brasil)
	 */
	public BTTextField getBrazilField() {
		if( this.brazilField == null ) {
			this.brazilField = new BTTextField( false );
		}
		return this.brazilField;
	}

	/**
	 * Monta e retorna campo referente ao idioma Inglês (EUA)
	 * @return Campo referente ao idioma Inglês (EUA)
	 */
	public BTTextField getUsaField() {
		if( this.usaField == null ) {
			this.usaField = new BTTextField( false );
		}
		return this.usaField;
	}

	/**
	 * Monta e retorna campo referente ao idioma Espanhol
	 * @return Campo referente ao idioma Espanhol
	 */
	public BTTextField getSpainField() {
		if( this.spainField == null ) {
			this.spainField = new BTTextField( false );
		}
		return this.spainField;
	}

	/**
	 * Monta e retorna campo referente ao idioma Francês
	 * @return Campo referente ao idioma Francês
	 */
	public BTTextField getFranceField() {
		if( this.franceField == null ) {
			this.franceField = new BTTextField( false );
		}
		return this.franceField;
	}
	
	/**
	 * Monta e retorna campo referente ao idioma Italiano
	 * @return Campo referente ao idioma Italiano
	 */
	public BTTextField getItalyField() {
		if( this.italyField == null ) {
			this.italyField = new BTTextField( false );
			
		}
		return this.italyField;
	}

	/**
	 * Monta e retorna campo referente ao idioma Alemão
	 * @return Campo referente ao idioma Alemão
	 */
	public BTTextField getGermanyField() {
		if( this.germanyField == null ) {
			this.germanyField = new BTTextField( false );
		}
		return this.germanyField;
	}
	
	/**
	 * Monta e retorna campo referente ao idioma Turco
	 * @return Campo referente ao idioma Turco
	 */
	public BTTextField getTurkeyField() {
		if( this.turkeyField == null ) {
			this.turkeyField = new BTTextField( false );
		}
		return this.turkeyField;
	}
	
	/**
	 * Monta e retorna campo referente ao idioma Eslovaco
	 * @return Campo referente ao idioma Eslovaco
	 */
	public BTTextField getSlovakiaField() {
		if( this.slovakiaField == null ) {
			this.slovakiaField = new BTTextField( false );
		}
		return this.slovakiaField;
	}
	
	/**
	 * Monta e retorna campo referente ao idioma Chinês
	 * @return Campo referente ao idioma Chinês
	 */
	public BTTextField getChinaField() {
		if( this.chinaField == null ) {
			this.chinaField = new BTTextField( false );
		}
		return this.chinaField;
	}

	/**
	 * Monta e retorna lista de campos do painel
	 * @return Lista de campos do painel
	 */
	public List<BTTextField> getFieldList() {
		if( this.fieldList == null ) {
			this.fieldList = new ArrayList<>();
			this.fieldList.add( this.getBrazilField() );
			this.fieldList.add( this.getUsaField() );
			this.fieldList.add( this.getSpainField() );
			this.fieldList.add( this.getFranceField() );
			this.fieldList.add( this.getItalyField() );
			this.fieldList.add( this.getGermanyField() );
			this.fieldList.add( this.getTurkeyField() );
			this.fieldList.add( this.getSlovakiaField() );
			this.fieldList.add( this.getChinaField() );
		}
		return this.fieldList;
	}
}
