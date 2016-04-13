package gui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import language.Language;

/**
 * Classe para controle das abas
 * @author giovane.oliveira
 */
class TabController extends MouseAdapter {

	/**
	 * Instância da janela
	 */
	private final MainFrame mainFrame;

	/**
	 * Abas
	 */
	private final JTabbedPane tabbedPane;

	/**
	 * Construtor
	 * @param  tabbedPane Abas da janela
	 * @param  mainFrame  Janela
	 */
	public TabController(JTabbedPane tabbedPane, MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		this.tabbedPane = tabbedPane;
	}

	/**
	 * Ação disparada ao pressionar botões do mouse em cima das abas
	 * @param e Evento do mouse
	 */
	public void mousePressed(MouseEvent e) {
		final int index = tabbedPane.getUI().tabForCoordinate(tabbedPane, e.getX(), e.getY());

		if (index != -1) {

			// Click esquerdo
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (tabbedPane.getSelectedIndex() != index) {
					tabbedPane.setSelectedIndex(index);
				} else if (tabbedPane.isRequestFocusEnabled()) {
					tabbedPane.requestFocusInWindow();
				}

			// Click wheel
			} else if (SwingUtilities.isMiddleMouseButton(e)) {
				if(index > 0) {
					removeTab(index);
				}

			// Click direito
			} else if (SwingUtilities.isRightMouseButton(e)) {
				final TabContextMenu contextMenu = new TabContextMenu(this.mainFrame, index);
				
				final Rectangle tabBounds = tabbedPane.getBoundsAt(index);
				contextMenu.show(tabbedPane, tabBounds.x, tabBounds.y + tabBounds.height);
			}
		}
	}

	/**
	 * Remove uma aba
	 * @param index Index da aba
	 */
	private void removeTab(int index) {
		switch (tabbedPane.getTitleAt(index)) {
		case Language.USA:
			mainFrame.getViewUSAItem().setSelected(false);
			break;
		case Language.SPAIN:
			mainFrame.getViewSpainItem().setSelected(false);
			break;
		case Language.FRANCE:
			mainFrame.getViewFranceItem().setSelected(false);
			break;
		case Language.ITALY:
			mainFrame.getViewItalyItem().setSelected(false);
			break;
		case Language.GERMANY:
			mainFrame.getViewGermanyItem().setSelected(false);
			break;
		}

		tabbedPane.removeTabAt(index);
	}
}