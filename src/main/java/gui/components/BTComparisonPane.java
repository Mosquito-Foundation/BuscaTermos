package gui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import configuration.Configuration;
import configuration.language.Language;

public class BTComparisonPane extends BTPanel {

	private static final long serialVersionUID = 1L;
	
	private List<BTLabelTextField> fields;
	
	public BTComparisonPane() {
		super(new BorderLayout());
		
		this.createComponents();
	}
	
	private void createComponents() {
		final BTPanel container = new BTPanel( new GridLayout(3, 3, 10, 5), BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );
		for (BTLabelTextField field: this.getFields()) {
			container.add(field);
		}
		
		this.add(container);
	}

	public List<BTLabelTextField> getFields() {
		if(this.fields == null) {
			this.fields = new ArrayList<>();
			for (Language language : Configuration.getInstance().getLanguages().values()) {
				this.fields.add( new BTLabelTextField( language.getTitle(), new BTTextField(false) ) );
			}
		}
		return this.fields;
	}
	
}
