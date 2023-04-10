package custom.settings;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Telephone {
    private HashMap<String, String> setting;

    public Telephone() {
        setting = new HashMap<String, String>();
    }

    public void put(String param, String value) {
        setting.put(param, value);
    }

    public String get(String param) {
        return setting.get(param);
    }

    public void delete(String param) {
        setting.remove(param);
    }

    @Override
    public String toString() {
        return setting.toString();
    }

    public boolean equals(Telephone obj) {
        return setting.equals(obj.setting);
    }

    public void saveToBinaryFile(String filename) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            Telephone obj = new Telephone();
            for (Map.Entry<String, String> set : this.setting.entrySet()) {
                obj.put(set.getKey(), set.getValue()
                        .replace("+7", "8")
                        .replace("-", "")
                        .replace(" ", "")
                        .replace("(", "")
                        .replace(")", "")
                );
            }
            out.writeObject(this);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void loadFromBinaryFile(String filename) throws IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    "{" + filename + "}"

            ));
            Telephone settings = (Telephone) in.readObject();
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
            throw new IOException(">> " + e.getMessage());
        }
    }

    public void loadFromTextFile(String filename) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line = in.readLine();
            in.close();
            this.setting = new HashMap<String, String>();
            String[] pairs = line.substring(10, line.length() - 1).split(", ");
            for (String pair : pairs) {
                String[] keyValue = pair.split(": ");
                this.setting.put(keyValue[0], keyValue[1]);
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
