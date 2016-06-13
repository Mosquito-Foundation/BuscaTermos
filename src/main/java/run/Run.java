package run;

import javax.swing.SwingUtilities;

import configuration.Configuration;
import gui.MainFrame;

public class Run {

	public static void main(String[] args) {
		
		Configuration.getInstance();
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				MainFrame.getInstance().setVisible( true );
			}
		} );
	}
}
