/**
 * 
 */
package view.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Klasa MenuItem sluzi za definisanje menuItem-a pri cemu se parametri salju preko konstruktora.
 * @author Ratomir
 *
 */
public class MenuItem extends JMenuItem
{
	private static final long serialVersionUID = 1L;

	public MenuItem(int mnemKey, int mnemMask, ImageIcon icon, String toolTip, String text)
	{
		this.setText(text);		
		this.setIcon(icon);
		this.setToolTipText(toolTip);	
		this.setMnemonic(mnemKey);
		this.setAccelerator(KeyStroke.getKeyStroke(mnemKey, mnemMask));
	}
}
