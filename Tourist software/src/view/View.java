package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import localization.Localization;
import model.UserModel;
import model.database.DataBaseTableModel;
import parser.XMLreader;
import state.ConnectionState;
import state.EditState;
import state.NewState;
import state.ReadyState;
import state.SelectionDialogState;
import state.SelectionState;
import state.State;
import view.database.DataBaseExplorer;
import view.database.DataBaseInput;
import view.database.DataBaseTableView;
import view.database.column.CustomTable;
import view.login.LoginForm;
import view.menu.MenuBar;
import view.renderers.TreeRenderer;
import view.toolbar.ToolBar;
import DBLayer.DBConnection;

import com.javadocking.DockingManager;
import com.javadocking.dock.BorderDock;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.model.FloatDockModel;

import controller.CommandController;
import controller.MenubarController;
import controller.ToolbarController;
import controller.WindowController;
import controller.database.DataBaseInputController;

/**
 * Klasa kreira izgled aplikacije. Dozvoljava korisniku kretanja kroz
 * aplikaciju i unos komandi.
 * 
 * @author Ratomir
 *
 */
public class View extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String locationDataBaseFile = "";
	public static String locale = "";
	
	public Localization localization = null;
	
	public LoginForm loginForma = null;
	
	private JPanel mainPanel;
	
	public DataBaseExplorer dataBaseExplorer = null;
	
	public DataBaseTableModel dataBaseTableModel = null;
	public DataBaseTableView dataBaseTableView = null;
	
	public DataBaseInput dataBaseInput = null;
	
	public MenuBar menuBar = null;
	public StatusBar statusBar = null;
	public ToolBar toolBar = null;
	
	//controllers
	public MenubarController menuController = null;
	
	public ToolbarController toolbarController=null;
	
	public DataBaseInputController dataBaseInputController = null;
	
	public CommandController controller = null;
	
	//#############
	
	public TreeRenderer treeRenderer = null;
	
	public DBConnection connection = null;
	
	public CustomTable columnTable = null;
	
	public UserModel user = null;
	
	/**
	 * Konstruktor klase View
	 */
	public View(UserModel userModel)
	{  
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		
		this.user = userModel;
		
		connection = new DBConnection();
		
		/**
		 * Inicijalizacija Database Explorera i Database viewera
		 */
		mainPanel = new JPanel(new BorderLayout());
		
		dataBaseTableModel = new DataBaseTableModel();
		dataBaseTableView = new DataBaseTableView();
		
		dataBaseTableView.setModel(dataBaseTableModel);
		
		treeRenderer = new TreeRenderer();
		ComponentFactory componentFactory = new ComponentFactory();
		this.columnTable = new CustomTable();
		dataBaseExplorer = new DataBaseExplorer(treeRenderer, dataBaseTableModel, dataBaseTableView);
		dataBaseExplorer.setComponentFactory(componentFactory);
		dataBaseExplorer.setCustomTable(this.columnTable);
		//poziv konstruktora cije klase formiraju prikaz editora
		
		controller = new CommandController(dataBaseExplorer);
		controller.setApplication(this);
		
		
		
		dataBaseInputController = new DataBaseInputController(controller, dataBaseExplorer.getModel());
		menuController    = new MenubarController(controller);
		toolbarController = new ToolbarController(controller);
		
		dataBaseInput = new DataBaseInput(dataBaseInputController, componentFactory);
		dataBaseInput.setTableModel(dataBaseTableModel);
		
		controller.setInputView(dataBaseInput);
		
		XMLreader reader = new XMLreader();
		reader.ReadWindowParameters(this);
		this.localization = Localization.getInstance();
		
		menuBar = new MenuBar(menuController,locale);
		dataBaseExplorer.setMenuBar(menuBar);
		
		statusBar = new StatusBar();
		statusBar.setTableModel(dataBaseTableModel);
		
		
		toolBar = new ToolBar(toolbarController, user);
		toolBar.setTableModel(dataBaseTableModel);
		
		//dodavanje observera
		dataBaseTableModel.addObserver(dataBaseTableView);
		dataBaseTableModel.addObserver(statusBar);
		dataBaseTableModel.addObserver(dataBaseInput);
		
		//dodavanje dataBaseExploreru statusbara i toolbara
		dataBaseExplorer.setStatusBar(statusBar);
		dataBaseExplorer.setToolBar(toolBar);
		
		initialState();
		
		State.getState();
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		this.setJMenuBar(menuBar);
		
		this.setTitle(localization.getString("title"));
		this.localization.registerComponent("title", this);

		this.addWindowListener(new WindowController(this));

		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		this.dynamicWindow();
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		this.setType(Type.NORMAL);					
		this.setIconImage(new ImageIcon("icons/logo64x64.png").getImage());
		this.setVisible(true);
		this.pack();
	}

	/**
	 * Metoda initialState vrsi inicijalizaciju stanja
	 */
	private void initialState()
	{
		SelectionState.statusBar = statusBar;
		SelectionState.toolBar = toolBar;
		SelectionState.menuBar = menuBar;
		SelectionState.dataBaseInput = dataBaseInput;
		
		NewState.statusBar = statusBar;
		NewState.toolBar = toolBar;
		NewState.menuBar = menuBar;
		NewState.dataBaseInput = dataBaseInput;
		
		SelectionDialogState.statusBar = statusBar;
		
		ConnectionState.statusBar = statusBar;
		ConnectionState.toolBar = toolBar;
		ConnectionState.menuBar = menuBar;
		
		EditState.statusBar = statusBar;
		EditState.toolBar = toolBar;
		EditState.menuBar = menuBar;
		EditState.dataBaseInput = dataBaseInput;
		
		ReadyState.menuBar = menuBar;
		ReadyState.toolBar = toolBar;
		ReadyState.statusBar = statusBar;
	}

	/**
	 * Metoda za otvaranje LogIn forme
	 */
	public void loginForm()
	{
		this.loginForma = new LoginForm();
	}
	
	/**
	 * Metoda za citanje lokalizacije
	 */
	public void readLocalization()
	{
		//citanje iz xml-a za lokalizaciju
		XMLreader reader = new XMLreader();
		reader.ReadWindowParameters(this);
		this.localization = Localization.getInstance("en", "US");
	}

	/**
	 * Metoda dynamicWindow omogucava razmjestaj elemenata onako kako zelimo
	 */
	public void dynamicWindow()
	{
		//JAVADOCKING
		this.localization = Localization.getInstance();
		
		FloatDockModel dockModel = new FloatDockModel();
		dockModel.addOwner("turist", this);
		DockingManager.setDockModel(dockModel);
		
		Dockable dockableDBExplorer = new DefaultDockable("dbe", dataBaseExplorer, this.localization.getString("dbExplorer"));
		Dockable dockableDBGrid = new DefaultDockable("dbg", dataBaseTableView, this.localization.getString("dbGrid"));
		Dockable dockableDBInput= new DefaultDockable("dbi", dataBaseInput,this.localization.getString("dbInput"));
		
		Dockable dockableColumnTable = new DefaultDockable("dbp", this.columnTable, this.localization.getString("columnProperties"));
		
		TabDock dock1 = new TabDock();
		TabDock dock2 = new TabDock();
		TabDock dock3 = new TabDock();
		
		dock1.addDockable(dockableColumnTable, new Position(1));
		dock1.addDockable(dockableDBExplorer, new Position(0));
		
		dock2.addDockable(dockableDBGrid, new Position(0));
		dock3.addDockable(dockableDBInput, new Position(0));
			
		SplitDock horizontalSplitDock = new SplitDock();
		SplitDock centerSplitDock = new SplitDock();
		SplitDock rightSplitDock = new SplitDock();
		
		centerSplitDock.addChildDock(dock1, new Position(Position.LEFT));
		centerSplitDock.addChildDock(rightSplitDock, new Position(Position.RIGHT));
		centerSplitDock.setDividerLocation(300);
			
		rightSplitDock.addChildDock(horizontalSplitDock, new Position(Position.RIGHT));
		rightSplitDock.setMaximumSize(new Dimension(300, getHeight()));
		rightSplitDock.setDividerLocation(600);
		
		horizontalSplitDock.addChildDock(dock3, new Position(Position.BOTTOM));
		horizontalSplitDock.addChildDock(dock2, new Position(Position.TOP));
		horizontalSplitDock.setDividerLocation(380);
		
		dockModel.addRootDock("root", centerSplitDock, this);
		
		BorderDock borderDock = new BorderDock();
		borderDock.setCenterComponent(centerSplitDock);
		
		add(borderDock, BorderLayout.CENTER);
		
		//######KRAJ JAVADOKINGA ######
	}

	/**
	 * @return the dataBaseTableView
	 */
	public DataBaseTableView getDataBaseTableView()
	{
		return dataBaseTableView;
	}

	/**
	 * @param dataBaseTableView the dataBaseTableView to set
	 */
	public void setDataBaseTableView(DataBaseTableView dataBaseTableView)
	{
		this.dataBaseTableView = dataBaseTableView;
	}
}
