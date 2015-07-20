package state;

import javax.swing.JTable;

import localization.Localization;
import state.interfaces.IState;
import view.StatusBar;
import view.menu.MenuBar;
import view.toolbar.ToolBar;
/**
 * Klasa ConnectionState omogucava realizaciju stanja povezivanja sa bazom.
 * 
 * @author Ratomir
 *
 */
public class ConnectionState implements IState
{
	public static StatusBar statusBar;
	public static ToolBar   toolBar;
	public static MenuBar   menuBar;
	
	private Localization localization = null;
	
	public ConnectionState()
	{
		this.localization = Localization.getInstance();
		
		this.toolBar.disableComponents();
		this.toolBar.setVisible(true);
		
		this.statusBar.setState(this.localization.getString("state.connection"));
		this.menuBar.disableEditMenu();
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

	@Override
	public void updateStatus(JTable table)
	{
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

}
