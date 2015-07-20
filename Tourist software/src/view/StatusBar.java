/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import localization.Localization;
import model.database.DataBaseTableModel;
import observer.IObserver;

/**
 * Klasa StatusBar predstavlja statusnu liniju aplikacije.
 * @author Ratomir
 *
 */
public class StatusBar extends JPanel implements IObserver
{
	private static final long serialVersionUID = 1L;

	public JLabel selectedTable, state, currentRow;
	
	private DataBaseTableModel model = null;
	
	private Localization localization = null;
	
	/**
	 * Konstruktor statusne linije.
	 */
	public StatusBar()
	{
		this.localization = Localization.getInstance();
		
		setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), this.localization.getString("statusBar"), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout());
		
		JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel statePanel = new JPanel(new GridLayout());
		state = new JLabel(this.localization.getString("state.ready"));
		this.localization.registerComponent("state.ready", statePanel);
		statePanel.add(state);
		left.add(statePanel);
		
		JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel element = new JPanel(new GridLayout());
		selectedTable = new JLabel(this.localization.getString("selectedTable.none"));
		this.localization.registerComponent("selectedTable.none", element);
		element.add(selectedTable);
		center.add(element);
		
		JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel row = new JPanel(new GridLayout());
		currentRow = new JLabel(this.localization.getString("currentRow.none"));
		this.localization.registerComponent("currentRow.none", row);
		row.add(currentRow);
		right.add(currentRow);
		
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
	}
	
	/**
	 * @return the model
	 */
	public DataBaseTableModel getModel()
	{
		return model;
	}
	
	public void setTableModel(DataBaseTableModel tableModel)
    {
    	this.model = tableModel;
    }

	@Override
	public void update()
	{
		this.localization = Localization.getInstance();
		
		this.selectedTable.setText(this.localization.getString("state") + model.getTableName());
		this.currentRow.setText(this.localization.getString("currentRow") + 1 + "/" + model.getRowCount());	
	}

	/**
	 * @return the selectedTable
	 */
	public JLabel getSelectedTable()
	{
		return selectedTable;
	}

	/**
	 * @param selectedTable the selectedTable to set
	 */
	public void setSelectedTable(String selectedTable)
	{
		this.selectedTable.setText(selectedTable);
	}
	
	public void setIcon()
	{
		ImageIcon icon = new ImageIcon("icons/loader.gif",
	              "a pretty but meaningless splat");
		this.selectedTable.setIcon(icon);
	}

	/**
	 * @return the state
	 */
	public JLabel getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state.setText(state);
	}

	/**
	 * @return the currentRow
	 */
	public JLabel getCurrentRow()
	{
		return currentRow;
	}

	/**
	 * @param currentRow the currentRow to set
	 */
	public void setCurrentRow(String currentRow)
	{
		this.currentRow.setText(currentRow);
	}

	

}
