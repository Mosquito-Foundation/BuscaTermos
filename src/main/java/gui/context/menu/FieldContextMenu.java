package gui.context.menu;

public class FieldContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	public FieldContextMenu() {
		super();

		this.createOptions();
	}

	private void createOptions() {
		this.add( this.getCopyOption() );
		this.add( this.getPasteOption() );
	}

}
