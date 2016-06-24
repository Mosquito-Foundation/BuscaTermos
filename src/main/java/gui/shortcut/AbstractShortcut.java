package gui.shortcut;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gui.LanguagePanel;
import gui.MainFrame;

abstract class AbstractShortcut implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void fireFilterFocus() {
		((LanguagePanel) MainFrame.getInstance().getLanguageTabs().getSelectedComponent()).getFilterField().requestFocus();
		((LanguagePanel) MainFrame.getInstance().getLanguageTabs().getSelectedComponent()).getFilterField().selectAll();
	}
	
	public void fireDisposeWindow( final Object windowToDispose ) {
		((Window) windowToDispose).dispose();
	}
}
