/**
 * 
 */
package view.database;

import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import model.database.DataBaseExplorerModel;
import model.database.DataBaseTableModel;
import view.ComponentFactory;
import view.StatusBar;
import view.database.column.CustomTable;
import view.menu.MenuBar;
import view.renderers.TreeRenderer;
import view.toolbar.ToolBar;
import controller.database.DataBaseExplorerController;

/**
 * Klasa DataBaseExplorer obezbjedjuje prikaz baze podataka u formi hijerarhije.
 * 
 * @author Ratomir
 */
public class DataBaseExplorer extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	
	private JTree tree;
	private DataBaseExplorerController controller;
	private DataBaseExplorerModel model;
	private TreeRenderer renderer = null;
	private DataBaseTableModel dataBaseModel = null;
	private DataBaseTableView  dataBaseTableView = null;
	
	private StatusBar statusBar = null;
	private ToolBar toolBar = null;
	private MenuBar menuBar = null;

	private ComponentFactory componentFactory = null;
	private CustomTable customTable = null;


	/**
	 * Konstruktor klase DataBaseExplorer
	 * @param renderer
	 * @param dataBaseModel
	 * @param dataBaseTableView
	 */
	public DataBaseExplorer(TreeRenderer renderer, DataBaseTableModel dataBaseModel, DataBaseTableView dataBaseTableView)
	{
		this.setRenderer(renderer);
		this.dataBaseModel = dataBaseModel;
		
		this.dataBaseTableView = dataBaseTableView;
	}
	
	/**
	 * Metoda setTree postavlja stablo.
	 * @param path
	 */
    public void setTree(String path)
    {
    	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Database");
    	model = new DataBaseExplorerModel(root, path);
    	this.getComponentFactory().setExplorerModel(model);
    	
		tree = new JTree(model);
		tree.setBorder(new EmptyBorder(2, 2, 2, 2));
		tree.setRowHeight(33);
		tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

		controller = new DataBaseExplorerController(this.dataBaseModel, this.dataBaseTableView);
		controller.setMenuBar(menuBar);
		controller.setStatusBar(statusBar);
		controller.setToolBar(toolBar);
		controller.setColumnTable(this.getCustomTable());
		
		tree.addTreeSelectionListener((TreeSelectionListener) controller);
		tree.addMouseListener((MouseListener) controller);
		
		tree.setCellRenderer(renderer);
		tree.setFont(new FontUIResource("Arial",Font.LAYOUT_LEFT_TO_RIGHT,16));
		setViewportView(tree);		
    }     
	/**
	 * @return the tree
	 */
	public JTree getTree()
	{
		return tree;
	}

	/**
	 * @param tree the tree to set
	 */
	public void setTree(JTree tree)
	{
		this.tree = tree;
	}

	/**
	 * @return the model
	 */
	public DataBaseExplorerModel getModel()
	{
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DataBaseExplorerModel model)
	{
		this.model = model;
	}

	/**
	 * @return the renderer
	 */
	public TreeRenderer getRenderer()
	{
		return renderer;
	}

	/**
	 * @param renderer the renderer to set
	 */
	public void setRenderer(TreeRenderer renderer)
	{
		this.renderer = renderer;
	}

	/**
	 * @return the statusBar
	 */
	public StatusBar getStatusBar()
	{
		return statusBar;
	}

	/**
	 * @param statusBar the statusBar to set
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
	 * @return the componentFactory
	 */
	public ComponentFactory getComponentFactory()
	{
		return componentFactory;
	}

	/**
	 * @param componentFactory the componentFactory to set
	 */
	public void setComponentFactory(ComponentFactory componentFactory)
	{
		this.componentFactory = componentFactory;
	}

	/**
	 * @return the customTable
	 */
	public CustomTable getCustomTable()
	{
		return customTable;
	}

	/**
	 * @param customTable the customTable to set
	 */
	public void setCustomTable(CustomTable customTable)
	{
		this.customTable = customTable;
	}

}
