package view.dialog;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import view.dialog.controller.ToolbarDialogController;

/**
 * Klasa ToolbarDialog implementira metode toolbar-a u selekcionom dijalogu.
 * 
 * @author Ratomir
 *
 */
public class ToolbarDialog extends JToolBar 
{
	private static final long serialVersionUID = 1L;
	
	private JButton first;
    private JButton next;
    private JButton previous;
    private JButton last;
    
    private ToolbarDialogController controller = null;
    
    /**
     * Konstruktor klase ToolbarDialog
     * @param controller
     */
    public ToolbarDialog(ToolbarDialogController controller) 
    {
		this.setController(controller);
		
		first = new JButton(new ImageIcon("icons/ikonice nove/first.png"));
    	first.setToolTipText("First row");
    	first.setActionCommand("first");
    	first.addActionListener(controller);
    	
    	next = new JButton(new ImageIcon("icons/ikonice nove/next.png"));
    	next.setToolTipText("Next row");
    	next.setActionCommand("next");
    	next.addActionListener(controller);
    	
    	previous = new JButton(new ImageIcon("icons/ikonice nove/back.png"));
    	previous.setToolTipText("Previous row");
    	previous.setActionCommand("previous");
    	previous.addActionListener(controller);
    	
    	last = new JButton(new ImageIcon("icons/ikonice nove/last.png"));
    	last.setToolTipText("Last row");
    	last.setActionCommand("last");
    	last.addActionListener(controller);
    	
    	this.add(first);
    	this.add(previous);
    	this.add(next);
    	this.add(last);
	}
    
    /**
     * Metoda za omogucavanje komponenti.
     */
    public void enableComponents()
	{
		for (Component comp : this.getComponents())
		{
			comp.setEnabled(true);
		}
	}
	
    /**
     * Metoda za onemogucavanje komponenti.
     */
	public void disableComponents()
	{
		for (Component comp : this.getComponents())
		{
			comp.setEnabled(false);
		}
	}

	/**
	 * @return the controller
	 */
	public ToolbarDialogController getController()
	{
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(ToolbarDialogController controller)
	{
		this.controller = controller;
	}
}
