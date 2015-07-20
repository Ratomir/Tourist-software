package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import parser.XMLwriter;
import state.State;
import view.login.LoginForm;

public class WindowController implements WindowListener
{
	JFrame application = null;
	
	public WindowController(JFrame application)
	{
		this.application = application;
	}
	
	@Override
	public void windowActivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
	}
	/**
	 * Metoda omogucava zatvaranje prozora aplikacije.
	 */
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		XMLwriter xml = new XMLwriter();
		xml.writeWindowParametars(this.application);
		
		State.setState(null);
		
		application.setVisible(false); //you can't see me!
		application.dispose(); //Destroy the JFrame object
		new LoginForm();	
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

	/**
	 * @return the application
	 */
	public JFrame getApplication()
	{
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(JFrame application)
	{
		this.application = application;
	}

}
