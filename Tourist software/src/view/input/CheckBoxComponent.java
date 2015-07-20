package view.input;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import model.database.DataBaseExplorerModel;
import view.renderers.TreeElement.Column;

/**
 * Klasa CheckBoxComponent omogucava prikaz polja za unos bool vrijednosti.
 * 
 * @author Ivana
 *
 */
public class CheckBoxComponent extends Component
{

	public CheckBoxComponent(Column column, DataBaseExplorerModel explorerModel) 
	{
		super(column, explorerModel);
		comp = new JCheckBox();
		comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JLabel labela = new JLabel(column.getName());
		Font        defaultFont = labela.getFont();
		FontMetrics fontMetrics = labela.getFontMetrics(defaultFont);
		//...
		int width = fontMetrics.stringWidth(column.getName());
		super.panel.setPreferredSize(new Dimension(width + 20, super.panel.getHeight()));
		addComponents();
	}

	@Override
	public Object getData()
	{
		Object obj = new Boolean((((JCheckBox)comp).isSelected()));
		return obj;
	}

	@Override
	public void setData(Object data)
	{

		if(data==null)
			return;
		
		boolean state = (data.toString().equals("1")) ? true : false;
		((JCheckBox)comp).setSelected(state);	
		
	}

	@Override
	public boolean isValid() 
	{
		return true;
	}

	@Override
	public void clear() 
	{
		((JCheckBox)comp).setSelected(false);
	}

}
