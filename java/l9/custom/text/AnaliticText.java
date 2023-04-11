package custom.text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static custom.text.AnaliticText.analyze;

public class AnaliticText {

    private final String[] files;
    private final Thread[] threads;
    public static ArrayList<HashMap<String, Integer>> analyze;

    public AnaliticText(String[] files) {
        this.files = files;
        threads = new Thread[files.length];
        analyze = new ArrayList<HashMap<String, Integer>>();
    }

    public void goAnalyze() throws IOException {

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ThreadTask(files[i], analyze));
            threads[i].start();
        }
    }
}


class ThreadTask implements Runnable {
    String filename;

    public ThreadTask(String filename, ArrayList<HashMap<String, Integer>> analyze) {
        this.filename = filename;
    }

    public void run() {
        HashMap<String, Integer> wordCounts = new HashMap<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCounts.merge(word, 1, Integer::sum);
                }
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        synchronized (analyze) {
            analyze.add(wordCounts);
        }
    }
}
