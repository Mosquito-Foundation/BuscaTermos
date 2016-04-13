package model;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe para modelo de colunas da grid
 * @author giovane.oliveira
 */
public class GridColumn {

	/**
	 * Nome/Label da coluna
	 */
	private String name;

	/**
	 * Alinhamento da coluna
	 */
	private DefaultTableCellRenderer align;

	/**
	 * Largura da coluna
	 */
	private int width;

	/**
	 * Indica se a coluna pode ser redimensionada
	 */
	private boolean resizable;

	/**
	 * Indica se a coluna é invisível
	 */
	private boolean visible;

	/**
	 * Classe para criação de uma coluna 
	 * @author giovane.oliveira
	 */
	public static class Builder {
		
		private String name = "";
		private int width = 100;
		private DefaultTableCellRenderer align = new DefaultTableCellRenderer();
		private boolean resizable = true;
		private boolean visible = true;
		
		public Builder() {
			this.align.setHorizontalAlignment( DefaultTableCellRenderer.LEFT );
		}
		
		public Builder name( final String name ) {
			this.name = name;
			return this;
		}
		
		public Builder width( final int width ) {
			this.width = width;
			return this;
		}

		public Builder align( final String hAlign ) {
			switch( hAlign ) {
			case "left":
				this.align.setHorizontalAlignment( DefaultTableCellRenderer.LEFT );
				break;
			case "center":
				this.align.setHorizontalAlignment( DefaultTableCellRenderer.CENTER );
				break;
			case "right":
				this.align.setHorizontalAlignment( DefaultTableCellRenderer.RIGHT );
			}
			return this;
		}
		
		public Builder resizable( final boolean resizable ) {
			this.resizable = resizable;
			return this;
		}
		
		public Builder visible( final boolean visible ) {
			this.visible = visible;
			return this;
		}
		
		public GridColumn build() {
			return new GridColumn( this );
		}
	}
	
	/**
	 * Construtor - Utiliza builder para montagem da coluna
	 * @param builder
	 */
	private GridColumn( final GridColumn.Builder builder ) {
		this.name = builder.name;
		this.width = builder.width;
		this.align = builder.align;
		this.resizable = builder.resizable;
		this.visible = builder.visible;
	}

	/**
	 * Retorna nome
	 * @return Nome/Label da coluna
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retorna largura
	 * @return Largura da coluna
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Retorna alinhamento
	 * @return Alinhamento da coluna
	 */
	public DefaultTableCellRenderer getAlign() {
		return this.align;
	}

	/**
	 * Retorna se é redimensionável
	 * @return Coluna é redimensionável ou não
	 */
	public boolean isResizable() {
		return this.resizable;
	}

	/**
	 * Retorna se é invisível
	 * @return Coluna é invisível ou não
	 */
	public boolean isVisible() {
		return this.visible;
	}

}
