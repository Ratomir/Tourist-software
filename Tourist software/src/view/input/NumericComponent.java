package view.input;

import javax.swing.JTextField;

import model.database.DataBaseExplorerModel;
import view.renderers.TreeElement.Column;
/**
 * Klasa NumericComponent omogucava prikaz polja za unos brojeva
 * 
 * @author Ivana
 *
 */
public class NumericComponent extends Component
{

	public NumericComponent(Column column, DataBaseExplorerModel explorerModel)
	{
		super(column, explorerModel);
		comp = new JTextField(7);
		addComponents();
	}

	@Override
	public Object getData() 
	{
		return ((JTextField)comp).getText();
	}

	@Override
	public void setData(Object data) 
	{
		((JTextField)comp).setText((String)data);
	}

	@Override
	public boolean isValid() 
	{
		String text = ((JTextField)comp).getText();
		
		if(col.getType().contains("int"))
		{
			try
			{	
				Integer.parseInt(text);
				return true;
			}
			catch(Exception e){}
		}
		else
		{
			try
			{
				Float.parseFloat(text);
				return true;
			}
			catch(Exception e){}
		}
		
		return false;
	}

	@Override
	public void clear()
	{
		((JTextField)comp).setText("");
		
	}

}
