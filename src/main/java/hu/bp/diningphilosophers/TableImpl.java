package hu.bp.diningphilosophers;

import java.util.Arrays;
import java.util.stream.Collectors;

class MyForks {
	public final int left;
	public final int right;

	public MyForks(int philosopherName) {
		left = philosopherName;
		right = (philosopherName == 7) ? 0 : philosopherName + 1;
	}
}

public class TableImpl implements Table {
							  //    p0     p1     p2    p3     p4    p5    p6    p7
	private Boolean[] table = {true,  true,  true,  true,  true, true, true, true  };

	public synchronized boolean pickUpForks(int philosopherName) {
		MyForks forks = new MyForks(philosopherName);
		if (table[forks.left] && table[forks.right]) {
			table[forks.left] = false;
			table[forks.right] = false;

			return true;
		}

		return false;
	}

	public void putDownForks(int philosopherName) {
		MyForks forks = new MyForks(philosopherName);
		if (!table[forks.left] && !table[forks.right]) {
			table[forks.left] = true;
			table[forks.right] = true;
		}
		else {
			throw new IllegalArgumentException("Philosopher " + philosopherName + " does not have two forks");
		}
	}

	public synchronized String toString() {
		return Arrays.stream(table).map(f -> f ? "F":" ").collect(Collectors.joining(""));
	}
}
