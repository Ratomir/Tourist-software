/**
 * 
 */
package view.database;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import localization.Localization;
import model.database.DataBaseTableModel;
import net.miginfocom.swing.MigLayout;
import observer.IObserver;
import view.ComponentFactory;
import view.input.Component;
import view.renderers.TreeElement.Column;
import controller.database.DataBaseInputController;

/**
 * Klasa DataBaseInput postavlja  prozor za input polja
 * 
 * @author Ratomir
 */
public class DataBaseInput extends JPanel implements IObserver
{
	private static final long serialVersionUID = 1L;

	private DataBaseTableModel tableModel = null;
	private HashMap<String, Component> components = null;
	
	private Localization localization = null;
	
	private JButton btnAccept;
	private JButton btnCancel;
	
	private ComponentFactory componentFactory;
	
	private DataBaseInputController controller = null;
	
	/**
	 * Konstruktor klase DataBaseInput
	 * @param controller
	 * @param componentFactory
	 */
	public DataBaseInput(DataBaseInputController controller, ComponentFactory componentFactory)
	{
		this.controller = controller;
		this.componentFactory = componentFactory;
	}
	
	/**
	 * Metoda omogucava update podataka u bazi
	 */
	@Override
	public void update()
	{
		this.localization = Localization.getInstance(); 
		
		if(tableModel==null)
			return;
		
		removeAll();
		components = new HashMap<String, Component>();
		
		setLayout(new BorderLayout());
		
		JScrollPane panePanel = new JScrollPane();
		panePanel.setOpaque(false);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new MigLayout("wrap 5"));
		
		JPanel southPanel = new JPanel(new BorderLayout());
		
		for (Column col: tableModel.getColumns()) 
		{
			Component comp = this.componentFactory.getComponent(col);
			components.put(col.getCode(), comp);
			
			if(comp!=null)
			{
				centerPanel.add(comp.getPanel());	
			}
		}
		
		panePanel.setViewportView(centerPanel);
		
		btnAccept = new JButton("Accept");
		btnAccept.setToolTipText(this.localization.getString("accept"));
		btnAccept.setOpaque(false);
		btnAccept.setContentAreaFilled(false);
		btnAccept.setBorderPainted(true);
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon32/yes.png");
		btnAccept.setIcon(new ImageIcon(img));
		btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText(this.localization.getString("cancel"));
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnAccept.setBorderPainted(false);
		img = Toolkit.getDefaultToolkit().getImage("icons/icon32/no.png");
		btnCancel.setIcon(new ImageIcon(img));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAccept.setActionCommand("accept");
		btnCancel.setActionCommand("cancel");
		
		btnAccept.addActionListener(controller);
		btnCancel.addActionListener(controller);
		
		JPanel subPanel = new JPanel();
		subPanel.add(btnAccept);
		subPanel.add(btnCancel);

		southPanel.add(subPanel, BorderLayout.EAST);
		add(panePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * @return the tableModel
	 */
	public DataBaseTableModel getTableModel()
	{
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(DataBaseTableModel tableModel)
	{
		this.tableModel = tableModel;
	}
	
	/**
	 * Metoda setEnabled omogucava dugmad btnAccept i btnCancel
	 */
	public void setEnabled(boolean enable)
	{
		if(components==null)
			return;
		
		btnAccept.setEnabled(enable);
		btnCancel.setEnabled(enable);
					
		for (Entry<String, Component> comp: components.entrySet()) 
		{
			if(comp.getValue()!=null)
				comp.getValue().setEnable(enable);
		}
	}
	 /**
	  * 
	  * @param row
	  */
	public void setValues(HashMap<String, Object> row)
	{
		Component comp;
		for (Entry<String, Object> col: row.entrySet()) 
		{
			comp = components.get(col.getKey());
			if(comp!=null)
			{
				comp.setData(col.getValue());
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getValues()
	{
		HashMap<String, Object> values = new HashMap<String, Object>();
		for (Entry<String, Component> comp : components.entrySet()) 
		{
			if(comp.getValue()!=null)
				values.put(comp.getKey(), comp.getValue().getData());
		}
		return values;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getIdRows()
	{
		HashMap<String, Object> values = new HashMap<String, Object>();
		for (Entry<String, Component> comp : components.entrySet()) 
		{
			if((comp.getValue()!=null) && (comp.getValue().getColumn().isPrimary()))
				values.put(comp.getKey(), comp.getValue().getData());
		}
		return values;
	}
	
	/**
	 * Metoda brise podatke iz polja
	 */
	public void clearFields()
	{
		for (Entry<String, Component> comp : components.entrySet()) 
		{
			if(comp.getValue()!=null)
				comp.getValue().clear();
		}
	}

}
