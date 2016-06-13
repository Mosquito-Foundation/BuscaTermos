package control;

import java.util.List;

import configuration.language.Languages;
import gui.LanguagePanel;
import gui.MainFrame;
import gui.components.BTLabelTextField;
import pojo.Term;

public class ComparisonPaneUpdater implements Runnable {

	private final List<BTLabelTextField> fields;
	
	private final String token;
	
	public ComparisonPaneUpdater(final List<BTLabelTextField> fields, final String token) {
		this.fields = fields;
		this.token = token;
	}
	
	@Override
	public void run() {
		LanguagePanel panel;
		for (Languages language : Languages.values()) {
			panel = MainFrame.getInstance().getLanguagePanelMap().get(language);
			new Thread( new ComparisonFieldUpdater(panel, this.fields.get(language.ordinal()), this.token) ).start();
		}
	}
	
}

class ComparisonFieldUpdater implements Runnable {

	private final LanguagePanel panel;
	
	private final BTLabelTextField field;
	
	private final String token;
	
	public ComparisonFieldUpdater(final LanguagePanel panel, final BTLabelTextField field, final String token) {
		this.panel = panel;
		this.field = field;
		this.token = token;
	}
	
	@Override
	public void run() {
		for ( Object term : this.panel.getGridData() ) {
			if( ((Term) term).getId().equals( this.token ) ) {
				this.field.getTextField().setText( ((Term) term).getText() );
				return;
			}
		}
		// TODO Evoluir posteriormente... do jeito abaixo, ocorre erro quando o item selecionado não aparece na grid por motivo de filtro
//		Object obj = this.panel.getGridData().stream().filter( o -> ((Term) o).getId().equals(this.token) ).findFirst().get();
//		this.field.getTextField().setText( ( (Term) obj ).getText() );
	}
	
}