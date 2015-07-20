package view.menu;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import localization.Localization;
import controller.ItemController;
import controller.MenubarController;

/**
 * Klasa MenuBar sluzi za kreiranje MenuBar-a.
 * @author Ivana
 *
 */
public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = 1L;

	private JMenu file;
	private JMenu edit;
	private JMenu reportMenu;
	private JMenu ugovorMenu;
	private JMenu language;
	private JMenu help;

	private JMenuItem first;
	private JMenuItem next;
	private JMenuItem previous;
	private JMenuItem last;
	private JMenuItem newR;
	private JMenuItem editR;
	private JMenuItem deleteR;
	private JMenuItem accept;
	private JMenuItem cancel;
	
	private JMenuItem korisniciReport;
	private JMenuItem putnikAranzmanReport;
	private JMenuItem sobaReport;
	
	private JMenuItem ugovorPutovanje;
	private JMenuItem ugovorHotel;

	private MenubarController controller = null;
	private ItemController itemController = null;

	private Localization localization = null;

	/**
	 * Konstruktor za postavljanje MenuBar-a.
	 * @param controller
	 * @param lang parametar po kojem se prepoznaje koji je jezik zadnji koriscen
	 */
	public MenuBar(MenubarController controller, String lang)
	{
		this.controller = controller;
		this.itemController = new ItemController();

		this.localization = Localization.getInstance();

		file = new JMenu(this.localization.getString("menuFile"));
		this.localization.registerComponent("menuFile", file);
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem load = new JMenuItem(this.localization.getString("menuFile.loadDatabase"));
		this.localization.registerComponent("menuFile.loadDatabase", load);
		load.setIcon(new ImageIcon("icons/ikonice nove/base.png"));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		load.setActionCommand("load");
		load.addActionListener(this.controller);

		JMenuItem change = new JMenuItem(this.localization.getString("menuFile.switchDatabase"));
		this.localization.registerComponent("menuFile.switchDatabase", change);
		change.setIcon(new ImageIcon("icons/ikonice nove/switch.png"));
		change.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		change.setActionCommand("change");
		change.addActionListener(this.controller);

		/*JMenuItem exit = new JMenuItem(this.localization.getString("menuFile.exit"));
		this.localization.registerComponent("menuFile.exit", exit);
		exit.setIcon(new ImageIcon("icons/menu/Exit.png"));
		exit.setActionCommand("exit");
		exit.addActionListener(this.controller);*/
		
		JMenuItem logOut=new JMenuItem(this.localization.getString("menuFile.logOut")) ;
		this.localization.registerComponent("menuFile.logOut", logOut);
		logOut.setIcon(new ImageIcon("icons/ikonice nove/logout.png"));
		logOut.setActionCommand("logOut");
		logOut.addActionListener(this.controller);
		
		file.add(load);
		file.add(change);
		file.addSeparator();
		file.add(logOut);
		//file.add(exit);

		this.add(file);

		edit = new JMenu(this.localization.getString("menuEdit"));
		this.localization.registerComponent("menuEdit", edit);
		edit.setMnemonic(KeyEvent.VK_E);

		first = new JMenuItem(this.localization.getString("menuEdit.first"));
		this.localization.registerComponent("menuEdit.first", first);
		first.setIcon(new ImageIcon("icons/ikonice nove/first.png"));
		first.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
		first.setActionCommand("firstRow");
		first.addActionListener(this.controller);

		previous = new JMenuItem(this.localization.getString("menuEdit.previous"));
		this.localization.registerComponent("menuEdit.previous", previous);
		previous.setIcon(new ImageIcon("icons/ikonice nove/back.png"));
		previous.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
		previous.setActionCommand("previousRow");
		previous.addActionListener(this.controller);

		next = new JMenuItem(this.localization.getString("menuEdit.next"));
		this.localization.registerComponent("menuEdit.next", next);
		next.setIcon(new ImageIcon("icons/ikonice nove/next.png"));
		next.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		next.setActionCommand("nextRow");
		next.addActionListener(this.controller);

		last = new JMenuItem(this.localization.getString("menuEdit.last"));
		this.localization.registerComponent("menuEdit.last", last);
		last.setIcon(new ImageIcon("icons/ikonice nove/last.png"));
		last.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
		last.setActionCommand("lastRow");
		last.addActionListener(this.controller);

		newR = new JMenuItem(this.localization.getString("menuEdit.new"));
		this.localization.registerComponent("menuEdit.new", newR);
		newR.setIcon(new ImageIcon("icons/ikonice nove/new.png"));
		newR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK + Event.SHIFT_MASK));
		newR.setActionCommand("newRow");
		newR.addActionListener(this.controller);

		editR = new JMenuItem(this.localization.getString("menuEdit.edit"));
		this.localization.registerComponent("menuEdit.edit", editR);
		editR.setIcon(new ImageIcon("icons/ikonice nove/update.png"));
		editR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK + Event.SHIFT_MASK));
		editR.setActionCommand("editRow");
		editR.addActionListener(this.controller);

		deleteR = new JMenuItem(this.localization.getString("menuEdit.delete"));
		this.localization.registerComponent("menuEdit.delete", deleteR);
		deleteR.setIcon(new ImageIcon("icons/ikonice nove/delete.png"));
		deleteR.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK + Event.SHIFT_MASK));
		deleteR.setActionCommand("deleteRow");
		deleteR.addActionListener(this.controller);

		accept = new JMenuItem(this.localization.getString("menuEdit.accept"));
		this.localization.registerComponent("menuEdit.accept", accept);
		accept.setIcon(new ImageIcon("icons/ikonice nove/accept.png"));
		accept.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		accept.setActionCommand("accept");
		accept.addActionListener(this.controller);

		cancel = new JMenuItem(this.localization.getString("menuEdit.cancel"));
		this.localization.registerComponent("menuEdit.cancel", cancel);
		cancel.setIcon(new ImageIcon("icons/ikonice nove/cancel.png"));
		cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this.controller);

		edit.add(first);
		edit.add(previous);
		edit.add(next);
		edit.add(last);
		edit.addSeparator();
		edit.add(newR);
		edit.add(editR);
		edit.add(deleteR);
		edit.addSeparator();
		edit.add(accept);
		edit.add(cancel);

		this.add(edit);

		reportMenu = new JMenu(this.localization.getString("menuReport"));
		this.localization.registerComponent("menuReport", reportMenu);
		reportMenu.setMnemonic(KeyEvent.VK_R);
		
		korisniciReport = new JMenuItem("Korisnici report");
		korisniciReport.setActionCommand("korisniciReport");
		korisniciReport.setIcon(new ImageIcon("icons/ikonice nove/report1.png"));
		korisniciReport.addActionListener(this.controller);
		reportMenu.add(korisniciReport);
		
		putnikAranzmanReport = new JMenuItem("Putnici-aranžman report");
		putnikAranzmanReport.setActionCommand("putnikAranzmanReport");
		putnikAranzmanReport.setIcon(new ImageIcon("icons/ikonice nove/report2.png"));
		putnikAranzmanReport.addActionListener(this.controller);
		reportMenu.add(putnikAranzmanReport);
		
		sobaReport = new JMenuItem("Sobe report");
		sobaReport.setActionCommand("sobeReport");
		sobaReport.setIcon(new ImageIcon("icons/ikonice nove/report3.png"));
		sobaReport.addActionListener(this.controller);
		reportMenu.add(sobaReport);

		this.add(reportMenu);
		
		//UGOVORI
		
		ugovorMenu = new JMenu(this.localization.getString("menuAgreement"));
		this.localization.registerComponent("menuAgreement", ugovorMenu);
		
		ugovorHotel = new JMenuItem("Ugovor o alotmanu");
		ugovorHotel.setActionCommand("alotman");
		ugovorHotel.setIcon(new ImageIcon("icons/ikonice nove/contract.png"));
		ugovorHotel.addActionListener(this.controller);
		ugovorMenu.add(ugovorHotel);
		
		ugovorPutovanje = new JMenuItem("Ugovor o putovanju");
		ugovorPutovanje.setActionCommand("putovanje");
		ugovorPutovanje.setIcon(new ImageIcon("icons/ikonice nove/contract.png"));
		ugovorPutovanje.addActionListener(this.controller);
		ugovorMenu.add(ugovorPutovanje);
		
		this.add(ugovorMenu);
		
		
		//########

		language = new JMenu(this.localization.getString("language"));
		this.localization.registerComponent("language", language);

		ButtonGroup checkGroup = new ButtonGroup();

		JMenuItem srpskiCirilica = new JCheckBoxMenuItem(this.localization.getString("language.sr_RS"));
		this.localization.registerComponent("language.sr_RS", srpskiCirilica);
		srpskiCirilica.setIcon(new ImageIcon("icons/menu/SerbiaFlag.png"));
		srpskiCirilica.setActionCommand("sr_RS");
		checkGroup.add(srpskiCirilica);
		language.add(srpskiCirilica);
		srpskiCirilica.addItemListener(itemController);

		JMenuItem srpskiLatinica = new JCheckBoxMenuItem(this.localization.getString("language.sr_BA"));
		this.localization.registerComponent("language.sr_BA", srpskiLatinica);
		srpskiLatinica.setIcon(new ImageIcon("icons/menu/SerbiaFlag.png"));
		srpskiLatinica.setActionCommand("sr_BA");
		checkGroup.add(srpskiLatinica);
		language.add(srpskiLatinica);
		srpskiLatinica.addItemListener(itemController);

		JMenuItem engleski = new JCheckBoxMenuItem(this.localization.getString("language.en_US"));
		this.localization.registerComponent("language.en_US", engleski);
		engleski.setIcon(new ImageIcon("icons/menu/UnitedKingdomFlag.png"));
		engleski.setActionCommand("en_US");
		checkGroup.add(engleski);
		language.add(engleski);
		engleski.addItemListener(itemController);

		if (lang == "en_US")
		{
			engleski.setSelected(true);
		}
		else if (lang == "sr_BA")
		{
			srpskiLatinica.setSelected(true);
		}
		else
		{
			srpskiCirilica.setSelected(true);
		}

		language.add(srpskiCirilica);
		language.add(srpskiLatinica);
		language.add(engleski);

		this.add(language);

		help = new JMenu(this.localization.getString("menuHelp"));
		this.localization.registerComponent("menuHelp", help);
		help.setMnemonic(KeyEvent.VK_H);

		JMenuItem about = new JMenuItem(this.localization.getString("menuHelp.about"));
		this.localization.registerComponent("menuHelp.about", about);
		about.setIcon(new ImageIcon("icons/ikonice nove/info.png"));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		about.setActionCommand("about");
		about.addActionListener(this.controller);

		help.add(about);
		this.add(help);
	}

	/**
	 * Metoda enableEditMenu omogucava opcije Edit menija.
	 */
	public void enableEditMenu()
	{
		first.setEnabled(true);
		next.setEnabled(true);
		previous.setEnabled(true);
		last.setEnabled(true);
		newR.setEnabled(true);
		editR.setEnabled(true);
		deleteR.setEnabled(true);
		accept.setEnabled(true);
		cancel.setEnabled(true);
		reportMenu.setEnabled(true);
	}

	/**
	 * Metoda disableEditMenu onemogucava opcije Edit menija.
	 */
	public void disableEditMenu()
	{
		first.setEnabled(false);
		next.setEnabled(false);
		previous.setEnabled(false);
		last.setEnabled(false);
		newR.setEnabled(false);
		editR.setEnabled(false);
		deleteR.setEnabled(false);
		accept.setEnabled(false);
		cancel.setEnabled(false);
		reportMenu.setEnabled(false);
	}

	/**
	 * @return the controller
	 */
	public MenubarController getController()
	{
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(MenubarController controller)
	{
		this.controller = controller;
	}

}
