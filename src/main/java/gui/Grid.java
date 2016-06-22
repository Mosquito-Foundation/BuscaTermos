package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import configuration.language.Languages;
import control.ComparisonPaneUpdater;
import gui.components.BTTable;
import gui.components.BTTableListener;

public class Grid extends JScrollPane implements BTTableListener {

	private static final long serialVersionUID = 1L;
	
	private final Languages language;
	
	private final BTTable table;
	
	private final List<GridColumn> columns;
	
	private GridTableModel model;
	
	private TableRowSorter<TableModel> sorter;
	
	private List<Object> data;
	
	public Grid( final Languages language ) {
		super();
		
		this.language = language;
		this.table = new BTTable();
		this.columns = new ArrayList<>();
	}

	public void setData(final List<Object> data) {
		this.data = data;
	}
	
	public void addColumn(final GridColumn column) {
		this.columns.add(column);
	}
	
	public Grid output() {
		this.getTable().setModel(this.getModel());
		this.getTable().setRowSorter(this.getSorter());
		
		this.parseColumns();
		
		this.setViewportView(this.getTable());
		this.setBorder( BorderFactory.createLineBorder(Color.decode("#79838E")) );
		
		return this;
	}
	
	private void parseColumns() {
		GridColumn column;
		for(int i = 0; i < this.getColumns().size(); i++) {
			column = this.getColumns().get(i);
			
			this.getTable().getColumnModel().getColumn(i).setCellRenderer(column.getAlign());
			this.getTable().getColumnModel().getColumn(i).setPreferredWidth(column.getWidth());
			this.getTable().getColumnModel().getColumn(i).setResizable(column.isResizable());

			if(!column.isResizable()) {
				this.getTable().getColumnModel().getColumn(i).setMinWidth(column.getWidth());
				this.getTable().getColumnModel().getColumn(i).setMaxWidth(column.getWidth());
			}
			
			if (!column.isVisible()) {
				this.getTable().getColumnModel().getColumn(i).setMinWidth(0);
				this.getTable().getColumnModel().getColumn(i).setMaxWidth(0);
			}
		}
	}
	
	public void find(final String text) {
		try {
			if (text.length() > 0 && !text.equals( "\"\"" )) {
				final boolean startMatch = text.charAt( 0 ) == '\"';
				final boolean endMatch = text.charAt( text.length() - 1 ) == '\"';

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
				escaped = escaped.replace( ")", "\\)" );
				escaped = escaped.replace( "|", "\\|" );
				
				// Qualquer letra
				escaped = escaped.replace("?", ".");
				escaped = escaped.replace("#question#", "\\?");
						
				StringBuilder sb = new StringBuilder( escaped );
				
				if ( startMatch ) {
					sb.setCharAt(0, '^');
				}
				
				if ( endMatch ) {
					sb.setCharAt( sb.length() - 1, '$' );
				}
				
				escaped = sb.toString();
				
				this.getSorter().setRowFilter( RowFilter.regexFilter( "(?i)" + escaped ));
			} else {
				this.getSorter().setRowFilter(null);
			}
		} catch (PatternSyntaxException e) {
			this.getSorter().setRowFilter(null);
		}
	}
	
	@Override
	public void fireTableSelection(final String token) {
		new Thread( new ComparisonPaneUpdater( MainFrame.getInstance().getLanguagePanelMap().get( this.getLanguage() ).getComparisonFields(), token ) ).start();
	}

	private BTTable getTable() {
		return this.table;
	}
	
	private GridTableModel getModel() {
		if(this.model == null) {
			this.model = new GridTableModel(this.getData(), this.getColumns());
		}
		return this.model;
	}
	
	private TableRowSorter<TableModel> getSorter() {
		if(this.sorter == null) {
			this.sorter = new TableRowSorter<TableModel>(this.getModel());
			this.sorter.toggleSortOrder(0);
		}
		return this.sorter;
	}
	
	public List<Object> getData() {
		return this.data;
	}
	
	private List<GridColumn> getColumns() {
		return this.columns;
	}
	
	public Languages getLanguage() {
		return this.language;
	}
	
}
