package view.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.database.DataBaseExplorerModel;
import view.dialog.SelectionDialog;
import view.renderers.TreeElement.Column;
import view.renderers.TreeElement.Table;

/**
 * Klasa Component je apstraktna klasa koja sadrzi metode koje su zajednicke za razlicita polja
 * 
 * @author Ivana
 *
 */

public abstract class Component
{
	protected Column col;
	protected JLabel colName;
	protected JButton button = null;
	protected JPanel panel = new JPanel(new GridLayout(2, 1));
	protected java.awt.Component comp;
	private Component component = this;

	private DataBaseExplorerModel explorerModel = null;
	public Component(Column column, DataBaseExplorerModel explorerModel)
	{
		col = column;
		this.explorerModel = explorerModel;
	}

	protected void addComponents()
	{
		
		panel.setBorder(new TitledBorder(null, col.getName(), TitledBorder.CENTER, TitledBorder.TOP, null, null));
		JPanel pan = new JPanel(new BorderLayout());
		pan.add(comp, BorderLayout.CENTER);

		if (col.getRefTable() != null)
		{
			button = new JButton();
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(true);
			Image img = Toolkit.getDefaultToolkit().getImage("icons/icon32/File_in_folder_16.png");
			
			button.setActionCommand("component");
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			button.setToolTipText(col.getName());
			button.setIcon(new ImageIcon(img));
			button.addActionListener(new ComponentController(this.explorerModel));
			
			pan.add(button, BorderLayout.EAST);
		}
		
		JPanel imeKolene = new JPanel(new BorderLayout(2,2));
		if (col.isNullable())
		{
			JLabel bitno = new JLabel("*");
			bitno.setFont(bitno.getFont().deriveFont(18.0f));
			bitno.setForeground(Color.RED);
			imeKolene.add(bitno, BorderLayout.EAST);
		}
		
		panel.add(imeKolene);
		panel.add(pan);
	}

	public Column getColumn()
	{
		return col;
	}

	public String getCode()
	{
		return col.getCode();
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public abstract Object getData();

	public abstract void setData(Object data);

	public abstract boolean isValid();

	public abstract void clear();

	public void setEnable(boolean enable)
	{
		if (enable == true)
		{
			enable();
		}
		else
		{
			disable();
		}
	}

	private void enable()
	{
		if (button == null)
		{
			comp.setEnabled(true);
		}
		else
		{
			button.setEnabled(true);
		}
	}

	private void disable()
	{
		comp.setEnabled(false);

		if (button != null)
		{
			button.setEnabled(false);
		}
	}

	/**
	 * @return the component
	 */
	public Component getComponent()
	{
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(Component component)
	{
		this.component = component;
	}

	/**
	 * @return the col
	 */
	public Column getCol()
	{
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(Column col)
	{
		this.col = col;
	}

	/**
	 * @return the comp
	 */
	public java.awt.Component getComp()
	{
		return comp;
	}

	/**
	 * @param comp
	 *            the comp to set
	 */
	public void setComp(java.awt.Component comp)
	{
		this.comp = comp;
	}

	/**
	 * @return the explorerModel
	 */
	public DataBaseExplorerModel getExplorerModel()
	{
		return explorerModel;
	}

	/**
	 * @param explorerModel
	 *            the explorerModel to set
	 */
	public void setExplorerModel(DataBaseExplorerModel explorerModel)
	{
		this.explorerModel = explorerModel;
	}

	public class ComponentController implements ActionListener
	{
		private DataBaseExplorerModel modelTree;
		
		public ComponentController(DataBaseExplorerModel modelTree1111)
		{
			this.modelTree = modelTree1111;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Table table = this.modelTree.getTable(col.getRefTable());
			new SelectionDialog(table, col, getData(), component);
		}
	}
}
