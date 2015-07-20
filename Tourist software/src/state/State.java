package state;

import state.interfaces.IState;
/**
 * Klasa State omogucava manipulaciju stanjima aplikacije.
 * @author Ratomir
 *
 */

public class State
{
	private static IState state = null;
	
	public State(){}
	
	public static void setState(IState newState)
	{
		state = newState;
	}

	public static IState getState()
	{
		if(state == null)
		{
			state = new ReadyState();
		}
		return state;
	}
}
