package gui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import pojo.Term;

public class GridTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	private List<Object> data;

	private List<GridColumn> columns;

	public GridTableModel( List<Object> data, List<GridColumn> columns ) {
		this.data = data;
		this.columns = columns;
	}

	public Object getRow(int row) {
		try {
			return this.data.get(row);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int col) {
		return this.columns.get( col ).getName();
	}

	@Override
	public int getColumnCount() {
		return this.columns.size();
	}

	@Override
	public int getRowCount() {
		try {
			return this.data.size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	@Override
	public Object getValueAt(int row, int col) {

		Term term = (Term) this.data.get(row);
		switch (col) {
		case 0:
			return term.getId();
		case 1:
			return term.getText();
		}

		return "opa";
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
}
