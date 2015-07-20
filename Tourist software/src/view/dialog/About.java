package view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import localization.Localization;
import parser.XMLreader;
/**
 * Klasa koja nasledjuje JDialog i implementira 
 * frame za informacije o programu.
 * 
 * @author Jelena 
 *
 */
public class About extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel = null;
	private JPanel downPanel = null;
	
	private Localization localization = null;
	
	/**
	 * Konstruktor klase About.
	 */
	public About()
	{
		this.setLookAndFeel();
		setModal(true);
		
		XMLreader reader = new XMLreader();
		reader.readLocalization();
		this.localization = Localization.getInstance();
		
		this.mainPanel = new JPanel(new BorderLayout(5,5));
		JLabel lblAnimacija = new JLabel(new ImageIcon("icons/logo271x220.png"));
		
		this.mainPanel.add(lblAnimacija, BorderLayout.NORTH);
		
		this.downPanel = new JPanel();
		
		JLabel text = new JLabel();
		text.setFont(new Font("Aparajita", Font.PLAIN, 23));
		text.setText("<html><center>"
				+ "Projekat je nasto na <br>Elektrotehnickom fakultetu Istocno Sarajevo"
				+ "<br>u okviru predmeta <br>"
				+ "Projektovanje informacionih sistema.</center></html>");
		
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setHorizontalTextPosition(JLabel.CENTER);
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		this.downPanel.add(text);
		
		JPanel imena = new JPanel();
		
		JLabel ivana = new JLabel("Ivana Mumovic");
		ivana.setFont(new Font("Andalus", Font.PLAIN, 23));
		imena.add(ivana);
		
		JLabel jelena = new JLabel("Jelena Jovovic");
		jelena.setFont(new Font("Andalus", Font.PLAIN, 23));
		imena.add(jelena);
		
		JLabel ratomir = new JLabel("Ratomir Vukadin");
		ratomir.setFont(new Font("Andalus", Font.PLAIN, 23));
		imena.add(ratomir);
		
		this.downPanel.add(imena);
		
		this.mainPanel.add(downPanel, BorderLayout.CENTER);
		
		this.add(mainPanel);
		
		Dimension dimensionWindow = new Dimension(500, 420);

		setTitle(this.localization.getString("menuHelp.about"));
		setIconImage(new ImageIcon("icons/logo64x64.png").getImage());
		
		setSize(dimensionWindow);
		setPreferredSize(dimensionWindow);
		setMinimumSize(dimensionWindow);
		
		Toolkit tkDimension = Toolkit.getDefaultToolkit();
		Dimension dimensionScrean = tkDimension.getScreenSize();

		setLocation(dimensionScrean.width / 2 - dimensionWindow.width / 2,
					dimensionScrean.height / 2 - dimensionWindow.height / 2);
		
		setLocationRelativeTo(null);
		setResizable(false);
		this.pack();
		setVisible(true);
	}
	
	/**
	 * Postavljanje look and feel-a aplikacije
	 */
	public void setLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	}
	
	/**
	 * @return the mainPanel
	 */
	public JPanel getMainPanel()
	{
		return mainPanel;
	}
	/**
	 * @param mainPanel the mainPanel to set
	 */
	public void setMainPanel(JPanel mainPanel)
	{
		this.mainPanel = mainPanel;
	}
}
