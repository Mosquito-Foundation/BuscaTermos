package model;

import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * Classe para modelo da grid
 * @author giovane.oliveira
 */
public class GridTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Dados da grid
	 */
	private List<Object> data;

	/**
	 * Colunas
	 */
	private List<GridColumn> columns;

	/**
	 * Construtor
	 * @param  data Dados da grid
	 */
	public GridTableModel( List<Object> data, List<GridColumn> columns ) {
		this.data = data;
		this.columns = columns;
	}

	/**
	 * Retorna dados da linha
	 * @param  row Index da linha
	 * @return Dados da linha
	 */
	public Object getRow(int row) {
		try {
			return this.data.get(row);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * Retorna nome da coluna
	 * @param  col Index da coluna
	 * @return Nome da coluna
	 */
	@Override
	public String getColumnName(int col) {
		return this.columns.get( col ).getName();
	}

	/**
	 * Retorna quantidade de colunas
	 * @return Quantidade de colunas
	 */
	@Override
	public int getColumnCount() {
		return this.columns.size();
	}

	/**
	 * Retorna quantidade de linhas
	 * @return Quantidade de linhas
	 */
	@Override
	public int getRowCount() {
		try {
			return this.data.size();
		} catch (NullPointerException e) {
			return 0;
		}
	}

	/**
	 * Retorna valor de uma célula
	 * @param  row Index da linha
	 * @param  col Index da coluna
	 * @return Dados da célula
	 */
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

	/**
	 * Retorna se a célula é editável
	 * @param  row Index da linha
	 * @param  col Index da coluna
	 * @return Célula é editável ou não
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	/**
	 * Remove linha da grid
	 * @param row Index da linha
	 */
	public void removeItem(int row) {
		this.data.remove(row);
	}
}
