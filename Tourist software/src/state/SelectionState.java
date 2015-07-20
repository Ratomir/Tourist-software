package state;

import javax.swing.JTable;

import localization.Localization;
import model.database.DataBaseTableModel;
import state.interfaces.IState;
import view.StatusBar;
import view.database.DataBaseInput;
import view.menu.MenuBar;
import view.toolbar.ToolBar;
/**
 * Klasa realizuje stanje izbora reda.
 * 
 * @author Ratomir
 *
 */
public class SelectionState implements IState
{
	public static StatusBar statusBar = null;
	public static ToolBar toolBar = null;
	public static MenuBar menuBar = null;
	
	public static DataBaseInput dataBaseInput = null;
	
	private Localization localization = null;
	
	private JTable  table = null;
	private DataBaseTableModel tableModel = null;
	
	public SelectionState(JTable table, DataBaseTableModel tableModel)
	{
		this.localization = Localization.getInstance();
		
		this.table = table;
		this.tableModel = tableModel;
		
		this.menuBar.enableEditMenu();
		this.toolBar.enableComponents();
		this.toolBar.accept.setEnabled(false);
		this.toolBar.cancel.setEnabled(false);
		this.toolBar.deleteRow.setEnabled(false);
		this.toolBar.setVisible(true);
		
		this.dataBaseInput.setEnabled(false);
		updateStatus(this.table);
	}
	
	@Override
	public void first(JTable table)
	{
		table.setRowSelectionInterval(0, 0);
		updateStatus(table);
	}

	@Override
	public void next(JTable table)
	{
		int row = (table.getSelectedRow()<table.getRowCount()-1) ? (table.getSelectedRow()+1) : (0);
		table.setRowSelectionInterval(row, row);
		updateStatus(table);
	}

	@Override
	public void previous(JTable table)
	{
		int row = (table.getSelectedRow()>0) ? (table.getSelectedRow()-1) : (table.getRowCount()-1);
		table.setRowSelectionInterval(row, row);
		updateStatus(table);
	}

	@Override
	public void last(JTable table)
	{
		table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
		updateStatus(table);
	}

	@Override
	public void updateStatus(JTable table)
	{
		int row = table.getSelectedRow();
		
		if(row==-1)
		{
			row = 0;
			table.setRowSelectionInterval(row, row);	
		}
		
		this.statusBar.setSelectedTable(this.localization.getString("selectedTable") + this.statusBar.getModel().getTableName());
		this.statusBar.setCurrentRow(this.localization.getString("currentRow") + (row+1) + "/" + table.getRowCount());
		this.statusBar.setState(this.localization.getString("state.selection"));
		
		this.dataBaseInput.setValues(this.tableModel.getRow(row));
	}

	@Override
	public void newRow()
	{
	}

	@Override
	public void editRow()
	{
	}

	@Override
	public void deleteRow()
	{
	}

	@Override
	public void accept()
	{
	}

	@Override
	public void cancel()
	{
	}

	/**
	 * @return the toolBar
	 */
	public ToolBar getToolBar()
	{
		return toolBar;
	}

	/**
	 * @param toolBar the toolBar to set
	 */
	public void setToolBar(ToolBar toolBar)
	{
		this.toolBar = toolBar;
	}

	/**
	 * @return the menuBar
	 */
	public MenuBar getMenuBar()
	{
		return menuBar;
	}

	/**
	 * @param menuBar the menuBar to set
	 */
	public void setMenuBar(MenuBar menuBar)
	{
		this.menuBar = menuBar;
	}

	/**
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
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

}
