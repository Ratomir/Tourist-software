package parser;

import java.io.FileWriter;

import javax.swing.JFrame;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import view.View;
import view.login.LoginForm;

public class XMLwriter
{
	private String source;
	private String rootElement;

	private String valueForWrite = "";

	public String getValueForWrite()
	{
		return valueForWrite;
	}

	public void setValueForWrite(String valueForWrite)
	{
		this.valueForWrite = valueForWrite;
	}

	public XMLwriter()
	{
		source = "";
		rootElement = "";
	}

	public XMLwriter(String source)
	{
		this.source = source;
		rootElement = "";
	}

	public XMLwriter(String source, String rootElement)
	{
		this.source = source;
		this.rootElement = rootElement;
	}

	public void writeWindowParametars(JFrame application)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try
		{
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("xml\\windowProp.xml"));

			writer.writeStartDocument();
			writer.writeStartElement("window");

			writer.writeStartElement("dimension");
			writer.writeStartElement("value");
			writer.writeCharacters("" + application.getWidth());
			writer.writeEndElement();
			writer.writeEndElement();

			writer.writeStartElement("dimension");
			writer.writeStartElement("value");
			writer.writeCharacters("" + application.getHeight());
			writer.writeEndElement();
			writer.writeEndElement();

			writer.writeStartElement("x-coordinate");
			writer.writeStartElement("value");
			writer.writeCharacters("" + application.getX());
			writer.writeEndElement();
			writer.writeEndElement();

			writer.writeStartElement("y-coordinate");
			writer.writeStartElement("value");
			writer.writeCharacters("" + application.getY());
			writer.writeEndElement();
			writer.writeEndElement();

			// pocetak koda za upis lokalizacije
			writer.writeStartElement("localization");
			writer.writeStartElement("value");
			
			if (application.getTitle().contains("software"))
			{
				writer.writeCharacters("en");
			}
			else if (application.getTitle().contains("program"))
			{
				writer.writeCharacters("bh");
			}
			else
			{
				writer.writeCharacters("sr");
			}
			
			writer.writeEndElement();
			writer.writeEndElement();
			// kraj koda za upis lokalizacije
			
			//pocetak koda za upis lokacije do database fajla
			writer.writeStartElement("location");
			writer.writeStartElement("value");
			
			writer.writeCharacters(View.locationDataBaseFile.toString());
			
			writer.writeEndElement();
			writer.writeEndElement();
			//kraj kod za upis lokacije do database fajla

			writer.writeEndDocument();

			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	/**
	 * Metoda pamti parametre za logovanje u zavisnosti od toga da li je check box oznacen.
	 * @param loginForm
	 */
	public void writeRemeberMeParameters(LoginForm loginForm)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try
		{
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("xml\\rememberMe.xml"));

			writer.writeStartDocument();
			writer.writeStartElement("window");

			writer.writeStartElement("name");
			writer.writeStartElement("value");
			writer.writeCharacters((loginForm.getRememberMe().isSelected() ? loginForm.getUserNameField().getText() : "0" ));
			writer.writeEndElement();
			writer.writeEndElement();

			writer.writeStartElement("password");
			writer.writeStartElement("value");
			writer.writeCharacters((loginForm.getRememberMe().isSelected() ? loginForm.getPasswordField().getText() : "0" ));
			writer.writeEndElement();
			writer.writeEndElement();
			
			writer.writeEndDocument();

			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	/*
	 * 
	 * Getters and setters
	 */

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getRootElement()
	{
		return rootElement;
	}

	public void setRootElement(String rootElement)
	{
		this.rootElement = rootElement;
	}

}
