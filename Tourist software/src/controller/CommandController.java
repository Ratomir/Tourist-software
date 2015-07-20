package controller;

import java.io.File;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.database.DataBaseTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import parser.XMLwriter;
import state.ConnectionState;
import state.EditState;
import state.NewState;
import state.State;
import state.interfaces.IState;
import view.View;
import view.database.DataBaseExplorer;
import view.database.DataBaseInput;
import view.database.DataBaseTableView;
import view.dialog.About;
import view.login.LoginForm;
import view.renderers.TreeElement.Column;
import DBLayer.DBConnection;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;

/**
 * Klasa sadrzi metode neophodne za izvrsavanje komandi koje su zajednicke za
 * menubar i toolbar.
 *
 * @author Ratomir
 * 
 */
public class CommandController
{
	private DataBaseExplorer dataBaseExplorer = null;
	private DataBaseTableView tableView = null;
	private DataBaseInput inputView = null;
	private About about = null;

	private JTable table = null;
	private JFrame application = null;

	public CommandController(DataBaseExplorer dataBaseExplorer)
	{
		this.dataBaseExplorer = dataBaseExplorer;
	}

	/**
	 * Metoda omogucava dijalog za interakciju sa korisnikom radi odabira
	 * zeljene datoteke. Po izboru datoteke vrsi se ucitavanje parametara.
	 */
	public void loadDatabase()
	{
		State.setState(new ConnectionState());

		String location = System.getProperty("user.dir") + "\\database files\\XML-Tourist-software.xml";

		File f = new File(View.locationDataBaseFile);

		if (f.exists() && !f.isDirectory())
			location = View.locationDataBaseFile;

		JFileChooser fileChooser = new JFileChooser(location);
		FileFilter fileFilter = new FileNameExtensionFilter("XML Files", "xml");

		fileChooser.addChoosableFileFilter(fileFilter);
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			View.locationDataBaseFile = selectedFile.getAbsolutePath();
			if (DBConnection.getInstance().findConectionParameters((selectedFile.getAbsolutePath())))
			{
				this.dataBaseExplorer.setTree(selectedFile.getAbsolutePath());
			}
		}
	}

	/**
	 * Metoda omogucava izbor prvog reda u tabeli.
	 */
	public void first()
	{
		IState state = State.getState();
		state.first(table);
		this.tableView.getVerticalScrollBar().setValue(0);
	}

	/**
	 * Metoda omogucava izbor posljednjeg reda u tabeli.
	 */
	public void last()
	{
		IState state = State.getState();
		state.last(table);

		JScrollBar vertical = this.tableView.getVerticalScrollBar();
		this.tableView.getVerticalScrollBar().setValue(vertical.getMaximum());
	}

	/**
	 * Metoda omogucava izbor sljedeceg reda u tabeli.
	 */
	public void next()
	{
		IState state = State.getState();
		state.next(table);

		JScrollBar vertical = this.tableView.getVerticalScrollBar();
		this.tableView.getVerticalScrollBar().setValue(vertical.getValue() + 5);
	}

	public void edit(JTable table)
	{
		State.setState(new EditState(table, this.inputView.getTableModel()));
	}

	/**
	 * Metoda omogucava izbor prethodnog reda u tabeli.
	 */
	public void previous()
	{
		IState state = State.getState();
		state.previous(table);
	}
	/**
	 * Metoda vrsi potvrdu svih promjena.
	 */
	public void accept()
	{
		IState state = State.getState();
		state.accept();
	}
	/**
	 * Metoda omogucava izlaz iz trenutnog stanja.
	 */
	public void cancel()
	{
		IState state = State.getState();
		state.cancel();
	}

	/**
	 * Metoda omogucava prikaz osnovnih informacija o aplikaciji i autorima.
	 * 
	 */
	public void about()
	{
		this.setAbout(new About());
	}

	/**
	 * Metoda omogucava odjavljivanje sa baze podataka i mogucnost ponovnog
	 * prijavljivanja.
	 */
	public void logOut()
	{
		XMLwriter xml = new XMLwriter();
		xml.writeWindowParametars(this.application);

		State.setState(null);

		application.setVisible(false); // you can't see me!
		application.dispose(); // Destroy the JFrame object
		new LoginForm();
	}

	/**
	 * Metoda omogucava brisanje izabranog reda.
	 */
	public void delete()
	{
		IState state = State.getState();
		state.deleteRow();
	}

	/**
	 * Metoda omogucava unošenje novog reda.
	 */
	public void newRow()
	{
		State.setState(new NewState(table));
		this.getInputView().clearFields();
	}

	/**
	 * Metoda za generisanje izvjestaja.
	 */
	public void report()
	{
		DataBaseTableModel model = new DataBaseTableModel();

		model.loadTableForReport(this.inputView.getTableModel().getTable());
		ResultSet resultSet = model.getResultSet();
		
		try
		{
			Style titleStyle = new Style("Title");
			titleStyle.setBorderBottom(Border.PEN_1_POINT());
			titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
			titleStyle.setPadding(15);
			titleStyle.setFont(Font.COMIC_SANS_BIG_BOLD);

			Date date = new Date();

			FastReportBuilder drb = new FastReportBuilder();
			drb.addAutoText("Univerzitet u Istocnom Sarajevu", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 300);
			drb.addAutoText("Elektrotehnicki fakultet", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
			drb.addAutoText(date.toString(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);

			drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_CENTER);

			DynamicReport dr = null;
			JRDataSource ds = null;

			int brojKolona = model.getColumnCount();
			for (int i = 0; i < brojKolona; i++)
			{
				Column c = model.getColumns().get(i);
				System.out.println(c.getCode());

				dr = drb.addColumn(c.getName(), c.getCode(), String.class.getName(), 50).build();
			}

			dr = drb.setTitleStyle(titleStyle).setTitle("Izvjestajni podsistem aplikacije")
					.setSubtitle("Tabela: " + model.getTableName()).setPrintBackgroundOnOddRows(true)
					.setFooterVariablesHeight(15).setUseFullPageWidth(true).build();
			resultSet.beforeFirst();
			resultSet.next();
			
			ds = new JRResultSetDataSource(resultSet);

			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
			System.out.println("Generisao sam report.");
			JasperViewer.viewReport(jp, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	/**
	 * @return the tableView
	 */
	public DataBaseTableView getTableView()
	{
		return tableView;
	}

	/**
	 * @param tableView
	 *            the tableView to set
	 */
	public void setTableView(DataBaseTableView tableView)
	{
		this.tableView = tableView;
	}

	/**
	 * @return the inputView
	 */
	public DataBaseInput getInputView()
	{
		return inputView;
	}

	/**
	 * @param inputView
	 *            the inputView to set
	 */
	public void setInputView(DataBaseInput inputView)
	{
		this.inputView = inputView;
	}

	/**
	 * @return the application
	 */
	public JFrame getApplication()
	{
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(JFrame application)
	{
		this.application = application;
	}

	/**
	 * @return the about
	 */
	public About getAbout()
	{
		return about;
	}

	/**
	 * @param about
	 *            the about to set
	 */
	public void setAbout(About about)
	{
		this.about = about;
	}
}
