package view.dialog.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JTable;

import state.State;
import state.interfaces.IState;
import view.database.DataBaseTableView;

public class ToolbarDialogController implements ActionListener
{
	private JTable table = null;
	private DataBaseTableView view = null;
	private DataBaseTableView tableView = null;
	
	public ToolbarDialogController()
	{
	}

	/**
	 * Metoda first omogucava izbor prvog reda u tabeli
	 */
	public void first()
	{
		IState state = State.getState();
		state.first(getTable());
		this.tableView.getVerticalScrollBar().setValue(0);
	}

	/**
	 * Metoda last omogucava izbor zadnjeg reda u tabeli
	 */
	public void last()
	{
		IState state = State.getState();
		state.last(getTable());

		JScrollBar vertical = this.tableView.getVerticalScrollBar();
		this.tableView.getVerticalScrollBar().setValue(vertical.getMaximum());
	}

	/**
	 * Metoda next omogucava izbor sljedceg reda u tabeli
	 */
	public void next()
	{
		IState state = State.getState();
		state.next(getTable());

		JScrollBar vertical = this.tableView.getVerticalScrollBar();
		this.tableView.getVerticalScrollBar().setValue(vertical.getValue() + 5);
	}

	/**
	 * Metoda previous omogucava izbor prethodnog reda u tabeli
	 */
	public void previous()
	{
		IState state = State.getState();
		state.previous(getTable());
	}

	/**
	 * Metoda osluskuje akcije na selekcionom dijalogu i poziva odgovarajuce funkcije
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "first":
		{
			this.first();
			break;
		}

		case "previous":
		{
			this.previous();
			break;
		}
		case "next":
		{
			this.next();
			break;
		}
		case "last":
		{
			this.last();
			break;
		}

		}
	}

	/**
	 * @return the table
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}

	/**
	 * @return the view
	 */
	public DataBaseTableView getView()
	{
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(DataBaseTableView view)
	{
		this.view = view;
	}

	/**
	 * @return the tableView
	 */
	public DataBaseTableView getTableView()
	{
		return tableView;
	}

	/**
	 * @param tableView the tableView to set
	 */
	public void setTableView(DataBaseTableView tableView)
	{
		this.tableView = tableView;
	}
}
