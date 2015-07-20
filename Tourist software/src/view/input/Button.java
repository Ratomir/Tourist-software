package view.input;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
/**
 * 
 * @author Ratomir
 *
 */
public class Button extends JButton
{
	private static final long serialVersionUID = 1L;
	public final int SIZE = 34;
	
	public Button()
	{
		this.setSize(new Dimension(SIZE, SIZE));
		this.setPreferredSize(new Dimension(SIZE, SIZE));
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		Map<Key, Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //VALUE_ANTIALIAS_ON | VALUE_ANTIALIAS_OFF | VALUE_ANTIALIAS_DEFAULT
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); //VALUE_ALPHA_INTERPOLATION_QUALITY | VALUE_ALPHA_INTERPOLATION_SPEED | VALUE_ALPHA_INTERPOLATION_DEFAULT
		hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY); //VALUE_COLOR_RENDER_QUALITY | VALUE_COLOR_RENDER_SPEED | VALUE_COLOR_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE); //VALUE_DITHER_DISABLE | VALUE_DITHER_ENABLE | VALUE_DITHER_DEFAULT
		hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON); //VALUE_FRACTIONALMETRICS_ON | VALUE_FRACTIONALMETRICS_OFF | VALUE_FRACTIONALMETRICS_DEFAULT
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR); //VALUE_INTERPOLATION_BICUBIC | VALUE_INTERPOLATION_BILINEAR | VALUE_INTERPOLATION_NEAREST_NEIGHBOR
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); //VALUE_RENDER_QUALITY | VALUE_RENDER_SPEED | VALUE_RENDER_DEFAULT
		hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); //VALUE_STROKE_NORMALIZE | VALUE_STROKE_DEFAULT | VALUE_STROKE_PURE
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP); //VALUE_TEXT_ANTIALIAS_ON | VALUE_TEXT_ANTIALIAS_OFF | VALUE_TEXT_ANTIALIAS_DEFAULT | VALUE_TEXT_ANTIALIAS_GASP | VALUE_TEXT_ANTIALIAS_LCD_HRGB | VALUE_TEXT_ANTIALIAS_LCD_HBGR | VALUE_TEXT_ANTIALIAS_LCD_VRGB | VALUE_TEXT_ANTIALIAS_LCD_VBGR

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHints(hints);
		
		RoundRectangle2D.Float roundRect = new RoundRectangle2D.Float(1,1,SIZE-2,SIZE-2, 10, 10);
		g2d.setStroke(new BasicStroke(2f));
		
		RoundRectangle2D.Float rect = new RoundRectangle2D.Float(2,2,SIZE-4,SIZE-4,10,10);
		g2d.draw(roundRect);
		
//		g2d.setFont(getFont().deriveFont(20f));
//		FontMetrics fm = g2d.getFontMetrics();
//		JLabel labela1 = new JLabel("");
		
		g2d.draw(rect);
//		g2d.fill(rect);
		
//		g2d.setColor(Color.BLACK);
		
		this.setToolTipText("Find");
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon32/search.png");
		g2d.drawImage(img, 1, 1, SIZE-5, SIZE-5, this);
//		g2d.setFont(getFont().deriveFont(Font.BOLD, 23f));
//		g2d.drawString(labela1.getText(), SIZE/2 - fm.stringWidth(labela1.getText())/2-5, SIZE/2 + + fm.getDescent());
	}

}
