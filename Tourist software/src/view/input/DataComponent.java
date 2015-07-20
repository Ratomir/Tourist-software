package view.input;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFormattedTextField;

import org.freixas.jcalendar.JCalendarCombo;

import model.database.DataBaseExplorerModel;
import view.renderers.TreeElement.Column;

/**
 * Klasa DataComponent je klasa za prikaz polja za unos datuma.
 * 
 * @author Ivana
 *
 */
public class DataComponent extends Component
{

	/**
	 * Konstruktor klase DataComponent
	 * 
	 * @param column
	 * @param explorerModel
	 */
	public DataComponent(Column column, DataBaseExplorerModel explorerModel)
	{
		super(column, explorerModel);
		// comp = new JFormattedTextField(DateFormat.getDateTimeInstance());
		JCalendarCombo calendar = new JCalendarCombo();
		calendar.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
		// calendar.setLocale(new Locale("bs"));
		calendar.setPreferredSize(new Dimension(165, 22));
		calendar.setEnabled(false);

		comp = calendar;

		addComponents();
	}

	@Override
	public Object getData()
	{
		JCalendarCombo calendar = (JCalendarCombo) comp;
		int godina = (new Date().getYear() > 2000) ? (2000 + calendar.getDate().getYear()) : (1900 + calendar.getDate()
				.getYear());
		String tekst = godina + "-" + (calendar.getDate().getMonth() + 1) + "-" + calendar.getDate().getDate() + " 00:00:00.0";

		JFormattedTextField formatedText = new JFormattedTextField();
		formatedText.setText(tekst);

		return formatedText.getText();
	}

	@Override
	public void setData(Object data)
	{
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// use of am/pm metric
		// DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy, hh a");
		// // use of hour and timezone
		// DateFormat format3 = new SimpleDateFormat("yyyy HH:mm:ss zzz");
		// // MEDIUM format: "MMM dd, yyyy"
		// DateFormat format4 = DateFormat.getDateInstance(DateFormat.MEDIUM);

		try
		{
			Date datum = format1.parse(data.toString());
			((JCalendarCombo) comp).setDate(datum);
			((JCalendarCombo) comp).repaint();
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean isValid()
	{
		String dateData = this.getData().toString();
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateTimeInstance();
		Date date;

		try
		{
			date = sdf.parse(dateData);
		}
		catch (ParseException e)
		{
			return false;
		}

		if (!sdf.format(date).equals(dateData))
		{
			return false;
		}

		return true;
	}

	/**
	 * Metoda brise sadrzaj polja.
	 */
	@Override
	public void clear()
	{
		((JCalendarCombo) comp).setDate(null);
		((JCalendarCombo) comp).repaint();
	}

}
