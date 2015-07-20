/**
 * 
 */
package view.database;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import model.database.DataBaseTableModel;
import observer.IObserver;
import view.renderers.TableRenderer;
import controller.database.DataBaseTableController;

/**
 * Klasa DataBaseTableView postavlja prozor za pregled tabela.
 *
 * @author Ratomir
 */
public class DataBaseTableView extends JScrollPane implements IObserver
{
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DataBaseTableModel model;
	private EventListener controller;

	/**
	 * Konstruktor klase DataBaseTableView
	 */
	public DataBaseTableView()
	{
		super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DataBaseTableModel model)
	{
		this.controller = new DataBaseTableController();
		this.model = model;
	}

	/**
	 * Metoda omogucava update podataka u tebelama
	 */
	@Override
	public void update()
	{
		if (model != null)
		{
			table = new JTable(model);
			table.setRowHeight(34);
			table.setDefaultRenderer(Object.class, new TableRenderer());

			table.setColumnSelectionAllowed(false);
			table.setRowSelectionAllowed(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setRowSelectionInterval(0, 0);
			table.setFillsViewportHeight(true);
			table.getTableHeader().setReorderingAllowed(false);
			
			//ENABLE SORTER
			table.setAutoCreateRowSorter(true);
			//KRAJ
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(20);
			int columnCount = columnModel.getColumnCount();
			int vrijednost = 0;
			JLabel lbl = new JLabel();
			FontMetrics fm;
			Font font;
			String columnName;
			for (int i = 1; i < columnCount; i++)
			{
				vrijednost = this.getMaxWidthRow(i);
				font = lbl.getFont();
				fm = lbl.getFontMetrics(font);
				columnName = this.table.getColumnName(i);
				if(vrijednost > fm.stringWidth(columnName))
				{
					columnModel.getColumn(i).setPreferredWidth(vrijednost+20);
				}
				else
				{
					columnModel.getColumn(i).setPreferredWidth(fm.stringWidth(this.table.getColumnName(i))+20);
				}
			}

			table.addMouseListener((MouseListener) controller);
			table.addKeyListener((KeyListener) controller);

			try
			{
				table.setRowSelectionInterval(0, 0);
			}
			catch (Exception e)
			{
			}

			this.setViewportView(table);
		}
	}
	
	/**
	 * Metoda podesava maksimalnu duzinu redova
	 * @param column
	 * @return
	 */
	public int getMaxWidthRow(int column)
	{
		int value = 0, temp = 0;
		JLabel data = new JLabel();
		int rows = this.table.getRowCount();
		FontMetrics fm;
		Font font;
		String columnValue;
		for(int i=0; i<rows; i++)
		{
			font = data.getFont();
			fm = data.getFontMetrics(font);
			columnValue = this.table.getValueAt(i, column).toString();
			temp = fm.stringWidth(columnValue);
			if(temp > value)
			{
				value = temp;
			}
		}
		
		return value;
	}

	/**
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}

}
