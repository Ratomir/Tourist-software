package controller.database;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import localization.Localization;
import model.database.DataBaseTableModel;
import state.ConnectionState;
import state.SelectionState;
import state.State;
import view.StatusBar;
import view.database.DataBaseTableView;
import view.database.column.CustomTable;
import view.database.column.CustomTableModel;
import view.menu.MenuBar;
import view.renderers.TreeElement;
import view.renderers.TreeElement.Table;
import view.toolbar.ToolBar;

/**
 * Kontroler omogucava rukovanje dogadjajima nad stablom.
 * Dvostrukim klikom na tabelu u stablu omogucava se ucitavanje 
 * tabele iz baze podataka i njen prikaz.
 * 
 * @author Ratomir
 *
 */
public class DataBaseExplorerController implements MouseListener, TreeSelectionListener
{
	private JTree tree;
	private Object node;

	private DataBaseTableModel dataBaseModel = null;
	private DataBaseTableView tableView = null;
	private CustomTable columnTable = null;

	private StatusBar statusBar = null;
	private ToolBar toolBar = null;
	private MenuBar menuBar = null;
	
	private Localization localization = null;

	public DataBaseExplorerController(DataBaseTableModel dataBaseModel, DataBaseTableView tableView)
	{
		this.dataBaseModel = dataBaseModel;
		this.tableView = tableView;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		tree = (JTree) e.getSource();
		node = tree.getLastSelectedPathComponent();
	}
	/**
	 * Metoda omogucava rukovanje dogadjajima koje su posljedica klika misem
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.localization = Localization.getInstance();
		
		if ((e.getSource() instanceof JTree) && (tree != null))
		{
			node = tree.getLastSelectedPathComponent();

			//
			// Klik na tabelu u stablu.
			//
			if ((e.getClickCount() == 2) && (node instanceof TreeElement.Table))
			{

				// TO-DO Treba iskljuciti akcije na toolbaru
				State.setState(new ConnectionState());
				this.getToolBar().disableComponents();

				// Tabela ispravno ucitana.
				if (this.dataBaseModel.loadTable((Table) node))
				{
					// TO-DO Treba podesiti ostale parametre.
					this.statusBar.setSelectedTable(this.localization.getString("selectedTable") + ((TreeElement.Table) node).getName());
				}

				this.getToolBar().getController().getCommandController().setTable(this.tableView.getTable());
				this.getToolBar().getController().getCommandController().setTableView(this.tableView);
				
				this.getToolBar().getController().setTable(this.tableView.getTable());
				this.getMenuBar().getController().setTable(this.tableView.getTable());

				State.setState(new SelectionState(this.tableView.getTable(), dataBaseModel));
				State.getState().updateStatus(this.tableView.getTable());
			}
			
			if ((e.getClickCount() == 2) && (node instanceof TreeElement.Column))
			{
				
				TreeElement.Column column = (TreeElement.Column)node;
				this.columnTable.setNameColumn(column.getName());
				CustomTableModel model = this.getColumnTable().getModel();
				model.setValueAt(column.isNullable(), 0, 1);
				model.setValueAt(column.isPrimary(),  1, 1);
				model.setValueAt(column.getSize(), 2, 1);
				model.setValueAt(column.getGroup(), 3, 1);
				model.setValueAt(column.getType(), 4, 1);
				model.setValueAt(column.getRefTable(), 5, 1);
				model.setValueAt(column.getRefColumn(), 6, 1);
				
				this.getColumnTable().updateTable();
				this.getColumnTable().setVisible(true);
			}
		}

		if (tree != null)
		{
			int row = tree.getRowForLocation(e.getX(), e.getY());

			if (row == -1)
			{
				tree.clearSelection();
			}
			else
			{
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				tree.setSelectionPath(selPath);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	/**
	 * @return the tree
	 */
	public JTree getTree()
	{
		return tree;
	}

	/**
	 * @param tree
	 *            the tree to set
	 */
	public void setTree(JTree tree)
	{
		this.tree = tree;
	}

	/**
	 * @return the node
	 */
	public Object getNode()
	{
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(Object node)
	{
		this.node = node;
	}

	/**
	 * @return the statusBar
	 */
	public StatusBar getStatusBar()
	{
		return statusBar;
	}

	/**
	 * @param statusBar
	 *            the statusBar to set
	 */
	public void setStatusBar(StatusBar statusBar)
	{
		this.statusBar = statusBar;
	}

	/**
	 * @return the toolBar
	 */
	public ToolBar getToolBar()
	{
		return toolBar;
	}

	/**
	 * @param toolBar
	 *            the toolBar to set
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
	 * @param menuBar
	 *            the menuBar to set
	 */
	public void setMenuBar(MenuBar menuBar)
	{
		this.menuBar = menuBar;
	}

	/**
	 * @return the columnTable
	 */
	public CustomTable getColumnTable()
	{
		return columnTable;
	}

	/**
	 * @param columnTable the columnTable to set
	 */
	public void setColumnTable(CustomTable columnTable)
	{
		this.columnTable = columnTable;
	}

}
