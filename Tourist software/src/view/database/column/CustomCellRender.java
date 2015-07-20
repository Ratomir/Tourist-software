package view.database.column;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Klassa prosiruje DefaultTreeCellRenderer klasu, uredjuje prikaz JTree komponente.
 * 
 * @author Ratomir
 *
 */
public class CustomCellRender extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metoda za uredjivanje JTreee komponente
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col)
	{
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		Color boja = row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE;
        c.setBackground(boja);
		
		setOpaque(true);
		setForeground(Color.black);
		removeAll();
		setFont(null);
		setText("");
		setHorizontalAlignment(SwingConstants.LEFT);
		
		if (value instanceof String) 
		{
			if(col == 0)
			{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			else
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			Font font = new Font(value.toString(), Font.PLAIN, 12);
			setText(value.toString());
			setFont(font);
		}
		else if (value instanceof Integer) 
		{
			if(col == 0)
			{
				setHorizontalAlignment(SwingConstants.LEFT);
			}
			else
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			
			Font font = new Font(value.toString(), Font.PLAIN, 12);
			setText(value.toString());
			setFont(font);
		}
		else if(value instanceof Boolean)
		{
			if(col == 1)
			{
				final JPanel panel = new JPanel(new BorderLayout(2,2));
				panel.setBackground(boja);
				final JCheckBox checkBox = new JCheckBox();
				checkBox.setOpaque(false);
				checkBox.setBorderPainted(false);
				checkBox.setBackground(boja);
				checkBox.setSelected(((Boolean)value).booleanValue());
				checkBox.setHorizontalAlignment(CENTER);
				checkBox.setVerticalAlignment(CENTER);
				panel.add(checkBox, BorderLayout.CENTER);
				return panel;
			}
		}
		
		return this;
	}
}
