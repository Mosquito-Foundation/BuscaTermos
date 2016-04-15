package run;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.zafarkhaja.semver.Version;

import conf.Configuration;
import gui.MainFrame;

public class Run {

	public static void main(String[] args) {
		
		Configuration.getInstance();
		
		Version v = Version.valueOf( "2.2.0" );
		
		JOptionPane.showMessageDialog(null, v.toString());
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		} );
	}
}
