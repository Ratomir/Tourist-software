package model.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import observer.IObserver;
import observer.IObserverModel;
import view.renderers.TreeElement;
import view.renderers.TreeElement.Column;
import DBLayer.DBConnection;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

/**
 * Klasa DataBaseTableMOdel obezbjedjuje metode za manipulaciju podacima iz
 * tabele. Podatke tabele je moguce ucitati iz baze, izmjeniti, izbrisati, kao i
 * unijeti nove podatke. Moguce je pronaci podatke o kolonama/redovima na osnovu
 * proslijedjenih indeksa ili kodova kolone.
 * 
 * @author Ivana
 */
public class DataBaseTableModel extends AbstractTableModel implements IObserverModel
{
	private static final long serialVersionUID = 1L;

	private Vector<String> columnNames; // Nazivi kolona
	private Vector<Vector<Object>> rows; // Redovi tabele - podaci
	private Vector<IObserver> observers;
	private TreeElement.Table table; // Opis tabele

	private Connection conn = null;

	private ResultSet rs = null;
	CallableStatement procStmt = null;

	private DBConnection dbConnection = null;

	public DataBaseTableModel()
	{
		this.dbConnection = DBConnection.getInstance();
		observers = new Vector<IObserver>();
	}

	/**
	 * Metoda ucitava tabelu
	 * 
	 * @param table
	 *            - tabela koja se treba ucitati
	 * @return true- tabela ucitana, false - doslo do greske u ucitavanju tabele
	 */
	public boolean loadTable(TreeElement.Table table)
	{
		boolean retVal = false;
		try
		{
			this.table = table;

			conn = this.dbConnection.getConnection();

			String procedureName = table.getRetrieveSProc();
			if (procedureName.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Ne postoji 'retreive' procedura za izabranu tabelu.");
				return false;
			}

			String sql = "{ call " + procedureName + "() }";
			procStmt = conn.prepareCall(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE,
					SQLServerResultSet.CONCUR_READ_ONLY);
			rs = procStmt.executeQuery();

			rows = new Vector<Vector<Object>>();
			this.columnNames = new Vector<>();
			this.columnNames.add("#");
			for (TreeElement col : table.getAllElements())
			{
				this.columnNames.add(col.getName());
			}

			int j = 0;
			while (rs.next())
			{
				Vector<Object> row = new Vector<Object>();

				row.add(++j);
				for (int i = 1; i < columnNames.size(); i++)
				{
					row.add(rs.getString(i));
				}

				rows.add(row);
			}

			notifyAllObservers();
			retVal = true;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "DB Doslo je do greske!\nOpis: " + e.getMessage(), "Greska!",
					JOptionPane.ERROR_MESSAGE);
		}

		return retVal;
	}

	/**
	 * Metoda ucitava tabelu za generisanje izvjestaja.
	 * 
	 * @param table
	 *            - Tabela koja se ucitava
	 */
	public boolean loadTableForReport(TreeElement.Table table)
	{
		boolean retVal = false;
		try
		{
			this.table = table;

			conn = this.dbConnection.getConnection();

			String procedureName = table.getRetrieveSProc();
			if (procedureName.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Ne postoji 'retreive' procedura za izabranu tabelu.");
				return false;
			}

			String sql = "{ call " + procedureName + "() }";
			procStmt = conn.prepareCall(sql, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE,
					SQLServerResultSet.CONCUR_READ_ONLY);
			rs = procStmt.executeQuery();

			rows = new Vector<Vector<Object>>();
			this.columnNames = new Vector<>();
			// this.columnNames.add("#");
			for (TreeElement col : table.getAllElements())
			{
				this.columnNames.add(col.getName());
			}

			int j = 0;
			while (rs.next())
			{
				Vector<Object> row = new Vector<Object>();

				row.add(Integer.toString(++j));
				for (int i = 1; i < columnNames.size(); i++)
				{
					row.add(rs.getString(i));
				}

				rows.add(row);
				row.clear();
			}

			retVal = true;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "DB Doslo je do greske!\nOpis: " + e.getMessage(), "Greska!",
					JOptionPane.ERROR_MESSAGE);
		}

		return retVal;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}

	@Override
	public Class<?> getColumnClass(int col)
	{
		// Prva kolona - "#"
		if (col == 0)
			return String.class;

		// Posto je prva kolona "#" koja ne pripada tabeli u bazi, indeks se
		// oduzima za tu jednu poziciju.
		col--;
		TreeElement.Column column = (Column) table.getAllElements().get(col);

		switch (column.getType().toUpperCase())
		{
		case "VARCHAR":
		case "CHARACTER":
		case "LONGVARCHAR":
			return String.class;
			/*
			 * case "NUMERIC": case "DECIMAL": return BigDecimal.class; case
			 * "TINYINT": case "SMALLINT": case "INTEGER": return Integer.class;
			 * case "BIGINT": return Long.class; case "REAL": return
			 * Float.class; case "FLOAT": case "DOUBLE PRECISION": return
			 * Double.class; case "BINARY": case "VARBINARY": case
			 * "LONGVARBINARY": return byte[].class; case "DATE": return
			 * Date.class; case "TIME": return Time.class; case "TIMESTAMP":
			 * return Timestamp.class;
			 */
		default:
			return Object.class;
		}
	}

	public ResultSet getRS()
	{
		try
		{
			rs.beforeFirst();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void addObserver(IObserver observer)
	{
		observers.add(observer);
	}

	@Override
	public void notifyAllObservers()
	{
		for (IObserver observer : observers)
		{
			observer.update();
		}
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.size();
	}

	@Override
	public int getRowCount()
	{
		return rows.size();
	}

	@Override
	public Object getValueAt(int r, int c)
	{
		try
		{
			return rows.get(r).get(c);
		}
		catch (Exception e)
		{
		}

		return null;
	}

	/**
	 * Metoda omogucava pribavljanje opisa svih kolona.
	 * 
	 * @return vektor opisa kolona
	 */
	public Vector<Column> getColumns()
	{
		Vector<Column> cols = new Vector<TreeElement.Column>();

		for (TreeElement e : table.getAllElements())
		{
			cols.add((Column) e);
		}

		return cols;
	}

	/**
	 * Metoda omogucava pribavljanje podataka reda tabele na osnovu
	 * proslijedjenog indeksa reda u tabeli.
	 * 
	 * @param index
	 *            - indeks reda cije je podatke potrebno pribaviti
	 * @return mapa vrijednosti
	 */
	public HashMap<String, Object> getRow(int index)
	{
		if (index > getRowCount())
			return null;

		HashMap<String, Object> row = new HashMap<String, Object>();

		for (int i = 1; i < getColumnCount(); i++)
		{
			row.put(table.getAllElements().get(i - 1).getCode(), getValueAt(index, i));
		}

		return row;
	}

	/**
	 * Metoda vraca ime kolone.
	 */
	@Override
	public String getColumnName(int column)
	{
		return columnNames.get(column);
	}

	public ResultSet getResultSet()
	{
		return this.rs;
	}

	public String getTableName()
	{
		return table.getName();
	}

	/**
	 * @return the table
	 */
	public TreeElement.Table getTable()
	{
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(TreeElement.Table table)
	{
		this.table = table;
	}

	/**
	 * Metoda omogucava pronalazenje indeksa kolone na osnovu proslijedjenog
	 * koda kolone.
	 * 
	 * @param columnCode
	 *            - kod kolone ciji je indeks potrebno pronaci
	 * @return indeks kolone ako ona postoji i -1 ako indeks nije pronadjen
	 */
	public int getColumnIndex(String columnCode)
	{
		int i = -1;

		for (TreeElement e : table.getAllElements())
		{
			i++;
			if (e.getCode().equalsIgnoreCase(columnCode))
			{
				return i;
			}
		}

		return -1;
	}

	/**
	 * Metoda omogucava pronalazenje indeksa reda tabele na osnovu
	 * proslijedjenog koda tabele i podatka u koloni ciji je indeks reda
	 * potrebno pronaci.
	 * 
	 * @param columnCode
	 *            - kod kolone u kojoj se trazi podatak
	 * @param data
	 *            - podatak koji se trazi u koloni
	 * @return indeks reda - ako je podatak pronadjen i 0 - ako podatak nije
	 *         pronadjen
	 */
	public int getRowIndex(String columnCode, Object data)
	{
		int col = getColumnIndex(columnCode) + 1;

		if (col >= 0)
		{
			for (int i = 0; i < getRowCount(); i++)
			{
				if (data.equals(getValueAt(i, col)))
				{
					return i;
				}
			}
		}

		return 0;
	}

	/**
	 * Metoda omogucava brisanje reda iz tabele iz baze podataka.
	 * 
	 * @param values
	 *            - mapa vrijednosti
	 */
	public void delete(HashMap<String, Object> values)
	{
		try
		{
			int i;
			String procedureName = table.getDeleteSProc();
			if (procedureName.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Ne postoji 'delete' procedura za izabranu tabelu.");
				return;
			}

			String procedure = "{ call " + procedureName + "(";
			for (i = 0; i < values.size(); i++)
			{
				if (i < values.size() - 1)
				{
					procedure += "?,";
				}
				else
				{
					procedure += "?) }";
				}

			}

			procStmt = conn.prepareCall(procedure, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE,
					SQLServerResultSet.CONCUR_READ_ONLY);

			i = 0;
			for (Entry<String, Object> val : values.entrySet())
			{
				procStmt.setObject(++i, val.getValue());
			}

			procStmt.execute();
			this.loadTable(table);
			notifyAllObservers();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske u toku brisanja podataka.");
		}
	}

	/**
	 * Metoda omogucava upis novih vrijednosti u tabelu.
	 * 
	 * @param values
	 *            - mapa vrijednosti
	 */

	public void newItem(HashMap<String, Object> values)
	{
		try
		{
			int i;
			String procedureName = table.getCreateSProc();
			if (procedureName.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Ne postoji 'create' procedura za izabranu tabelu.");
				return;
			}

			String procedure = "{ call " + procedureName + "(";
			for (i = 0; i < values.size(); i++)
			{
				if (i < values.size() - 1)
				{
					procedure += "?,";
				}
				else
				{
					procedure += "?) }";
				}

			}

			procStmt = conn.prepareCall(procedure, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE,
					SQLServerResultSet.CONCUR_READ_ONLY);

			for (i = 0; i < table.getAllElements().size(); i++)
			{
				Object obj = values.get(table.getAllElements().get(i).getCode());

				procStmt.setObject(i + 1, obj);
			}

			procStmt.execute();
			this.loadTable(table);
			notifyAllObservers();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske u toku unosa podataka.");
		}
	}

	/**
	 * Metoda omogucava izmjenu podataka u tabeli na osnovu proslijedjenih
	 * vrijednosti.
	 * 
	 * @param values
	 *            - mapa vrijednosti
	 */
	public void updateTable(HashMap<String, Object> values)
	{
		try
		{
			int i;
			String procedureName = table.getUpdateSProc();
			if (procedureName.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Ne postoji 'update' procedura za izabranu tabelu.");
				return;
			}
			String procedure = "{ call " + procedureName + "(";
			for (i = 0; i < values.size(); i++)
			{
				if (i < values.size() - 1)
				{
					procedure += "?,";
				}
				else
				{
					procedure += "?) }";
				}

			}

			procStmt = conn.prepareCall(procedure, SQLServerResultSet.TYPE_SCROLL_INSENSITIVE,
					SQLServerResultSet.CONCUR_READ_ONLY);

			for (i = 0; i < table.getAllElements().size(); i++)
			{
				Object obj = values.get(table.getAllElements().get(i).getCode());
				procStmt.setObject(i + 1, obj);
			}

			procStmt.executeUpdate();
			this.loadTable(table);
			notifyAllObservers();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske u toku unosa podataka.");
		}
	}
}
