package parser;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import localization.Localization;

import org.xml.sax.SAXException;

import view.View;
import view.login.LoginForm;

public class XMLreader
{	
	private Localization localization = null;
	/**
	 * Metoda cita parametre
	 * 
	 * @param application
	 */
	public void ReadWindowParameters(JFrame application)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try
		{
			// Kreiranje novog parsera
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xml/windowProp.xml", handler);

			application.setSize(Integer.parseInt(handler.getParameters().get(0)),
					Integer.parseInt(handler.getParameters().get(1)));
			application.setPreferredSize(new Dimension(Integer.parseInt(handler.getParameters().get(0)), Integer
					.parseInt(handler.getParameters().get(1))));
			application.setLocation(Integer.parseInt(handler.getParameters().get(2)),
					Integer.parseInt(handler.getParameters().get(3)));

			this.localization = Localization.getInstance();
			// kod za citanje i vracanje lokalizacije iz xml-a
			if ((handler.getParameters().get(4)).contains("en"))
			{
				View.locale = "en_US";
				localization.setLocal("en", "US");
			}
			else if ((handler.getParameters().get(4)).contains("bh"))
			{
				View.locale = "sr_BA";
				localization.setLocal("sr", "BA");
			}
			else
			{
				View.locale = "sr_RS";
				localization.setLocal("sr", "RS");
			}

			View.locationDataBaseFile = handler.getParameters().get(5).toString();
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Metoda omogucava kreiranje novog parsera, cita i vraca lokalizaciju iz xml-a
	 */
	public void readLocalization()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try
		{
			// Kreiranje novog parsera
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xml/windowProp.xml", handler);

			this.localization = Localization.getInstance();
			// kod za citanje i vracanje lokalizacije iz xml-a
			if ((handler.getParameters().get(4)).contains("en"))
			{
				localization.setLocal("en", "US");
			}
			else if ((handler.getParameters().get(4)).contains("bh"))
			{
				localization.setLocal("sr", "BA");
			}
			else
			{
				localization.setLocal("sr", "RS");
			}
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Metoda cita parametre za logovanje.
	 * 
	 * @param loginForm
	 */
	public void readLoginParameters(LoginForm loginForm)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try
		{
			// Kreiranje novog parsera
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse("xml/rememberMe.xml", handler);

			String userName = handler.getParameters().get(0).toString();
			
			if(!userName.equals("0"))
			{
				loginForm.getUserNameField().setText(userName);
				loginForm.getPasswordField().setText(handler.getParameters().get(1).toString());
				loginForm.getRememberMe().setSelected(true);
				loginForm.getBtnLogin().setEnabled(true);
			}
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String defaultWorkspace()
	{
		return "";
	}

	public String defaultTheme()
	{
		return "";
	}

}
