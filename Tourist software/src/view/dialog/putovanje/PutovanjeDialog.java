package view.dialog.putovanje;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
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

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import controller.login.DataBaseModel;

public class PutovanjeDialog extends JDialog implements WindowListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<String> nazivAgencije;
	private JLabel lblNewLabel_1;
	private JComboBox<String> imePutnika;
	private JLabel lblNewLabel_2;
	private JTextField destinacije;
	private JLabel lblNewLabel_3;
	private JTextField iznos;
	private JLabel lblNewLabel_4;
	private JTextField datumPolaska;
	private JLabel lblNewLabel_5;
	private JTextField periodTrajanja;

	private JLabel lblAranzmani;
	private JComboBox<String> aranzmani;

	private ArrayList<String> nazivAranzmana = new ArrayList<String>();
	private ArrayList<String> destinacijeLista = new ArrayList<String>();
	private ArrayList<String> iznosLista = new ArrayList<String>();
	private ArrayList<String> datumPolaskaLista = new ArrayList<String>();
	private ArrayList<String> periodTrajanjaLista = new ArrayList<String>();

	private JPanel mainPanel = null;
	private JPanel panelInput = null;
	private JPanel panelButton = null;

	private JButton accept = null;
	private JButton cancel = null;

	public PutovanjeDialog()
	{
		DataBaseModel model = new DataBaseModel();

		destinacijeLista = model.findNameDestinacije();
		iznosLista = model.findIznos();
		datumPolaskaLista = model.findDatumPolaska();
		periodTrajanjaLista = model.findPeriodTrajanja();

		mainPanel = new JPanel(new BorderLayout(5, 5));

		this.panelInput = new JPanel();
		panelInput.setLayout(new MigLayout("", "[][][][grow]", "[][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Naziv agencije:");
		panelInput.add(lblNewLabel, "cell 1 1");

		nazivAgencije = new JComboBox<String>();

		ArrayList<String> imena = model.findNameAgency();

		for (int i = 0; i < imena.size(); i++)
		{
			nazivAgencije.insertItemAt(imena.get(i), i);
		}
		nazivAgencije.setSelectedIndex(0);

		panelInput.add(nazivAgencije, "cell 3 1,growx");

		lblNewLabel_1 = new JLabel("Ime putnika:");
		panelInput.add(lblNewLabel_1, "cell 1 2");

		imePutnika = new JComboBox<String>();
		imena = model.findNamePutnika();

		for (int i = 0; i < imena.size(); i++)
		{
			imePutnika.insertItemAt(imena.get(i), i);
		}

		imePutnika.setSelectedIndex(0);
		panelInput.add(imePutnika, "cell 3 2,growx");

		lblAranzmani = new JLabel("Aranzmani:");
		panelInput.add(lblAranzmani, "cell 1 3");

		aranzmani = new JComboBox<String>();

		nazivAranzmana = model.findNameAranzman();

		for (int i = 0; i < nazivAranzmana.size(); i++)
		{
			aranzmani.insertItemAt(nazivAranzmana.get(i), i);
		}
		
		aranzmani.setSelectedIndex(0);
		panelInput.add(aranzmani, "cell 3 3,growx");
		aranzmani.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int numberOfSelected = aranzmani.getSelectedIndex();

				destinacije.setText(destinacijeLista.get(numberOfSelected));
				datumPolaska.setText(datumPolaskaLista.get(numberOfSelected));
				iznos.setText(iznosLista.get(numberOfSelected));
				periodTrajanja.setText(periodTrajanjaLista.get(numberOfSelected));
			}
		});
		lblNewLabel_2 = new JLabel("Destinacije:");
		panelInput.add(lblNewLabel_2, "cell 1 4");

		destinacije = new JTextField();
		destinacije.setText(destinacijeLista.get(0));
		destinacije.setEnabled(false);
		panelInput.add(destinacije, "cell 3 4,growx");

		lblNewLabel_3 = new JLabel("Iznos:");
		panelInput.add(lblNewLabel_3, "cell 1 5");

		iznos = new JTextField();
		iznos.setText(iznosLista.get(0));
		panelInput.add(iznos, "cell 3 5,growx");
		iznos.setColumns(10);
		iznos.setEnabled(false);

		lblNewLabel_4 = new JLabel("Datum polaska:");
		panelInput.add(lblNewLabel_4, "cell 1 6");

		datumPolaska = new JTextField();
		datumPolaska.setText(datumPolaskaLista.get(0));
		panelInput.add(datumPolaska, "cell 3 6,growx");
		datumPolaska.setColumns(10);
		datumPolaska.setEnabled(false);

		lblNewLabel_5 = new JLabel("Period trajanja:");
		panelInput.add(lblNewLabel_5, "cell 1 7");

		periodTrajanja = new JTextField();
		periodTrajanja.setText(periodTrajanjaLista.get(0));
		periodTrajanja.setEnabled(false);
		panelInput.add(periodTrajanja, "cell 3 7,growx");

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
			parameters.put("NAZIV", this.nazivAgencije.getSelectedItem().toString());
			parameters.put("IME", this.imePutnika.getSelectedItem().toString());
			parameters.put("PREZIME", " ");
			parameters.put("DESTINACIJE", this.destinacije.getText());
			parameters.put("IZNOS", this.iznos.getText());
			parameters.put("DATUM POLASKA", this.datumPolaska.getText());
			parameters.put("PERIOD TRAJANJA", this.periodTrajanja.getText());

			JasperReport jr = JasperCompileManager.compileReport("ugovori/Ugovor o organizovanju putovanja.jrxml");
			JasperPrint jp = JasperFillManager.fillReport(jr, parameters, new JREmptyDataSource());
			JasperViewer.viewReport(jp, false);
		}
		catch (JRException e)
		{
			e.printStackTrace();
		}
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

	@Override
	public void windowActivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
	}

}
