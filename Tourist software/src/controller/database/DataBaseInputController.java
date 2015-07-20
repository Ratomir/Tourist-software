package controller.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.database.DataBaseExplorerModel;
import view.dialog.SelectionDialog;
import view.input.Component;
import view.renderers.TreeElement.Table;
import controller.CommandController;

/**
 * Klasa sadrzi metode neophodne za izvrsavanje komandi u input poljima.
 * 
 * @author Ratomir
 *
 */
public class DataBaseInputController implements ActionListener
{
	private CommandController commandController = null;
	private DataBaseExplorerModel treeModel = null;
	
	public DataBaseInputController(CommandController controller, DataBaseExplorerModel treeModel)
	{
		this.setCommandController(controller);
		this.treeModel = treeModel;
	}
	/**
	 * Metoda osluskuje akcije i implementira funkcije accept i cancel
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "cancel":
		{
			this.commandController.cancel();
			break;
		}
		case "accept":
		{
			this.commandController.accept();
			break;
		}
		
		case "component":
		{
			Component comp = (Component)e.getSource();
			Table table = treeModel.getTable(comp.getCol().getRefTable());
			new SelectionDialog(table, comp.getCol(), comp.getData(), comp.getComponent());	
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

}
