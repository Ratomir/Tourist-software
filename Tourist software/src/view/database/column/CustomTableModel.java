package view.database.column;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import view.renderers.TreeElement;
/**
 * Klasa CustomTableModel predstavlja model za selektovanu kolonu.
 * @author Ratomir
 *
 */
public class CustomTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	
//	TreeElement.Column column = new TreeElement.Column(false, false, 0, 0, 0, 0);
	TreeElement.Column column = new TreeElement.Column();
	Vector<String> columnNames = new Vector<>();
	
	Object [][] rows = null;
	
	/**
	 * Konstruktor klase CustomTableModel
	 */
	public CustomTableModel()
	{
		columnNames.addElement("<html><body><p style=\"font-size: 1.25em; \">Properties</p></body></html>");
		columnNames.addElement("<html><body><p style=\"font-size: 1.25em; \">Value</p></body></html>");
		
		rows = new Object[][]
			{
				{ "Nullable",            column.isNullable()},
				{ "Primary",             column.isPrimary()},
				{ "Size",                column.getSize()},
				{ "Group",               column.getGroup()},
				{ "Type",                column.getType()},
				{ "Referent \n\rtable",  column.getRefTable()},
				{ "Referent \n\rcolumn", column.getRefColumn()}
			};
	}
	
	@Override
	public Class<?> getColumnClass(int col)
	{
		switch (col)
		{
		case 0:
		case 1:
			return String.class;
		default:
			return Object.class;
		}
	}
	
	/**
	 * Metoda broji kolone
	 */
	@Override
	public int getColumnCount()
	{
		return 2;
	}
	
	/**
	 * Metoda postavlja ime kolone
	 */
	@Override
	public String getColumnName(int column)
	{
		return columnNames.elementAt(column);
	}
	
	/**
	 * Metoda broji redove
	 */
	@Override
	public int getRowCount()
	{
		return 7;
	}

	/**
	 * Metoda vraca podatak iz polja koje se nalazi na presjeku navedenog reda i kolone
	 */
	@Override
	public Object getValueAt(int row, int column)
	{
		return rows[row][column];
	}
	
	/**
	 * Metoda postavlja podatak u polju koje se nalazi na presjeku navedenog reda i kolone
	 */
	@Override
	public void setValueAt(Object value, int row, int column)
	{
		rows[row][column] = value;
	}

	/**
	 * @return the column
	 */
	public TreeElement.Column getColumn()
	{
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(TreeElement.Column column)
	{
		this.column = column;
	}
	
}
