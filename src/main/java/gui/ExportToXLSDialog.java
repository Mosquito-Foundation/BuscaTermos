package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import configuration.Configuration;
import configuration.language.Language;
import configuration.language.Languages;
import export.XLSExporter;
import gui.components.BTButton;
import gui.components.BTLoading;
import gui.shortcut.ShortcutFactory;
import utils.Token;

public class ExportToXLSDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private LinkedHashMap<XLSExporter.Type, LinkedHashMap<Languages, Boolean>> exportOptionsMap;
	
	private BTLoading loading;
	
	public ExportToXLSDialog() {
		super( MainFrame.getInstance(), ModalityType.APPLICATION_MODAL );
		
		this.exportOptionsMap = new LinkedHashMap<>();
		
		this.addKeyListener( ShortcutFactory.createDisposeWindowShortcut( this ) );
		
		this.add( this.createComponents() );
		this.showDialog();
	}

	private JPanel createComponents() {
		final JPanel mainPanel = new JPanel( new BorderLayout() );
		mainPanel.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );
		
		mainPanel.add( this.getFormPanel() );
		mainPanel.add( this.getBottomBar(), BorderLayout.SOUTH );
		
		return mainPanel;
	}
	
	private JPanel getFormPanel() {
		final JPanel container = new JPanel( new GridLayout( 1, 2 ) );
		container.add( this.getOptionsList( Token.CUSTOMIZE_LIST_ALL, XLSExporter.Type.ALL ) );
//		container.add( this.getOptionsList( Token.CUSTOMIZE_LIST_MISSING, XLSExporter.Type.MISSING ) );
		return container;
	}

	private JPanel getOptionsList( final String groupTitle, final XLSExporter.Type type ) {
		final JPanel optionsContainer = new JPanel( new GridLayout( Configuration.getInstance().getLanguages().size(), 1 ) );
		optionsContainer.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder( groupTitle ), BorderFactory.createEmptyBorder( 0, 5, 0, 5) ) );
		for ( final Language language : Configuration.getInstance().getLanguages().values() ) {
			final JCheckBox checkbox = new JCheckBox( language.getTitle() );
			checkbox.setSelected( type.equals( XLSExporter.Type.ALL ) );
			checkbox.addKeyListener( ShortcutFactory.createDisposeWindowShortcut( this ) );
			checkbox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setExportOption( type, language.getId(), ((JCheckBox) e.getSource()).isSelected() );
				}
			});

			this.setExportOption( type, language.getId(), checkbox.isSelected() );
			optionsContainer.add( checkbox );
		}
		return optionsContainer;
	}
	
	private JPanel getBottomBar() {
		final JPanel bottomBar = new JPanel( new BorderLayout() );
		bottomBar.setBorder( BorderFactory.createEmptyBorder( 10, 10, 0, 10 ) );
		
		final BTButton okButton = new BTButton( Token.EXPORT ) {
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension( this.getWidth(), 25 );
			}
		};
		okButton.setFocusable( true );
		okButton.addKeyListener( ShortcutFactory.createDisposeWindowShortcut( this ) );
		okButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( isValidOptions() ) {
					findPath();
				} else {
					JOptionPane.showMessageDialog(null, Token.SELECT_AT_LEAST_ONE_OPTION, "", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		bottomBar.add( okButton );
		return bottomBar;
	}

	private void showDialog() {
		this.setTitle( Token.EXPORT_TO_XLS );
		this.setResizable( false );
		this.setSize( 300, 350 );
		this.setLocationRelativeTo( this.getOwner() );
		this.setVisible( true );
	}

	private void setExportOption( final XLSExporter.Type type, final Languages language, final boolean enabled ) {
		if ( this.exportOptionsMap.get( type ) == null ) {
			final LinkedHashMap<Languages, Boolean> option = new LinkedHashMap<>();
			option.put( language, enabled );
			this.exportOptionsMap.put( type, option );
		} else {
			this.exportOptionsMap.get( type ).put( language, enabled );
		}
	}
	
	private boolean isValidOptions() {
		for ( LinkedHashMap<Languages, Boolean> option : this.exportOptionsMap.values() ) {
			for ( Boolean checked : option.values() ) {
				if ( checked ) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void findPath() {
		String outputPath = Configuration.getInstance().getLastExportPath();
		boolean hasPermissionToSave = false;
		
		while ( !hasPermissionToSave ) {
			final JFileChooser fileChooser = new JFileChooser( outputPath );
			fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
			
			final int choice = fileChooser.showSaveDialog( fileChooser );
	
			if ( choice == JFileChooser.APPROVE_OPTION ) {
				outputPath = fileChooser.getSelectedFile().getParentFile().toString();
				hasPermissionToSave = Files.isWritable( new File( fileChooser.getSelectedFile().getParentFile().toString() ).toPath() );
				
				if ( !hasPermissionToSave ) {
					JOptionPane.showMessageDialog( null, Token.PERMISSION_DENIED, "", JOptionPane.WARNING_MESSAGE );
				} else {
					Configuration.getInstance().setLastExportPath( outputPath );
					this.export( fileChooser.getSelectedFile().toString() );
				}
			} else {
				return;
			}
		}
	}
	
	private void export( final String outputFile ) {
		final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws IOException {
				final XLSExporter xlsExporter = new XLSExporter( outputFile, getExportOptionsMap() );
				xlsExporter.save();
				return null;
			}
			
			@Override
			protected void done() {
				try {
					get();
					JOptionPane.showMessageDialog( null, Token.EXPORT_SUCCESS, "", JOptionPane.INFORMATION_MESSAGE );
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog( null, Token.EXPORT_FILE_IN_USE, "", JOptionPane.ERROR_MESSAGE );
				} finally {
					hideLoading();
				}
			}
		};
		worker.execute();
		
		this.showLoading();
//		this.dispose();
	}
	
	private LinkedHashMap<XLSExporter.Type, LinkedHashMap<Languages, Boolean>> getExportOptionsMap() {
		return this.exportOptionsMap;
	}
	
	private void showLoading() {
		if ( this.loading == null ) {
			this.loading = new BTLoading( this );
		}
		this.loading.setVisible( true );
	}

	private void hideLoading() {
		this.loading.dispose();
	}
	
}
