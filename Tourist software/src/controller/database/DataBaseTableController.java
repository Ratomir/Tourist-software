package controller.database;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import state.State;

/**
 * Klasa DataBaseTableController omogucava rukovanje dogadjajima nad tabelom.
 * Nakon dogadjaja poziva se metoda iz dijagrama stanja koja azurira statusnu
 * traku.
 * 
 * @author Ratomir
 *
 */
public class DataBaseTableController implements MouseListener, KeyListener
{

	@Override
	public void keyPressed(KeyEvent arg0)
	{
	}
	/**
	 * Prelaz strelicom tastature na prethodni ili naredni red.
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		if((e.getKeyCode()==KeyEvent.VK_DOWN) || (e.getKeyCode()==KeyEvent.VK_UP))
		{
			State.getState().updateStatus((JTable)e.getSource());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
	}
	/**
	 * Klik misa na red u tabeli
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getClickCount()==1)
		{
			State.getState().updateStatus((JTable)e.getSource());
		}	
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
	}

}
