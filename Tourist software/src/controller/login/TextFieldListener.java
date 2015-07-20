package controller.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Klasa TextFieldListener definise metode za osluskivanje promjena sadrzaja tekstualnih polja.
 * 
 * @author Ratomir
 *
 */
public class TextFieldListener implements DocumentListener
{
	private JTextField txtPassword = null;
	private JTextField txtUserName = null;
	private JButton btnLogin = null;
	private JLabel error = null;
	
	public TextFieldListener(JTextField txtPassword, JTextField txtUserName, JButton btnLogin, JLabel error)
	{
		this.txtPassword = txtPassword;
		this.txtUserName = txtUserName;
		this.btnLogin = btnLogin;
		
		this.error = error;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0)
	{
	}

	@Override
	public void insertUpdate(DocumentEvent arg0)
	{
		this.changeTextField(arg0);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0)
	{
		this.changeTextField(arg0);
	}

	public void changeTextField(DocumentEvent event)
	{
		if(this.error.isVisible())
		{
			this.error.setVisible(false);
		}
		
		if(this.txtPassword.getText().length() > 0 && this.txtUserName.getText().length() > 0)
		{
			this.btnLogin.setEnabled(true);
		}
		else
		{
			this.btnLogin.setEnabled(false);
		}
	}

	/**
	 * @return the btnLogin
	 */
	public JButton getBtnLogin()
	{
		return btnLogin;
	}

	/**
	 * @param btnLogin the btnLogin to set
	 */
	public void setBtnLogin(JButton btnLogin)
	{
		this.btnLogin = btnLogin;
	}

	/**
	 * @return the txtUserName
	 */
	public JTextField getTxtUserName()
	{
		return txtUserName;
	}

	/**
	 * @param txtUserName the txtUserName to set
	 */
	public void setTxtUserName(JTextField txtUserName)
	{
		this.txtUserName = txtUserName;
	}

	/**
	 * @return the txtPassword
	 */
	public JTextField getTxtPassword()
	{
		return txtPassword;
	}

	/**
	 * @param txtPassword the txtPassword to set
	 */
	public void setTxtPassword(JTextField txtPassword)
	{
		this.txtPassword = txtPassword;
	}

}
