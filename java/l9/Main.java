import custom.people.People;
import custom.text.AnaliticText;
import static custom.text.AnaliticText.analyze;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String[] z = {
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l9\\test_1.txt",
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l9\\test_2.txt",
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l9\\test_3.txt",
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l9\\test_4.txt"
        };

        AnaliticText a = new AnaliticText(z);
        a.goAnalyze();
        TimeUnit.SECONDS.sleep(1);

        for (java.util.HashMap<String, Integer> stringIntegerHashMap : analyze) {
            System.out.println(stringIntegerHashMap);
        }
    }
}