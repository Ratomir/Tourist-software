package view.dialog;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.database.DataBaseTableModel;
import state.SelectionDialogState;
import state.State;
import state.interfaces.IState;
import view.database.DataBaseTableView;
import view.dialog.controller.ToolbarDialogController;
import view.input.Component;
import view.renderers.TreeElement;
import view.renderers.TreeElement.Column;
/**
 * Klasa SelectionDialog omogucava prikaz dijaloga za selekciju.
 * 
 * @author Ratomir
 *
 */
public class SelectionDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private IState previousState;
	
	private void returnState()
	{
		State.setState(previousState);
		State.getState().updateStatus(null);
		
		dispose();
	}
	
	/**
	 * Konstruktor klase SelectionDialog
	 * @param table
	 * @param column
	 * @param data
	 * @param component
	 */
	public SelectionDialog(TreeElement.Table table, Column column, Object data, Component component) 
	{	
		final Column col = column;
		final Component comp = component;
		setLayout(new BorderLayout());
		setModal(true);
		
		ToolbarDialogController controller = new ToolbarDialogController();
		ToolbarDialog toolbar = new ToolbarDialog(controller);
		add(toolbar,BorderLayout.NORTH);
		
		final DataBaseTableView tableViewDialog = new DataBaseTableView();
		final DataBaseTableModel tableModelDialog = new DataBaseTableModel();
		tableViewDialog.setModel(tableModelDialog);
		tableModelDialog.addObserver(tableViewDialog);
		tableModelDialog.loadTable(table);
		controller.setTable(tableViewDialog.getTable());
		controller.setTableView(tableViewDialog);
		
		try
		{
			int rowIn = tableModelDialog.getRowIndex(column.getRefColumn(), data);
			tableViewDialog.getTable().setRowSelectionInterval(rowIn, rowIn);
		}
		catch(Exception e){}
		
		JPanel mainPanel = new JPanel();
		JPanel subPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JButton btnAccept = new JButton();
		btnAccept.setToolTipText("Accept");
		btnAccept.setOpaque(false);
		btnAccept.setContentAreaFilled(false);
		btnAccept.setBorderPainted(true);
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon32/yes.png");
		btnAccept.setIcon(new ImageIcon(img));
		btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton btnCancel = new JButton();
		btnCancel.setToolTipText("Cancel");
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnAccept.setBorderPainted(true);
		img = Toolkit.getDefaultToolkit().getImage("icons/icon32/no.png");
		btnCancel.setIcon(new ImageIcon(img));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnAccept.setText("Accept");
		btnCancel.setText("Cancel");
		
		
		subPanel.add(btnAccept);
		subPanel.add(btnCancel);
		mainPanel.add(subPanel,BorderLayout.EAST);
		
		add(tableViewDialog,BorderLayout.CENTER);
		add(mainPanel,BorderLayout.SOUTH);
		
		previousState = State.getState();
		State.setState(new SelectionDialogState());
		
		btnAccept.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{					
				int colIndex = tableModelDialog.getColumnIndex(col.getRefColumn())+1;
				int rowIndex = tableViewDialog.getTable().getSelectedRow();
				
				Object data = tableViewDialog.getTable().getValueAt(rowIndex, colIndex);
				comp.setData(data);
				
				returnState();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() 
		{	
			@Override
			public void actionPerformed(ActionEvent e) 
			{			
				returnState();
			}
		});
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				returnState();
			}
		});

		setIconImage(new ImageIcon("icons/logo64x64.png").getImage());
		setTitle(table.getName());
		setSize(600, 400);
		setPreferredSize(new Dimension(600,400));
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
