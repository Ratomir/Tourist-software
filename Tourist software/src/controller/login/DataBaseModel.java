package controller.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import dbManipulation.DBManipulation;
import dbType.DatabaseType;

public class DataBaseModel
{
	DBManipulation DBM = null;
	Connection conn = null;

	CallableStatement cstmt = null;

	ResultSet rs = null;
	ResultSetMetaData rsmd = null;

	Vector<Object> data = new Vector<Object>();

	public DataBaseModel()
	{
		DBM = new DBManipulation(DatabaseType.MsSQL_JDBC, "78.28.157.8", "1433", "PIS2015", "EtfPIS2015G4",
				"EtfPIS2015G49372");
		conn = DBM.getDbConnection();

		System.out.println("Konekcija ostvarena: " + DBM.isConnectionOpen());
	}
	/**
	 * Metoda ispituje korisnicke podatke.
	 * 
	 * @param username - korisnicko ime
	 * @param password - sifra
	 */
	public boolean findUser(String username, String password)
	{
		int numberOfData = 0;
		data.clear();
		password = MD5(password);

		try
		{
			cstmt = conn.prepareCall("{ call SelectKorisniciWhereUnPwd(?,?) }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				if (numberOfData++ > 0)
					return false;

				Vector<Object> tmpData = new Vector<Object>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					tmpData.addElement(rs.getObject(i));
				}

				data.addElement(tmpData);

				tmpData = null;

				Object objectData = data.get(0);

				if (objectData != null)
				{
					String obj = data.elementAt(0).toString();
					obj = obj.replaceAll("\\s+", "");
					obj = obj.substring(1, obj.length() - 1);
					String[] lstString = obj.split(",");

					if (lstString[1].equals(username) && lstString[2].equals(password))
						return true;
				}
			}

			return false;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<String> findNameAgency()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectPravnoLice() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(4).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findNamePutnika()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectPutnik() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(2).toString() + " " + rs.getObject(3).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findNameHotel()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectHotel() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(9).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findNameNaseljenoMjesto()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectNaseljenoMjesto() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(2).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findNameAranzman()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectAranzman() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(7).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findNameDestinacije()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectAranzman() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(15).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findIznos()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectAranzman() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(12).toString());
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findDatumPolaska()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectAranzman() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(8).toString());
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public ArrayList<String> findPeriodTrajanja()
	{
		ArrayList<String> imena = new ArrayList<String>();
		try
		{
			cstmt = conn.prepareCall("{ call SelectAranzman() }",
					SQLServerResultSet.TYPE_SCROLL_INSENSITIVE, SQLServerResultSet.CONCUR_READ_ONLY);
			rs = cstmt.executeQuery();

			rsmd = rs.getMetaData();

			while (rs.next())
			{
				imena.add(rs.getObject(9).toString());
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return imena;
	}
	
	public String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}

	public ResultSet getResultSet()
	{
		return rs;
	}

	public void setResultSet(ResultSet resultSet)
	{
		this.rs = resultSet;
	}

	public ResultSetMetaData getResultSetMetaData()
	{
		return rsmd;
	}

	public void setResultSetMetaData(ResultSetMetaData resultSetMetaData)
	{
		this.rsmd = resultSetMetaData;
	}
}
