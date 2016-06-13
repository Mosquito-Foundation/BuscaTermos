package gui.template;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import gui.components.BTGridBagConstraints;
import utils.Token;

public class ReleaseView extends JPanel {

	private static final long serialVersionUID = 1L;

	public static class Builder {
		
		private ReleaseVersion version;
		
		private ReleaseDescription description = null;
		
		private List<ReleaseChange> features = new ArrayList<>();
		
		private List<ReleaseChange> fixes = new ArrayList<>();
		
		public Builder version( final String version ) {
			this.version = new ReleaseVersion( version );
			return this;
		}
		
		public Builder description( final String description ) {
			if ( description != null ) {
				this.description = new ReleaseDescription( description );
			}
			return this;
		}
		
		public Builder features( final List<String> features ) {
			if ( !features.isEmpty() ) {
				for (String feature : features) {
					this.features.add( new ReleaseChange( feature ) );
				}
			}
			return this;
		}
		
		public Builder fixes( final List<String> fixes ) {
			if ( !fixes.isEmpty() ) {
				for (String fix : fixes) {
					this.fixes.add( new ReleaseChange( fix ) );
				}
			}
			return this;
		}
		
		public ReleaseView build() {
			return new ReleaseView( this );
		}
	}
	
	private List<ReleaseChange> features;
	
	private List<ReleaseChange> fixes;
	
	private ReleaseView( final ReleaseView.Builder builder ) {
		super( new GridBagLayout() );
		super.setBackground( Color.WHITE );

		this.features = builder.features;
		this.fixes = builder.fixes;
		
		this.add( builder.version, new BTGridBagConstraints.Builder().insets( new Insets(10, 0, 0, 0) ).build() );
		
		if ( builder.description != null ) {
			this.add( builder.description, new BTGridBagConstraints.Builder().insets( new Insets(5, 5, 0, 5) ).build() );
		}
		
		this.add( this.getChangeContainer(), new BTGridBagConstraints.Builder().insets( new Insets(0, 5, 0, 5) ).build() );
	}
	
	private JPanel getChangeContainer() {
		JPanel container = new JPanel( new GridBagLayout() );
		container.setBackground( Color.WHITE );
		
		BTGridBagConstraints subtitleLayout = new BTGridBagConstraints.Builder().insets( new Insets(10, 0, 0, 0) ).build();
		BTGridBagConstraints changesContainerLayout = new BTGridBagConstraints.Builder().insets( new Insets(5, 5, 0, 5) ).build();
		
		if(!this.features.isEmpty()) {
			container.add( new ReleaseSubtitle( Token.FEATURES ), subtitleLayout );
			container.add( this.getChanges( this.features ), changesContainerLayout );
		}
		
		if(!this.fixes.isEmpty()) {
			container.add( new ReleaseSubtitle( Token.FIXES ), subtitleLayout );
			container.add( this.getChanges( this.fixes ), changesContainerLayout );
		}
		
		return container;
	}
	
	private JPanel getChanges( final List<ReleaseChange> changes ) {
		JPanel listContainer = new JPanel( new GridBagLayout() );
		BTGridBagConstraints changesLayout = new BTGridBagConstraints.Builder().build();

		for (ReleaseChange change : changes) {
			listContainer.add( change, changesLayout );
		}
		
		return listContainer;
	}
}
