package larissa.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.impl.client.HttpClients;

public class Main {
    private static final int NUM_RUNS = 10;

    public static void main(String[] args) throws InterruptedException, IOException {
        Map<String, Long[]> results = new HashMap<>();
        WeatherDataCollector collector = new WeatherDataCollector(HttpClients.createDefault());

        // Versão de referência (sem threads)
        results.put("Reference (0 threads)", runExperiment(collector, 0, 1));

        // Versão com 3 threads (cada uma com 9 requisições)
        results.put("3 threads (each 9 cities)", runExperiment(collector, 3, 9));

        // Versão com 9 threads (cada uma com 3 requisições)
        results.put("9 threads (each 3 cities)", runExperiment(collector, 9, 3));

        // Versão com 27 threads (cada uma com 1 requisição)
        results.put("27 threads (each 1 city)", runExperiment(collector, 27, 1));

        // Mostrar resultados em forma de tabela
        showResults(results);
    }

    private static Long[] runExperiment(WeatherDataCollector collector, int numberOfThreads, int citiesPerThread) throws InterruptedException {
        Long[] executionTimes = new Long[NUM_RUNS];

        for (int i = 0; i < NUM_RUNS; i++) {
            long startTime = System.currentTimeMillis();
            collector.collectAndProcessDataWithThreads(numberOfThreads, citiesPerThread);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            executionTimes[i] = executionTime;
        }

        return executionTimes;
    }

    private static void showResults(Map<String, Long[]> results) {
        System.out.println("Comparison of Execution Times (Each run in milliseconds)\n");

        // Print header
        System.out.printf("%-30s", "Experiment");
        for (int i = 1; i <= NUM_RUNS; i++) {
            System.out.printf("| Run %2d ", i);
        }
        System.out.printf("| Average  ");
        System.out.println();

        // Print separator
        for (int i = 0; i < 35 + NUM_RUNS * 8 + 10; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print each experiment's results and their average
        results.forEach((experiment, executionTimes) -> {
            System.out.printf("%-30s", experiment);
            long total = 0;
            for (Long time : executionTimes) {
                System.out.printf("| %6d ", time);
                total += time;
            }
            long average = total / NUM_RUNS;
            System.out.printf("| %8d ", average);
            System.out.println();
        });

        System.out.println();
    }
}
