package run;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import configuration.Configuration;
import gui.MainFrame;

public class Run {

	public static void main(String[] args) {
		Configuration.getInstance();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		Configuration.getInstance().loadTheme();

		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				MainFrame.getInstance().setVisible( true );
			}
		});	
	}
}
