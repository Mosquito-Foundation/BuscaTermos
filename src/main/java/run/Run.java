package run;

import javax.swing.SwingUtilities;

import configuration.Configuration;
import gui.MainFrame;

public class Run {

	public static void main(String[] args) {
		
		Configuration.getInstance();
		
//		JFrame.setDefaultLookAndFeelDecorated( true );
//		JDialog.setDefaultLookAndFeelDecorated( true );
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
//				MainFrame.getInstance().setVisible( true );
//				SubstanceLookAndFeel.setSkin( new BusinessSkin() );
//				SubstanceLookAndFeel.setFontPolicy( FontPolicies.getLogicalFontsPolicy() );
				MainFrame.getInstance().setVisible( true );
			}
		} );
	}
}
