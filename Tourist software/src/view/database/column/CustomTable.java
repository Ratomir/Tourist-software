package view.database.column;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
/**
 * Klasa prosiruje JPanel, uredjuje prikaz tabela.
 * 
 * @author Ratomir 
 *
 */
public class CustomTable extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JTable table = null;
	private CustomTableModel model = null;
	private JLabel nameColumn = null;
	
	/**
	 * Konstruktor klase CustomTable
	 */
	public CustomTable()
	{
		this.setLayout(new BorderLayout(2,2));
		
		JPanel namePanel = new JPanel(new BorderLayout(5,5));
		
		JLabel naziv = new JLabel("Name column:");
		naziv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		naziv.setHorizontalTextPosition(JLabel.LEFT);
		
		nameColumn = new JLabel("");
		nameColumn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameColumn.setHorizontalTextPosition(JLabel.RIGHT);
		
		namePanel.add(naziv, BorderLayout.WEST);
		namePanel.add(nameColumn, BorderLayout.EAST);
		
		namePanel.setPreferredSize(new Dimension(namePanel.getWidth(), 40));
		
		this.add(namePanel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBorder(new EmptyBorder(5,5,5,5));
		model = new CustomTableModel();
		
		table = new JTable(model);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		
		table.setDefaultRenderer(String.class, new CustomCellRender());
		table.setDefaultRenderer(Integer.class, new CustomCellRender());
		table.setDefaultRenderer(Boolean.class, new CustomCellRender());
		
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	/**
	 * Metoda za update-ovanje tabele
	 */
	public void updateTable()
	{
		for(int i=0; i<7; i++)
		{
			((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, 1);
		}
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
	 * @return the model
	 */
	public CustomTableModel getModel()
	{
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(CustomTableModel model)
	{
		this.model = model;
	}

	/**
	 * @return the nameColumn
	 */
	public JLabel getNameColumn()
	{
		return nameColumn;
	}

	/**
	 * @param nameColumn the nameColumn to set
	 */
	public void setNameColumn(String nameColumn)
	{
		this.nameColumn.setText(nameColumn);
	}
}
