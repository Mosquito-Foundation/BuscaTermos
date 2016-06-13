package gui;

import javax.swing.table.DefaultTableCellRenderer;

public class GridColumn {

	private String name;

	private DefaultTableCellRenderer align;

	private int width;

	private boolean resizable;

	private boolean visible;

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
	
	private GridColumn( final GridColumn.Builder builder ) {
		this.name = builder.name;
		this.width = builder.width;
		this.align = builder.align;
		this.resizable = builder.resizable;
		this.visible = builder.visible;
	}

	public String getName() {
		return this.name;
	}

	public int getWidth() {
		return this.width;
	}

	public DefaultTableCellRenderer getAlign() {
		return this.align;
	}

	public boolean isResizable() {
		return this.resizable;
	}

	public boolean isVisible() {
		return this.visible;
	}

}
