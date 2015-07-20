package state;

import javax.swing.JTable;

import state.interfaces.IState;
import view.StatusBar;
/**
 * Klasa SelectionDialogState realizuje stanje u kom se vrsi izbor podatka iz vezane tabele.
 * 
 * @author Ratomir
 *
 */
public class SelectionDialogState implements IState
{
	public static StatusBar statusBar = null;
	public SelectionDialogState()
	{
		this.statusBar.setState("Selection dialog state");
	}
	
	@Override
	public void first(JTable table) 
	{
		table.setRowSelectionInterval(0, 0);
	}

	@Override
	public void next(JTable table) 
	{
		int row = (table.getSelectedRow()<table.getRowCount()-1) ? (table.getSelectedRow()+1) : (0);
		table.setRowSelectionInterval(row, row);
	}

	@Override
	public void previous(JTable table) 
	{
		int row = (table.getSelectedRow()>0) ? (table.getSelectedRow()-1) : (table.getRowCount()-1);
		table.setRowSelectionInterval(row, row);
	}

	@Override
	public void last(JTable table) 
	{
		table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
	}

	@Override
	public void updateStatus(JTable table) {}

	@Override
	public void newRow() {}

	@Override
	public void editRow() {}

	@Override
	public void deleteRow() {}
	
	@Override
	public void accept() {}

	@Override
	public void cancel() {}
}
