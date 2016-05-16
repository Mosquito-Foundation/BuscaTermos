package gui.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Grid;
import gui.GridContextMenu;

/**
 * Classe para montagem da tabela utilizada na grid
 * @author giovane.oliveira
 */
public class BTTable extends JTable {

	private static final long serialVersionUID = 1L;

	/**
	 * Instância da grid que a tabela pertence
	 */
	private Grid gridInstance;
	
	public BTTable( final Grid gridInstance ) {
		super();
		
		this.gridInstance = gridInstance;
		
		this.setCellSelectionEnabled( true );
		
		ListSelectionModel cellSelectionModel = this.getSelectionModel();
	    cellSelectionModel.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

	    cellSelectionModel.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if ( !e.getValueIsAdjusting() ) {
					final int selectedRow = getInstance().getSelectedRow();
					String token = "";
					
					if( selectedRow >= 0 ) {
						token = (String) getInstance().getValueAt( selectedRow, 0 );
					}
					
					getGridInstance().fireSelectionTrigger( token );
				}
			}
		} );
		
	    this.setRowHeight( 20 );
	    
//		this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        int r = getInstance().rowAtPoint(e.getPoint());
		        int c = getInstance().columnAtPoint(e.getPoint());
		        
		        if (r >= 0 && r < getInstance().getRowCount()) {
		        	getInstance().setRowSelectionInterval(r, r);
		        	getInstance().setColumnSelectionInterval(c, c);
		        } else {
		        	getInstance().clearSelection();
		        }

		        final int rowindex = getInstance().getSelectedRow();
		        
		        if (rowindex < 0) {
		            return;
		        }
		        
		        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
		            JPopupMenu popup = new GridContextMenu( getGridInstance() );
		            popup.show(e.getComponent(), e.getX(), e.getY());
		        }
		    }
		});
	}
	
	/**
	 * Retorna própria inst�ncia
	 * @return
	 */
	public BTTable getInstance() {
		return this;
	}
	
	/**
	 * Retorna instância da grid que a tabela pertence
	 * @return
	 */
	public Grid getGridInstance() {
		return this.gridInstance;
	}
}
