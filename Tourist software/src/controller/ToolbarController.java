package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

/**
 * Klasa Toolbar Controller sadrzi akcije i osluskivace koji se koriste za rad sa toolbar-om.
 *
 * @author Ratomir
 * 
 */
public class ToolbarController implements ActionListener
{
	private CommandController commandController = null;
	private JTable table = null;

	public ToolbarController(CommandController controller)
	{
		this.commandController = controller;
	}
	/**
	 * Metoda prati akcije nad dugmadima i omogucava implementaciju funkcija 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "loadDatabaseT":
		{
			this.commandController.loadDatabase();
			break;
		}
		case "switchDatabaseT":
		{
			this.commandController.loadDatabase();
			break;
		}

		case "firstT":
		{
			this.commandController.first();
			break;
		}

		case "previousT":
		{
			this.commandController.previous();
			break;
		}
		case "nextT":
		{
			this.commandController.next();
			break;
		}
		case "lastT":
		{
			this.commandController.last();
			break;
		}
		case "newRowT":
		{
			this.commandController.newRow();
			break;
		}
		case "editRowT":
		{
			this.commandController.edit(getTable());
			break;
		}
		case "deleteRowT":
		{
			this.commandController.delete();;
			break;
		}
		case "reportT":
		{
			this.commandController.report();
			break;
		}
		case "cancelT":
		{
			this.commandController.cancel();
			break;
		}
		case "acceptT":
		{
			this.commandController.accept();
			break;
		}
		case "aboutT":
		{
			this.commandController.about();
			break;
		}
		case "logOutT":
		{
			this.commandController.logOut();
			break;
		}
		}
	}

	/**
	 * @return the commandController
	 */
	public CommandController getCommandController()
	{
		return commandController;
	}

	/**
	 * @param commandController
	 *            the commandController to set
	 */
	public void setCommandController(CommandController commandController)
	{
		this.commandController = commandController;
	}

	/**
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}

}
