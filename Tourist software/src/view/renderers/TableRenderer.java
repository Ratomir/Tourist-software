package view.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Klasa TableRenderer omogucava formatiran prikaz celija tabele
 * 
 * @author Ratomir
 *
 */
public class TableRenderer extends DefaultTableCellRenderer
{

	private static final long serialVersionUID = 1L;

	/**
	 * Podesavanje izgleda tabela
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col)
	{
		setIcon(null);
		setHorizontalAlignment(JLabel.LEFT);
		setToolTipText(null);
		setFont(null);
		setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 3));
		setForeground(Color.black);
		
		// Podesavanje boje pozadine za neparne i parne redove
		Color backColor = (row%2==0) ? (new Color(51, 153, 255)) : (new Color(255, 250, 250));
		setBackground(backColor);
		
		if (value != null) 
		{
			setText(value.toString());
		
			// Podesavanje boje pozadine za oznacene redove
			if (isSelected) 
			{
				setBackground(new Color(135,209,220));
			}

			Rectangle cellRect = table.getCellRect(row, 0, true);
			Rectangle cellRect1 = table.getCellRect(row, table.getColumnCount(), true);
			cellRect.add(cellRect1);
		}
		
		// Fiksna sirina prve kolone - "#"
		if(col==0)
		{
//			table.getColumnModel().getColumn(col).setMaxWidth(40);
//			table.getColumnModel().getColumn(col).setMinWidth(40);
			setHorizontalAlignment(CENTER);
		}
		
		if(value instanceof Integer)
		{
			setHorizontalAlignment(CENTER);
			setHorizontalTextPosition(JLabel.CENTER);
		}
			
		
		return this;
	}
}
