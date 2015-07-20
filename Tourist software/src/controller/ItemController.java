package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;

import localization.Localization;

/**
 * 
 * Klasa Item Controller hvata sve dogadjaje koji se dese za bilo koji check box 
 * koji se klikne na meniju.
 * 
 * @author Grupa 4
 *
 */
public class ItemController implements ItemListener
{

	private Localization localization = Localization.getInstance();
	
	public ItemController()
	{
	}
	/**
	 * Metoda koja prati projenu stanja check box-a i omogucava lokalizaciju aplikacije.
	 */
	@Override
	public void itemStateChanged(ItemEvent source)
	{
		AbstractButton ab = (AbstractButton) source.getSource();
		if (source.getStateChange() == ItemEvent.SELECTED)
		{
			if (ab.getActionCommand().equals("en_US"))
			{
				localization.setLocal("en", "US");
				localization.updateAll();
			}
			else if (ab.getActionCommand().equals("sr_RS"))
			{
				localization.setLocal("sr", "RS");
				localization.updateAll();
			}
			else if (ab.getActionCommand().equals("sr_BA"))
			{
				localization.setLocal("sr", "BA");
				localization.updateAll();
			}
			
		}
	}

}
