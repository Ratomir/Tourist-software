/**
 * 
 */
package view.toolbar;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import localization.Localization;
import model.UserModel;
import model.database.DataBaseTableModel;
import observer.IObserver;
import controller.ToolbarController;

/**
 * Klasa ToolBar sluzi za kreiranje ToolBar-a.
 * @author Jelena
 *
 */
public class ToolBar extends JToolBar implements IObserver
{
	private static final long serialVersionUID = 1L;
	public ToolBarItem switchDatabase, loadDatabase;
	public ToolBarItem first, next, previous, last;
	public ToolBarItem newRow, editRow, deleteRow;
	public ToolBarItem report, accept, cancel;
	public ToolBarItem about,logOut;
	
	private ToolbarController controller = null;
	private DataBaseTableModel tableModel = null;
	
	private Localization localization = Localization.getInstance();

	private UserModel user = null;
	JLabel userName = null;
	
	/**
	 * Konstruktor ToolBar-a.
	 */
	public ToolBar(ToolbarController controller, UserModel userModel) 
	{
		this.controller = controller;
		this.setUser(userModel);
		
		loadDatabase = new ToolBarItem(new ImageIcon("icons/ikonice nove/base.png"),this.localization.getString("toolbox.loadDatabase"), "loadDatabaseT");
		this.localization.registerComponent("toolbox.loadDatabase", loadDatabase);
		loadDatabase.addActionListener(controller);
		loadDatabase.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		switchDatabase = new ToolBarItem(new ImageIcon("icons/ikonice nove/switch.png"), this.localization.getString("toolbox.switchDatabase"), "switchDatabaseT");
		this.localization.registerComponent("toolbox.switchDatabase", switchDatabase);
		switchDatabase.addActionListener(controller);
		switchDatabase.setCursor(new Cursor(Cursor.HAND_CURSOR));

		first = new ToolBarItem(new ImageIcon("icons/ikonice nove/First.png"),this.localization.getString("toolbox.first"), "firstT");
		this.localization.registerComponent("toolbox.first", first);
		first.addActionListener(controller);
		first.setCursor(new Cursor(Cursor.HAND_CURSOR));

		previous = new ToolBarItem(new ImageIcon("icons/ikonice nove/back.png"),this.localization.getString("toolbox.previous"), "previousT");
		this.localization.registerComponent("toolbox.previous", previous);
		previous.addActionListener(controller);
		previous.setCursor(new Cursor(Cursor.HAND_CURSOR));

		next = new ToolBarItem(new ImageIcon("icons/ikonice nove/next.png"),this.localization.getString("toolbox.next"), "nextT");
		this.localization.registerComponent("toolbox.next", next);
		next.addActionListener(controller);
		next.setCursor(new Cursor(Cursor.HAND_CURSOR));

		last = new ToolBarItem(new ImageIcon("icons/ikonice nove/last.png"),this.localization.getString("toolbox.last"), "lastT");
		this.localization.registerComponent("toolbox.last", last);
		last.addActionListener(controller);
		last.setCursor(new Cursor(Cursor.HAND_CURSOR));

		newRow = new ToolBarItem(new ImageIcon("icons/ikonice nove/new.png"),this.localization.getString("toolbox.new"), "newRowT");
		this.localization.registerComponent("toolbox.new", newRow);
		newRow.addActionListener(controller);
		newRow.setCursor(new Cursor(Cursor.HAND_CURSOR));

		editRow = new ToolBarItem(new ImageIcon("icons/ikonice nove/update.png"),this.localization.getString("toolbox.edit"), "editRowT");
		this.localization.registerComponent("toolbox.edit",editRow);
		editRow.addActionListener(controller);
		editRow.setCursor(new Cursor(Cursor.HAND_CURSOR));

		deleteRow = new ToolBarItem(new ImageIcon("icons/ikonice nove/delete.png"),this.localization.getString("toolbox.delete"), "deleteRowT");
		this.localization.registerComponent("toolbox.delete", deleteRow);
		deleteRow.addActionListener(controller);
		deleteRow.setCursor(new Cursor(Cursor.HAND_CURSOR));

		report = new ToolBarItem(new ImageIcon("icons/ikonice nove/report.png"),this.localization.getString("toolbox.createReport"), "reportT");
		this.localization.registerComponent("toolbox.createReport", report);
		report.addActionListener(controller);
		report.setCursor(new Cursor(Cursor.HAND_CURSOR));

		accept = new ToolBarItem(new ImageIcon("icons/ikonice nove/accept.png"),this.localization.getString("toolbox.accept"), "acceptT");
		this.localization.registerComponent("toolbox.accept", accept);
		accept.addActionListener(controller);
		accept.setCursor(new Cursor(Cursor.HAND_CURSOR));

		cancel = new ToolBarItem(new ImageIcon("icons/ikonice nove/cancel.png"),this.localization.getString("toolbox.cancel"), "cancelT");
		this.localization.registerComponent("toolbox.cancel", cancel);
		cancel.addActionListener(controller);
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		about = new ToolBarItem(new ImageIcon("icons/ikonice nove/info.png"),this.localization.getString("toolbox.about"), "aboutT");
		this.localization.registerComponent("toolbox.about", about);
		about.addActionListener(controller);
		about.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		logOut= new ToolBarItem(new ImageIcon("icons/ikonice nove/logout.png"), this.localization.getString("toolbox.logOut"), "logOutT");
		this.localization.registerComponent("toolbox.logOut", logOut);
		logOut.addActionListener(controller);
		logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		add(loadDatabase);
		add(switchDatabase);
		addSeparator();
		add(first);
		add(previous);
		add(next);
		add(last);
		addSeparator();
		add(newRow);
		add(editRow);
		add(deleteRow);
		addSeparator();
		add(report);
		add(accept);
		add(cancel);
		addSeparator();
		add(about);
		
		
		Component horizontalGlue = Box.createHorizontalGlue();
		add(horizontalGlue);
		
		userName = new JLabel(userModel.getUserName());
		userName.setFont(new Font("Tahoma", Font.BOLD, 20));
		userName.setIcon(new ImageIcon("icons/ikonice nove/user.png"));
		userName.setHorizontalTextPosition(JLabel.RIGHT);
		add(userName);
		add(logOut);
	}

	@Override
	public void update()
	{
	}

	/**
	 * @return the tableModel
	 */
	public DataBaseTableModel getTableModel()
	{
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(DataBaseTableModel tableModel)
	{
		this.tableModel = tableModel;
	}
	
	/**
	 * Metoda disableComponents sluzi za omogucavanje opcija ToolBara-a
	 */
	public void enableComponents()
	 {
		  for (Component comp : this.getComponents())
		  {
			  comp.setEnabled(true);
		  }
	 }
	
	/**
	 * Metoda disableComponents sluzi za onemogucavanje opcija ToolBara-a
	 */
	 public void disableComponents()
	 {
		  for (Component comp : this.getComponents())
		  {
			  comp.setEnabled(false);
		  }
		  
		  this.userName.setEnabled(true);
		  this.logOut.setEnabled(true);
	 }

	/**
	 * @return the controller
	 */
	public ToolbarController getController()
	{
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(ToolbarController controller)
	{
		this.controller = controller;
	}

	/**
	 * @return the user
	 */
	public UserModel getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserModel user)
	{
		this.user = user;
	}
}
