package hu.bp;

import hu.bp.diningphilosophers.Philosopher;
import hu.bp.diningphilosophers.PhilosopherImpl;
import hu.bp.diningphilosophers.Table;
import hu.bp.diningphilosophers.TableImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Table table = new TableImpl();
        List<Philosopher> philosophers = IntStream.range(0, 8).mapToObj(i -> new PhilosopherImpl(i, table)).collect(Collectors.toList());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);

        List<ScheduledFuture<?>> tasks = philosophers.stream().map(p -> executor.scheduleWithFixedDelay(p, new Random().nextInt(500) + 1, new Random().nextInt(5) + 1, TimeUnit.NANOSECONDS)).collect(Collectors.toList());

        int count = 1000;
        while (count-- > 0) {
            try {
                if (count % 100 == 0) System.out.println(table.toString() + " - " + count);
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        tasks.forEach(task -> task.cancel(true));
        executor.shutdown();

        philosophers.stream().forEach(System.out::println);
    }
}
