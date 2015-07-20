package view;

import model.database.DataBaseExplorerModel;
import view.input.CheckBoxComponent;
import view.input.Component;
import view.input.DataComponent;
import view.input.NumericComponent;
import view.input.TextComponent;
import view.renderers.TreeElement;

/**
 * Klasa za kreiranje polja za unos na osnovu proslijedjenog opisa kolone.
 * 
 * @author Ivana
 *
 */
public class ComponentFactory 
{
	private DataBaseExplorerModel explorerModel = null;
	
	public ComponentFactory() 
	{
	}
	
	/**
	 * Metoda kao parametar prihvata opis kolone, a vraca odgovarajuce polje za unos sa nazivom 
	 * kolone i dugmetom za pokretanje selekcionog dijaloga ukoliko je kolona u referenci sa
	 * drugom kolonom.
	 * 
	 * @param column - opis kolone
	 * @return formatirano polje za unos
	 */
	public Component getComponent(TreeElement.Column column)
	{
		String type = column.getType();
		
	    if( type.contains("int") || type.contains("float") || type.contains("numeric"))
	    {
	    	return new NumericComponent(column, this.explorerModel);
	    }
	    else if(type.contains("bit") || type.contains("smallint"))
		{
		    return new CheckBoxComponent(column, this.explorerModel);
		}
	    else if(type.contains("datetime"))
		{
		   return new DataComponent(column, this.explorerModel);
		}
	    
	    return new TextComponent(column, this.explorerModel);  
	}

	/**
	 * @return the explorerModel
	 */
	public DataBaseExplorerModel getExplorerModel()
	{
		return explorerModel;
	}

	/**
	 * @param explorerModel the explorerModel to set
	 */
	public void setExplorerModel(DataBaseExplorerModel explorerModel)
	{
		this.explorerModel = explorerModel;
	}

}
