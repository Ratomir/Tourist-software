package state.interfaces;

import javax.swing.JTable;
/**
 * Interfejs IState definise metode koje je potrebno realizovati u svim stanjima.
 * 
 * @author Jelena
 *
 */
public interface IState
{
	public void first(JTable table);
	public void next(JTable table);
	public void previous(JTable table);
	public void last(JTable table);
	public void updateStatus(JTable table);
	public void newRow();
	public void editRow();
	public void deleteRow();
	public void accept();
	public void cancel();
}
