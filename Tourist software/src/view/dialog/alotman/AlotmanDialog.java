package view.dialog.alotman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import controller.login.DataBaseModel;

public class AlotmanDialog extends JDialog implements ActionListener, WindowListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<String> nazivAgencije;
	private JLabel lblNewLabel_1;
	private JComboBox<String> imeHotela;
	private JLabel lblNewLabel_2;
	private JTextField cijenaPunogPanisona;
	private JLabel lblNewLabel_3;
	private JTextField cijenaPoluPanisiona;
	private JLabel lblNewLabel_4;
	private JTextField nocenjeSaDoruckom;
	private JLabel lblNewLabel_5;
	private JComboBox<String> naseljenoMjesto;

	private JPanel mainPanel = null;
	private JPanel panelInput = null;
	private JPanel panelButton = null;

	private JButton accept = null;
	private JButton cancel = null;

	public AlotmanDialog()
	{
		mainPanel = new JPanel(new BorderLayout(5, 5));

		this.panelInput = new JPanel();
		panelInput.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Naziv agencije:");
		panelInput.add(lblNewLabel, "cell 1 1");

		nazivAgencije = new JComboBox<String>();
		DataBaseModel model = new DataBaseModel();
		ArrayList<String> imena = model.findNameAgency();

		for (int i = 0; i < imena.size(); i++)
		{
			nazivAgencije.insertItemAt(imena.get(i), i);
		}

		panelInput.add(nazivAgencije, "cell 3 1,growx");

		lblNewLabel_1 = new JLabel("Ime hotela:");
		panelInput.add(lblNewLabel_1, "cell 1 2");

		imeHotela = new JComboBox<String>();
		imena = model.findNameHotel();

		for (int i = 0; i < imena.size(); i++)
		{
			imeHotela.insertItemAt(imena.get(i), i);
		}

		panelInput.add(imeHotela, "cell 3 2,growx");

		lblNewLabel_2 = new JLabel("Cijena punog pansiona:");
		panelInput.add(lblNewLabel_2, "cell 1 3");

		cijenaPunogPanisona = new JTextField()
		{
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
		panelInput.add(cijenaPunogPanisona, "cell 3 3,growx");
		cijenaPunogPanisona.setColumns(10);

		lblNewLabel_3 = new JLabel("Cijena polu pansiona:");
		panelInput.add(lblNewLabel_3, "cell 1 4");

		cijenaPoluPanisiona = new JTextField()
		{
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
		panelInput.add(cijenaPoluPanisiona, "cell 3 4,growx");
		cijenaPoluPanisiona.setColumns(10);

		lblNewLabel_4 = new JLabel("Cijena no\u0107enja sa doru\u010Dkom:");
		panelInput.add(lblNewLabel_4, "cell 1 5");

		nocenjeSaDoruckom = new JTextField()
		{
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
		panelInput.add(nocenjeSaDoruckom, "cell 3 5,growx");
		nocenjeSaDoruckom.setColumns(10);

		lblNewLabel_5 = new JLabel("Naseljeno mjesto:");
		panelInput.add(lblNewLabel_5, "cell 1 6");

		naseljenoMjesto = new JComboBox<String>();
		imena = model.findNameNaseljenoMjesto();

		for (int i = 0; i < imena.size(); i++)
		{
			naseljenoMjesto.insertItemAt(imena.get(i), i);
		}

		panelInput.add(naseljenoMjesto, "cell 3 6,growx");

		accept = new JButton("Accept");
		accept.setToolTipText("Accept");
		accept.setOpaque(false);
		accept.setContentAreaFilled(false);
		accept.setBorderPainted(true);
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon32/yes.png");
		accept.setIcon(new ImageIcon(img));
		accept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		accept.addActionListener(this);

		cancel = new JButton("Cancel");
		cancel.setToolTipText("Cancel");
		cancel.setOpaque(false);
		cancel.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		img = Toolkit.getDefaultToolkit().getImage("icons/icon32/no.png");
		cancel.setIcon(new ImageIcon(img));
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancel.addActionListener(this);

		accept.setActionCommand("accept");
		cancel.setActionCommand("cancel");

		this.panelButton = new JPanel(new BorderLayout(5, 5));
		this.panelButton.add(accept, BorderLayout.EAST);
		this.panelButton.add(cancel, BorderLayout.WEST);

		this.mainPanel.add(this.panelInput, BorderLayout.CENTER);
		this.mainPanel.add(this.panelButton, BorderLayout.SOUTH);

		this.add(mainPanel);

		setSize(600, 400);
		setMinimumSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setModal(true);

		setIconImage(new ImageIcon("icons/logo64x64.png").getImage());

		pack();
		setVisible(true);
	}

	public void generisiIzvjestaj()
	{
		try
		{
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("NAZIV AGENCIJE", this.nazivAgencije.getSelectedItem().toString());
			parameters.put("NAZIV HOTELA", this.imeHotela.getSelectedItem().toString());
			parameters.put("CIJENA PUN PANSION", this.cijenaPunogPanisona.getText());
			parameters.put("CIJENA POLUPANSION", this.cijenaPoluPanisiona.getText());
			parameters.put("CIJENA SA DORUCKOM", this.nocenjeSaDoruckom.getText());
			parameters.put("NASELJENO MJESTO", this.naseljenoMjesto.getSelectedItem().toString());

			JasperReport jr = JasperCompileManager
					.compileReport("ugovori/Ugovor o angazovanju ugostiteljskih kapaciteta.jrxml");
			JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "accept":
		{
			this.generisiIzvjestaj();
		}
		case "cancel":
		{
			dispose();
		}

		}
	}

}
