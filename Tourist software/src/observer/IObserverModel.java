package observer;
/**
 * Interfejs IObserverModel definise osnovne metode koje treba da obezbjede modeli.
 * 
 * @author Grupa 4
 */

public interface IObserverModel
{
	public void addObserver(IObserver observer);
	public void notifyAllObservers();
}
