package gui.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import configuration.Configuration;
import configuration.language.Language;
import gui.shortcut.ShortcutFactory;

public class BTComparisonPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private List<BTLabelTextField> fields;
	
	public BTComparisonPane() {
		super(new BorderLayout());
		
		this.createComponents();
	}
	
	private void createComponents() {
		final JPanel container = new JPanel( new GridLayout(3, 3, 10, 5) );
		container.setBorder( BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );
		for (BTLabelTextField field: this.getFields()) {
			container.add(field);
		}
		
		this.add(container);
	}

	public List<BTLabelTextField> getFields() {
		if(this.fields == null) {
			this.fields = new ArrayList<>();
			for (Language language : Configuration.getInstance().getLanguages().values()) {
				JTextField textField = new JTextField();
				textField.setEditable(false);
				textField.addKeyListener( ShortcutFactory.createFilterFocusShortcut() );
				this.fields.add( new BTLabelTextField( language.getTitle(), textField ) );
			}
		}
		return this.fields;
	}
	
}
