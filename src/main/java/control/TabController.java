package control;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import gui.MainFrame;
import gui.components.BTTabbedPane;
import gui.context.menu.TabContextMenu;

public class TabController extends MouseAdapter {

	private final BTTabbedPane tabbedPane;

	public TabController( final BTTabbedPane tabbedPane ) {
		super();

		this.tabbedPane = tabbedPane;
	}

	public void mousePressed(MouseEvent e) {
		final int index = this.tabbedPane.getUI().tabForCoordinate( this.tabbedPane, e.getX(), e.getY() );

		if ( index != -1 ) {

			// Click esquerdo
			if ( SwingUtilities.isLeftMouseButton(e) ) {
				if ( this.tabbedPane.getSelectedIndex() != index ) {
					this.tabbedPane.setSelectedIndex( index );
				} else if ( this.tabbedPane.isRequestFocusEnabled() ) {
					this.tabbedPane.requestFocusInWindow();
				}

			// Click wheel
			} else if ( SwingUtilities.isMiddleMouseButton(e) ) {
				if( index > 0 ) {
					this.removeTab( index );
				}

			// Click direito
			} else if ( SwingUtilities.isRightMouseButton(e) ) {
				final TabContextMenu contextMenu = new TabContextMenu( this.tabbedPane, index );
				
				final Rectangle tabBounds = this.tabbedPane.getBoundsAt( index );
				contextMenu.show(this.tabbedPane, tabBounds.x, tabBounds.y + tabBounds.height);
			}

		}
	}

	private void removeTab( final int index ) {
		MainFrame.getInstance().removeTabByIndex( index );
	}
}