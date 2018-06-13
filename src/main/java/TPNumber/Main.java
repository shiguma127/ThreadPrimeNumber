package TPNumber;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static int i = 3;
    public static void main(String[] args) {
        int d;
        int cnt;
        int noD;
        String temp;
        int threadNumber;
        List<String> sosu = null;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            sosu = Files.readAllLines(Paths.get("sosu.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(sosu.isEmpty())){
            int sosuSize = sosu.size();
            temp = sosu.get(sosuSize-1);
            i = Integer.valueOf(temp);
            System.out.println(i);
        }
        writer(2);
        while (true) {
            if ((i % 2 == 0)
                    || (i < 10 && i % 5 == 0)
                    || (i < 10 && i % 7 == 0)) {
                i++;
                continue;
            }

            noD = 0;
            for (cnt = 1; cnt <= i; cnt = cnt + 1) {
                d = cnt;
                if (i % d == 0) {
                    noD++;
                    if (noD > 2) {
                        break;
                    }
                }
            }
            if (noD == 2) {
                System.out.println(i);
                executorService.submit(new TestRunnable());
            }
            i++;
        }


    }
    public static class TestRunnable implements Runnable{
        @Override
        public void run() {
            try {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("sosu.txt"), true), StandardCharsets.UTF_8), true);
                printWriter.println(i);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private static void writer(double suuji) {
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("sosu.txt"), true), StandardCharsets.UTF_8), true);
            printWriter.println(suuji);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
