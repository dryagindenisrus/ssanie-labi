import custom.settings.Settings;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Settings a = new Settings();
        a.put("Volume", 12);
        a.put("Light", 0);
        System.out.println(a);

        try {
//            a.saveToBinaryFile("C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l7\\settings.bin");
            a.saveToTextFile("C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l7\\settings.txt");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
