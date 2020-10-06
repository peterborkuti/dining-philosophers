package hu.bp.diningphilosophers;

public abstract class Philosopher implements Runnable {
	public final int name;

	public Philosopher(int name) {
		this.name = name;
	}

}
