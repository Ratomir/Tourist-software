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
 * Klasa EditState omogucava realizaciju stanja izmjene podataka u tabeli.
 * 
 * @author Ratomir
 *
 */
public class EditState implements IState
{
	private JTable table = null;
	
	public static StatusBar statusBar = null;
	public static ToolBar toolBar = null;
	public static MenuBar menuBar = null;
	
	public static DataBaseInput dataBaseInput = null;
	
	private Localization localization = null;
	
	private DataBaseTableModel tableModel = null;
	
	public EditState(JTable table, DataBaseTableModel tableModel)
	{
		this.localization = Localization.getInstance(); 
		this.tableModel = tableModel;
		this.table = table;
		updateStatus(table);
	}
	
	@Override
	public void first(JTable table)
	{
	}
	/**
	 * Metoda selektuje sljedeci red u tabeli.
	 */
	@Override
	public void next(JTable table)
	{
		int row = (table.getSelectedRow()<table.getRowCount()-1) ? (table.getSelectedRow()+1) : (0);
		table.setRowSelectionInterval(row, row);
		updateStatus(table);
	}
	/**
	 * Metoda selektuje prethodni red u tabeli.
	 */
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
	}

	@Override
	public void updateStatus(JTable table)
	{
		if(table == null) return;
		
		int row = table.getSelectedRow();
		
		if(row==-1)
		{
			row = 0;
			table.setRowSelectionInterval(row, row);	
		}
		
		statusBar.setCurrentRow(this.localization.getString("currentRow") + (table.getSelectedRow()+1) + "/" + table.getRowCount());
		statusBar.setState(this.localization.getString("state.edit"));
		
		menuBar.enableEditMenu();
		toolBar.enableComponents();
		toolBar.setVisible(true);
		dataBaseInput.setEnabled(true);
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
	/**
	 * Metoda brise selektovani red
	 */
	@Override
	public void deleteRow()
	{
//		app = Application.getApplication();
//		InputFieldView inputField = app.getInputField();
//		DbTableModel tableModel = app.getTableModel();	
//		tableModel.delete(inputField.getIdRows());
		this.dataBaseInput.getTableModel().delete(this.dataBaseInput.getIdRows());
	}
	/**
	 * Metoda pamti sve promjene.
	 */
	@Override
	public void accept()
	{
		this.dataBaseInput.getTableModel().updateTable(this.dataBaseInput.getValues());
	}
	/**
	 * Metoda omogucava izlaz iz trenutnog stanja
	 */
	@Override
	public void cancel()
	{
		State.setState(new SelectionState(getTable(), this.dataBaseInput.getTableModel()));
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

}
