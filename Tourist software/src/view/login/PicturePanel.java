/**
 * 
 */
package view.login;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Klasa PicturePanel postavlja panel za sliku na formi za logovanje
 * @author Ratomir
 *
 */
public class PicturePanel extends JPanel
{
	Dimension dimension = new Dimension(271, 220);
	private static final long serialVersionUID = 1L;
	
	public PicturePanel()
	{
		setPreferredSize(dimension);
	}
	
	/**
	 * Metoda paint iscrtava sliku na panelu
	 */
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		Image img = Toolkit.getDefaultToolkit().getImage("icons/logo271x220.png");
		
		g2d.drawImage(img, this.getSize().width/2 - dimension.width/2, 0, dimension.width, dimension.height, this);
		g2d.finalize();
	}

}
