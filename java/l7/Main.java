import custom.settings.Settings;
import custom.settings.Telephone;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Settings a = new Settings();
        a.put("Volume", 12);
        a.put("Light", 0);
        System.out.println(a);

        Telephone b = new Telephone();
        b.put("Igorek", "+79217771243");
        b.put("Sanich_avtoservise", "+7 (900) 653-21-09");
        System.out.println(b);

        try {
            b.saveToTextFile("C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l7\\phone.txt");
//            a.saveToBinaryFile("C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l7\\settings.bin");
//            a.saveToTextFile("C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l7\\settings.txt");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
