package state;

import javax.swing.JTable;

import localization.Localization;
import state.interfaces.IState;
import view.StatusBar;
import view.database.DataBaseInput;
import view.menu.MenuBar;
import view.toolbar.ToolBar;
/**
 * Klasa NewState definise metode za realizaciju stanja dodavanja novog reda u tabelu.
 * 
 * @author Ratomir
 *
 */
public class NewState  implements IState
{
	public static StatusBar statusBar = null;
	public static ToolBar toolBar = null;
	public static MenuBar menuBar = null;
	
	public static DataBaseInput dataBaseInput = null;
	
	private Localization localization = null;
	
	private JTable table = null;
	
	public NewState(JTable table)
	{
		this.localization = Localization.getInstance();
		
		this.setTable(table);
		
		updateStatus(null);
		this.dataBaseInput.clearFields();
	}
	
	@Override
	public void first(JTable table)
	{
	}

	@Override
	public void next(JTable table)
	{
	}

	@Override
	public void previous(JTable table)
	{
	}

	@Override
	public void last(JTable table)
	{
	}
	/**
	 * Metoda prati promjene selektovanja trenutnog reda, stanja i selektovane tabele.
	 */
	@Override
	public void updateStatus(JTable table)
	{
		this.statusBar.setCurrentRow("");
		this.statusBar.setState(this.localization.getString("state.new"));
		this.statusBar.setSelectedTable(this.localization.getString("selectedTable") + this.dataBaseInput.getTableModel().getTableName());
		
		this.menuBar.enableEditMenu();
		this.toolBar.enableComponents();
		this.toolBar.setVisible(true);
		this.dataBaseInput.setEnabled(true);
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
	/**
	 * Metoda pamti sve promjene.
	 */
	@Override
	public void accept()
	{
//		InputFieldView inputField = Application.getApplication().getInputField();
//		DbTableModel tableModel = Application.getApplication().getTableModel();	
//		tableModel.newItem(inputField.getValues());
		this.dataBaseInput.getTableModel().newItem(this.dataBaseInput.getValues());
	}
	/**
	 * Metoda omogucava izlaz iz trenutnog stanja
	 */
	@Override
	public void cancel()
	{
		State.setState(new SelectionState(this.getTable(),this.dataBaseInput.getTableModel()));
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
