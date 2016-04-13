package run;

import javax.swing.SwingUtilities;

import conf.Configuration;
import gui.MainFrame;

public class Run {

	public static void main(String[] args) {
		
		Configuration.getInstance();
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		} );
	}
}
