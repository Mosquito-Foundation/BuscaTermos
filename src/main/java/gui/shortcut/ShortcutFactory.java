package gui.shortcut;

import java.awt.event.KeyEvent;

public class ShortcutFactory {

	public static Shortcut createFilterFocusShortcut() {
		return new Shortcut.Builder( KeyEvent.VK_F, "fireFilterFocus" ).modifier( KeyEvent.CTRL_MASK ).build();
	}
	
}
