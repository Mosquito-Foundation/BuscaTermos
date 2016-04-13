package gui;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JTextField;

/**
 * Classe para controle do menu de contexto do campo de pesquisa dos painéis de idioma 
 * @author giovane.oliveira
 */
public class FieldContextMenu extends ContextMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * Painel ao qual o campo pertence
	 */
	private final SearchPanel searchPanel;
	
	/**
	 * Campo de pesquisa
	 */
	private final JTextField field;
	
	/**
	 * Construtor - Inicializa atributos e constrói itens do menu de contexto
	 * @param searchPanel Painel do idioma
	 */
	public FieldContextMenu(SearchPanel searchPanel) {
		super(searchPanel.getMainFrame());
		this.searchPanel = searchPanel;
		this.field = searchPanel.getSearchField();
		this.createOptions();
	}

	/**
	 * Cria opções do menu
	 */
	private void createOptions() {
		this.add(this.getCopyOption());
		this.add(this.getPasteOption());
	}

	/**
	 * Ação executada no item de menu Copiar
	 */
	protected void copyAction() {
		Action copy = this.field.getActionMap().get("copy");
		ActionEvent ae = new ActionEvent(this.field, ActionEvent.ACTION_PERFORMED, "");
		copy.actionPerformed(ae);
	}
	
	/**
	 * Ação executada no item de menu Colar
	 */
	protected void pasteAction() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		if(hasTransferableText) {
			try {
				result = (String) contents.getTransferData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		
		this.field.setText(this.field.getText() + result);
		this.searchPanel.doGridSearch();
	}
}
