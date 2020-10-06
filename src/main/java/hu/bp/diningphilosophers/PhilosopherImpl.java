package hu.bp.diningphilosophers;

import java.util.Random;

public class PhilosopherImpl extends Philosopher {
	private State state;
	private Table table;
	private long hungry;

	public PhilosopherImpl(int name, Table table) {
		super(name);
		this.table = table;
		this.state = State.THINKING;
		this.hungry = 0;
	}

	public void run() {
		changeState();
		if (state == State.HUNGRY && table.pickUpForks(this.name)) {
			state = State.EATING;
		}
		if (state == State.HUNGRY) hungry++;
	}

	private void changeState() {
		if (state == State.THINKING && Math.random() < 0.1) {
			state = State.HUNGRY;
		} else if (state == State.EATING && Math.random() < 0.1) {
			table.putDownForks(name);
			state = State.THINKING;
		}
	}

	public String toString() {
		return "Philosopher " + name + " was hungry " + hungry + " times";
	}
}
