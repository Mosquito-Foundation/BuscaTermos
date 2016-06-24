package gui.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import utils.IconManager;
import utils.Token;

public class BTSplitPane extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JComponent topComponent;
	
	private final JComponent bottomComponent;
	
	private BTButton split;
	
	private boolean expanded;

	public BTSplitPane(final JComponent top, final JComponent bottom) {
		this(top, bottom, true);
	}
	
	public BTSplitPane(final JComponent top, final JComponent bottom, final boolean isExpanded) {
		super(new BorderLayout());
		
		this.topComponent = top;
		this.bottomComponent = bottom;
		this.expanded = isExpanded;
		
		this.createComponents();
	}
	
	private void createComponents() {
		this.add(this.getTopComponent());
		this.add(this.getSplitAndBottomComponent(), BorderLayout.SOUTH);
	}
	
	private JPanel getSplitAndBottomComponent() {
		final JPanel bottomPane = new JPanel(new BorderLayout());
		bottomPane.add(this.getSplit());
		bottomPane.add(this.getBottomComponent(), BorderLayout.SOUTH);
		return bottomPane;
	}
	
	private BTButton getSplit() {
		if(this.split == null) {
			this.split = new BTButton(this.getSplitTooltip(), IconManager.getInstance().getIcon( this.getSplitIconName(), 12, 12 ));
			this.split.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showHideBottomComponent();
				}
			});
		}
		return this.split;
	}
	
	private String getSplitTooltip() {
		return this.isExpanded() ? Token.COLLAPSE : Token.EXPAND;
	}
	
	private String getSplitIconName() {
		return this.isExpanded() ? "icons/collapse.png" : "icons/expand.png";
	}
	
	private void showHideBottomComponent() {
		this.setExpanded(!this.isExpanded());
		this.getSplit().setIcon(IconManager.getInstance().getIcon(this.getSplitIconName(), 12, 12));
		this.getSplit().setToolTipText(this.getSplitTooltip());
		this.getSplit().revalidate();
	}
	
	public JComponent getTopComponent() {
		return this.topComponent;
	}

	public JComponent getBottomComponent() {
		return this.bottomComponent;
	}

	public boolean isExpanded() {
		return this.expanded;
	}

	public void setExpanded(final boolean expanded) {
		this.expanded = expanded;
	}
	
}
