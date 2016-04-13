package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.SearchPanel;
import gui.components.BTTable;


/**
 * Classe para montagem da grid
 * @author giovane.oliveira
 */
public class Grid extends JScrollPane {

	private static final long serialVersionUID = 1L;

	/**
	 * Painel do idioma
	 */
	private final SearchPanel searchPanel;

	/**
	 * Tabela da grid
	 */
	private BTTable table;

	/**
	 * Configurações das colunas
	 */
	private List<GridColumn> gridColumns;

	/**
	 * Dados da grid
	 */
	private List<Object> data;

	/**
	 * Modelo da tabela da grid
	 */
	private GridTableModel model;

	/**
	 * Ordenação dos registros
	 */
	private TableRowSorter<TableModel> sorter;

	/**
	 * Ordenação padrão
	 */
	private int defaultColumnSort;

	/**
	 * Constutor
	 * @param  searchPanel Painel que contém a grid
	 */
	public Grid(SearchPanel searchPanel) {
		this.searchPanel = searchPanel;

		this.table = new BTTable( this );
		this.gridColumns = new ArrayList<>();
		this.defaultColumnSort = 0;
	}

	/**
	 * Seta dados da grid
	 * @param data Dados provenientes da leitura dos arquivos de idiomas
	 */
	public void setData(List<Object> data) {
		this.data = data;
	}
	
	/**
	 * Adiciona uma nova coluna à grid
	 * @param column
	 */
	public void addColumn( final GridColumn column ) {
		this.gridColumns.add( column );
	}
	
	/**
	 * Seta coluna para ordenação padrão
	 * @param col Index da coluna
	 */
	public void setDefaultColumnSort(int col) {
		this.defaultColumnSort = col;
	}
	
	/**
	 * Monta e retorna listagem
	 * @return JScrollPane com a BTTable dentro
	 */
	public Grid output() {
		this.model = new GridTableModel( this.data, this.gridColumns );
		this.sorter = new TableRowSorter<TableModel>( this.model );
		this.table.setModel( this.model );
		this.table.setRowSorter( this.sorter );
		this.sorter.toggleSortOrder( this.defaultColumnSort );

		String[] columnsNames = new String[ this.gridColumns.size() ];
		GridColumn column;

		for (int i = 0; i < this.gridColumns.size(); i++) {
			column = this.gridColumns.get( i );
			columnsNames[i] = column.getName();
			
			this.table.getColumnModel().getColumn(i).setCellRenderer( column.getAlign() );
			this.table.getColumnModel().getColumn(i).setPreferredWidth( column.getWidth() );
			this.table.getColumnModel().getColumn(i).setResizable( column.isResizable() );

			if( !column.isResizable() ) {
				this.table.getColumnModel().getColumn( i ).setMinWidth( column.getWidth() );
				this.table.getColumnModel().getColumn( i ).setMaxWidth( column.getWidth() );
			}
			
			if ( !column.isVisible() ) {
				this.table.getColumnModel().getColumn( i ).setMinWidth( 0 );
				this.table.getColumnModel().getColumn( i ).setMaxWidth( 0 );
			}
		}

		this.setViewportView( this.table );
		this.setBorder( BorderFactory.createLineBorder(Color.decode("#79838E")) );

		return this;
	}
	
	/**
	 * Filtra grid por texto
	 * @param text
	 */
	public void find( final String text ) {
		try {
			if (text.length() > 0 && !text.equals( "\"\"" )) {
				final boolean perfectMatch = text.charAt(0) == '\"' && text.charAt(text.length()-1) == '\"';
				
				String escaped = text;

				// Qualquer letra
				escaped = escaped.replace("\\?", "#question#");
				
				// Escape dos caracteres especiais
				escaped = escaped.replace("\\", "\\\\");
				escaped = escaped.replace( "[", "\\[" );
				escaped = escaped.replace( ".", "\\." );
				escaped = escaped.replace( "$", "\\$" );
				escaped = escaped.replace( "^", "\\^" );
				escaped = escaped.replace( "+", "\\+" );
				escaped = escaped.replace( "*", "\\*" );
				escaped = escaped.replace( "{", "\\{" );
				escaped = escaped.replace( "(", "\\(" );
				escaped = escaped.replace( "|", "\\|" );
				
				// Qualquer letra
				escaped = escaped.replace("?", ".");
				escaped = escaped.replace("#question#", "\\?");
						
				if ( perfectMatch ) {
					StringBuilder sb = new StringBuilder(escaped);
					sb.setCharAt(0, '^');
					sb.setCharAt(sb.length()-1, '$');
					escaped = sb.toString();
				}
				
				this.getSorter().setRowFilter( RowFilter.regexFilter( "(?i)" + escaped ));
			} else {
				this.getSorter().setRowFilter(null);
			}
		} catch (PatternSyntaxException e) {
			this.getSorter().setRowFilter(null);
		}
	}
	
	/**
	 * Callback de seleção de célula
	 * @param token Código do termo selecionado
	 */
	public void fireSelectionTrigger( final String token ) {
		this.getSearchPanel().updateLanguageFields( token );
	}
	
	/**
	 * Retorna colunas da grid
	 * @return colunas da grid
	 */
	public List<GridColumn> getGridColumns() {
		return this.gridColumns;
	}
	
	/**
	 * Retorna linha selecionada
	 * @return Index da linha
	 */
	public int getSelectedRow() {
		try {
			return this.table.convertRowIndexToModel( this.table.getSelectedRow() );
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * Retorna grid
	 * @return BTTable da grid
	 */
	public BTTable getTable() {
		return this.table;
	}

	/**
	 * Retorna modelo da grid
	 * @return Modelo da grid
	 */
	public GridTableModel getTableModel() {
		return this.model;
	}

	/**
	 * Retorna ordenador da grid
	 * @return Ordenador da grid
	 */
	public TableRowSorter<TableModel> getSorter() {
		return this.sorter;
	}
	
	/**
	 * Retorna painel do idioma
	 * @return Painel do idioma
	 */
	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}
	
	/**
	 * Retorna própria instância
	 * @return Grid
	 */
	public Grid getInstance() {
		return this;
	}
}
