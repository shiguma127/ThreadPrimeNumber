package TPNumber;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
        public static void main(String[] args) {
            double i;




        }
        private static void writer(int suuji){
            try {
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("sosu.txt"), true), StandardCharsets.UTF_8), true);
                printWriter.println(suuji);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
}
