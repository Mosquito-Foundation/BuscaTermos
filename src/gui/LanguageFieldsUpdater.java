package gui;

import java.util.List;

import gui.components.BTTextField;
import pojo.Term;

public class LanguageFieldsUpdater implements Runnable {

	private List<SearchPanel> panes;
	
	private List<BTTextField> fields;
	
	private String token;
	
	public LanguageFieldsUpdater( final List<SearchPanel> panes, final List<BTTextField> fields, final String token ) {
		this.panes = panes;
		this.fields = fields;
		this.token = token;
	}
	
	@Override
	public void run() {
		for ( int index = 0; index < this.panes.size(); index++ ) {
			new Thread( new FieldUpdater( this.panes.get( index ), this.fields.get( index ), this.token ) ).start();
		}
	}

}

class FieldUpdater implements Runnable {

	private SearchPanel pane;
	
	private BTTextField field;
	
	private String token;
	
	public FieldUpdater( final SearchPanel pane, final BTTextField field, final String token ) {
		this.pane = pane;
		this.field = field;
		this.token = token;
	}
	
	@Override
	public void run() {
		for ( Object term : this.pane.getGridData() ) {
			if( ((Term) term).getId().equals( this.token ) ) {
				this.field.setText( ((Term) term).getText() );
				break;
			}
		}
	}
	
}