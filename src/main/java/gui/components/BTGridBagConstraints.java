package gui.components;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BTGridBagConstraints extends GridBagConstraints {

	private static final long serialVersionUID = 1L;

	public static class Builder {
		
		private int anchor = GridBagConstraints.NORTHWEST ;
		
		private int fill = GridBagConstraints.HORIZONTAL;
		
		private int gridwidth = GridBagConstraints.REMAINDER;
		
		private double weightx = 1d;
		
		private double weighty = 1d;
		
		private Insets insets = new Insets(0, 0, 0, 0);
		
		public Builder anchor( final int anchor ) {
			this.anchor = anchor;
			return this;
		}
		
		public Builder fill( final int fill ) {
			this.fill = fill;
			return this;
		}
		
		public Builder gridwidth( final int gridwidth ) {
			this.gridwidth = gridwidth;
			return this;
		}
		
		public Builder weightx( final double weightx ) {
			this.weightx = weightx;
			return this;
		}
		
		public Builder weighty( final double weighty ) {
			this.weighty = weighty;
			return this;
		}
		
		public Builder insets( final Insets insets ) {
			this.insets = insets;
			return this;
		}
		
		public BTGridBagConstraints build() {
			return new BTGridBagConstraints( this );
		}
	}
		
	
	private BTGridBagConstraints( final BTGridBagConstraints.Builder builder ) {
		super();
		this.anchor = builder.anchor;
		this.fill = builder.fill;
		this.gridwidth = builder.gridwidth;
		this.weightx = builder.weightx;
		this.weighty = builder.weighty;
		this.insets = builder.insets;
	}

}
