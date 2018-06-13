package TPNumber;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final int THREAD_NUMBER = 2^12;
    private static final String FILE_NAME = "sosu.txt";

    private static Long i = 7L;
    private static PrintWriter printWriter;

    public static void main(String[] args) throws Exception {
        printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(FILE_NAME), true), StandardCharsets.UTF_8), true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> printWriter.close()));
        List<String> sosu = null;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
        try {
            sosu = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (sosu != null && !sosu.isEmpty()) {
            String lastSosu = sosu.get(sosu.size() - 1);
            i = Long.valueOf(lastSosu);
            System.out.println(i);
        }
        writer(2L);
        writer(3L);
        writer(5L);
        writer(7L);

        List<Long> sorted = new ArrayList<>();
        List<Future<Long>> futures = new ArrayList<>();
        while (true) {
            for (int thread = 0; thread < THREAD_NUMBER; thread++) {
                futures.add(executor.submit(new sosu(++i)));
            }
            for (Future<Long> future : futures) {
                Long re = future.get();
                if (re != null) sorted.add(re);

            }
            futures.clear();
            sorted.sort(Long::compareTo);

            for (Long a : sorted) {
                writer(a);
                System.out.println(a);
            }
            sorted.clear();
        }
    }

    public static class sosu implements Callable<Long> {
        private Long i;

        sosu(Long i) {
            this.i = i;
        }

        @Override
        public Long call() {
            if (i % 2 == 0 || i % 5 == 0 || i % 7 == 0) return null;

            int noD = 0;
            for (int cnt = 1; cnt <= i; cnt++) {
                if (i % cnt == 0) {
                    noD++;
                    if (noD > 2) {
                        break;
                    }
                }
            }
            if (noD != 2) return null;
            return i;
        }
    }

    private static void writer(Long suuji) {
        printWriter.println(suuji);
    }

}
