package controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractButton;

import model.UserModel;
import parser.XMLwriter;
import view.View;
import view.login.LoginForm;

/**
 * Klasa LoginController sadrzi metode neophodne za prijavljivanje korisnika na sistem 
 * i otvaranje forme aplikacije.
 * 
 * @author Ratomir
 *
 */
public class LoginController implements ActionListener, WindowListener
{
	private LoginForm loginForm = null;
	private UserModel user = null;
	private DataBaseModel dataBaseModel = null;

	public LoginController(LoginForm loginForm)
	{
		this.loginForm = loginForm;
		dataBaseModel = new DataBaseModel();
	}
	/**
	 * Metoda implementira funkcije za prijavljivanje i odjavljivanje sa baze.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		AbstractButton ab = (AbstractButton) e.getSource();

		switch (ab.getActionCommand())
		{
		case "login":
		{
			// nakon uspješne autentifikacije otvaramo view od aplikacije
			
			if(dataBaseModel.findUser(this.loginForm.getUserNameField().getText(), this.loginForm.getPasswordField().getText()))
			{
				XMLwriter xml = new XMLwriter();
				xml.writeRemeberMeParameters(this.loginForm);
				UserModel userModel = new UserModel(this.loginForm.getUserNameField().getText(), this.loginForm.getPasswordField().getText());
				
				loginForm.dispose();
				new View(userModel);
			}
			else
			{
				this.loginForm.getLblErrorUserName().setVisible(true);
			}
			
			break;
		}
		case "cancel":
		{
			XMLwriter xml = new XMLwriter();
			xml.writeRemeberMeParameters(this.loginForm);
			
			System.exit(0);
			break;
		}

		default:
			break;
		}
	}

	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginModel()
	{
		return loginForm;
	}

	/**
	 * @param loginForm
	 *            the loginForm to set
	 */
	public void setLoginModel(LoginForm loginModel)
	{
		this.loginForm = loginModel;
	}

	/**
	 * @return the user
	 */
	public UserModel getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserModel user)
	{
		this.user = user;
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
	}
	/**
	 * Metoda omogucava zatvaranje aplikacije.
	 */
	@Override
	public void windowClosing(WindowEvent e)
	{
		XMLwriter xml = new XMLwriter();
		xml.writeRemeberMeParameters(this.loginForm);
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

}
