package hu.bp.diningphilosophers;

public interface Table {
	public boolean pickUpForks(int philosopherName);
	public void putDownForks(int philosopherName);
}
