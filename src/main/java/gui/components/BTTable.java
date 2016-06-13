package gui.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.context.menu.GridContextMenu;

public class BTTable extends JTable {

	private static final long serialVersionUID = 1L;

	public BTTable() {
		super();
		
		this.setCellSelectionEnabled( true );
		
		ListSelectionModel cellSelectionModel = this.getSelectionModel();
	    cellSelectionModel.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

	    cellSelectionModel.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if ( !e.getValueIsAdjusting() ) {
					triggerTableSelection();
				}
			}
		} );
		
	    this.setRowHeight( 20 );
	    
//		this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        triggerMouseClick(e);
		    }
		});
	}
	
	private void triggerTableSelection() {
		final int rowindex = this.getSelectedRow();
		String token = "";

		if( rowindex >= 0 ) {
			token = (String) this.getValueAt( rowindex, 0 );
		}

		if( this.getParent().getParent() instanceof BTTableListener ) {
			( (BTTableListener) this.getParent().getParent() ).fireTableSelection( token );
		}
	}

	private void triggerMouseClick(final MouseEvent e) {
		final int r = this.rowAtPoint(e.getPoint());
        final int c = this.columnAtPoint(e.getPoint());
        
        if (r >= 0 && r < this.getRowCount()) {
        	this.setRowSelectionInterval(r, r);
        	this.setColumnSelectionInterval(c, c);
        } else {
        	this.clearSelection();
        }

        final int rowindex = this.getSelectedRow();
        
        if (rowindex < 0) {
            return;
        }
        
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            JPopupMenu popup = new GridContextMenu( this );
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
	}
	
}
