package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * Klasa DB connectioni obezbjedjuje metodu za ucitavanje parametara za povezivanje na bazu.
 * Realizovana je i metoda za definisanje konekcije sa bazom.
 * 
 * @author Ratomir
 *
 */
public class DBConnection
{
	private String address;	
	private String type;	
	private String databaseName; 
	private String user;
	private String password;
	private int portNumber;
	
    private static DBConnection instance;
    
    public static DBConnection getInstance()
    {
    	if(instance==null)
    	{
    			instance = new DBConnection();
    	}
    	return instance;
    }
	
	public DBConnection()
	{
		
	}
	/**
	 * Metoda ucitava parametre za povezivanje sa bazom iz zadate datoteke.
	 * @param path- putanja do opisa baze podataka
	 * 
	 */
	public boolean findConectionParameters(String path)
	{
		try
    	{	
    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
    	    Document doc = docBuilder.parse(path);
    	    
    	    NodeList params = doc.getDocumentElement().getElementsByTagName("connection");
    	    
    	    for (int i = 0; i < params.getLength(); i++)
    	    {
	    		this.address = ((Element) params.item(i)).getAttribute("address");
	    		this.type = ((Element) params.item(i)).getAttribute("database_type");
	    		this.databaseName = ((Element) params.item(i)).getAttribute("database");
	    		this.user = ((Element) params.item(i)).getAttribute("username");
	    		this.password = ((Element) params.item(i)).getAttribute("password");
	    		String port = ((Element) params.item(i)).getAttribute("port");
	    		
	    		try
	    		{
	    			portNumber = Integer.parseInt(port);
	    		} 
	    		catch(Exception e) 
	    		{
	    			portNumber = 1433;
	    		}
    	    }
    	    	
    	} 
    	catch (Exception e)
    	{
    		JOptionPane.showMessageDialog(null, "Doslo je do greske!\nOpis: " + e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	return true;
	}
	/**
	 * Metoda ostvaruje konekciju sa bazom.
	 * @return objekat Connection za povezivanje sa bazom
	 */
	public Connection getConnection()
	{
		String connectionUrl = "jdbc:sqlserver://" +  address + ":" + portNumber + 
		         ";databaseName=" + databaseName + ";user=" + user + ";password=" + password;
   	
		Connection conn = null;
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionUrl);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * @return the databaseName
	 */
	public String getDatabaseName()
	{
		return databaseName;
	}
	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName)
	{
		this.databaseName = databaseName;
	}
	/**
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user)
	{
		this.user = user;
	}
	/**
	 * @return the portNumber
	 */
	public int getPortNumber()
	{
		return portNumber;
	}
	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(int portNumber)
	{
		this.portNumber = portNumber;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
