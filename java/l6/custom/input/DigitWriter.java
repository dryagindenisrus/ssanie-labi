package custom.input;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DigitWriter {

    public DigitWriter() {}

    public void sscanf(String in, String filename) throws FileNotFoundException {
        try {
            PrintWriter out = new PrintWriter(filename);
            String asss = in
                    .replace("1", "one")
                    .replace("2", "two")
                    .replace("3", "three")
                    .replace("4", "four")
                    .replace("5", "five")
                    .replace("6", "six")
                    .replace("7", "seven")
                    .replace("8", "eight")
                    .replace("9", "nine")
                    .replace("0", "zero");
            out.println(asss
            );
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
}

