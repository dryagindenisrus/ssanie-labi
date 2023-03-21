package custom.settings;

import java.io.*;
import java.util.HashMap;

public class Settings {
    private HashMap<String, Integer> setting;

    public Settings() {
        setting = new HashMap<String, Integer>();
    }

    public void put(String param, int value) {
        setting.put(param, value);
    }

    public int get(String param) {
        return setting.get(param);
    }

    public void delete(String param) {
        setting.remove(param);
    }

    @Override
    public String toString() {
        return setting.toString();
    }

    public boolean equals(Settings obj) {
        return setting.equals(obj.setting);
    }

    public void saveToBinaryFile(String filename) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void loadFromBinaryFile(String filename) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            Settings settings = (Settings) in.readObject();
            in.close();
            this.setting = settings.setting;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void saveToTextFile(String filename) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.println(this);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void loadFromTextFile(String filename) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            in.close();
            this.setting = new HashMap<String, Integer>();
            String[] pairs = line.substring(10, line.length() - 1).split(", ");
            for (String pair : pairs) {
                String[] keyValue = pair.split(": ");
                this.setting.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
