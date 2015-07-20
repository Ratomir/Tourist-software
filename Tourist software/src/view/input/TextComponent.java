package view.input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.database.DataBaseExplorerModel;
import view.renderers.TreeElement.Column;

/**
 * Klasa TextCopmonent omogucava prikaz polja za unos teksta.
 * 
 * @author Ivana
 *
 */
public class TextComponent extends Component
{
	private DataBaseExplorerModel explorerModel = null;
	protected Column column = null;

	public TextComponent(Column column, DataBaseExplorerModel explorerModel)
	{
		super(column, explorerModel);
		this.column = column;
			
		JTextField textField = new JTextField(20)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// Unleash Your Creativity with Swing and the Java 2D API!
			// http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			@Override
			protected void paintComponent(Graphics g)
			{
				if (!isOpaque())
				{
					int w = getWidth();
					int h = getHeight();
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.setColor(Color.GRAY);
					g2.drawRoundRect(0, 0, w - 1, h - 1, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		textField.setOpaque(false);
		textField.setBackground(new Color(0, 0, 0, 0));
		textField.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		super.comp = textField;
		
		if(super.col.isPrimary())
		{
			this.setEnable(false);
		}
		
		addComponents();
	}

	@Override
	public Object getData()
	{
		if(column.getCode().equals("KO_SIFRA_KORISNIKA"))
		{
			JTextField textField = ((JTextField) comp);
			textField.setText(MD5(textField.getText()));
			return textField.getText();
		}
		return ((JTextField) comp).getText();
	}
	
	public String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}

	@Override
	public void setData(Object data)
	{
		// JLabel labela = new JLabel(column.getName());
		// Font defaultFont = labela.getFont();
		// FontMetrics fontMetrics = labela.getFontMetrics(defaultFont);
		// //...
		// int widthOfTitle = fontMetrics.stringWidth(column.getName());
		//
		// JLabel textDataLabel = new JLabel((String)data);
		// Font defaultFontData = textDataLabel.getFont();
		// FontMetrics fontMetricsData =
		// textDataLabel.getFontMetrics(defaultFontData);
		//
		// int widthOfData = fontMetricsData.stringWidth((String)data) +
		// comp.getWidth();
		//
		// if(widthOfTitle > widthOfData)
		// {
		// super.panel.setPreferredSize(new Dimension(widthOfTitle+20,
		// super.panel.getHeight()));
		// }

		((JTextField) comp).setText((String) data);
	}

	@Override
	public boolean isValid()
	{
		if (col.isNullable())
		{
			return ((((JTextField) comp).getText().length() > 0) ? (true) : (false));
		}

		return true;
	}

	@Override
	public void clear()
	{
		((JTextField) comp).setText("");
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
}
