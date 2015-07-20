package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import view.dialog.alotman.AlotmanDialog;
import view.dialog.putovanje.PutovanjeDialog;
import DBLayer.DBConnection;

/**
 * Klasa Menubar Controller sadrzi akcije i osluskivace koji se koriste za rad
 * sa menubar-om.
 *
 * @author Ivana
 * 
 */
public class MenubarController implements ActionListener
{
	private CommandController commandController = null;
	private JTable table = null;

	public MenubarController(CommandController controller)
	{
		this.commandController = controller;
	}
	/**
	 * Metoda prati akcije nad dugmadima i omogucava implementaciju funkcija 
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "load":
		{
			this.commandController.loadDatabase();
			break;
		}
		case "change":
		{
			this.commandController.loadDatabase();
			break;
		}

		case "firstRow":
		{
			this.commandController.first();
			break;
		}

		case "previousRow":
		{
			this.commandController.previous();
			break;
		}
		case "nextRow":
		{
			this.commandController.next();
			break;
		}
		case "lastRow":
		{
			this.commandController.last();
			break;
		}
		case "newRow":
		{
			this.commandController.newRow();
			break;
		}
		case "editRow":
		{
			this.commandController.edit(getTable());
			break;
		}
		case "deleteRow":
		{
			this.commandController.delete();
			break;
		}
		case "cancel":
		{
			this.commandController.cancel();
			break;
		}
		case "accept":
		{
			this.commandController.accept();
			break;
		}
		case "about":
		{
			this.commandController.about();
			break;
		}
		case "logOut":
		{
			this.commandController.logOut();
			break;
		}

		case "sr_RS":
		{
			break;
		}

		case "sr_BA":
		{
			break;
		}

		case "en_US":
		{
			break;
		}
		case "korisniciReport":
		{
			this.staticReport("Korisnici.jrxml");
			break;
		}
		case "putnikAranzmanReport":
		{
			this.staticReport("PutnikAranzman.jrxml");
			break;
		}
		case "sobeReport":
		{
			this.staticReport("soba.jrxml");
			break;
		}
		case "alotman":
		{
			AlotmanDialog alotman = new AlotmanDialog();
			break;
		}
		case "putovanje":
		{
			PutovanjeDialog alotman = new PutovanjeDialog();
			break;
		}

		}
	}
	/**
	 * Metofa omogucava generisanje statickog izvjestaja.
	 * 
	 * @param naziv - naziv statickog izvjestaja
	 */
	public void staticReport(String naziv)
	{
		try
		{
			JasperReport jr;
			JasperPrint jp;
			jr = JasperCompileManager.compileReport("staticreport/" + naziv);
			jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
			JasperViewer.viewReport(jp, false);
		}
		catch (JRException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table)
	{
		this.table = table;
	}
}
