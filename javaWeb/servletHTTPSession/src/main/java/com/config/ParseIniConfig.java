package com.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParseIniConfig {
    private final Map<String, String> configMap;

    public ParseIniConfig() {
        configMap = new HashMap<>();
    }

    public void loadConfigFromFile(String filePath) {
        try {
            File configFile = new File(filePath);
            FileReader fileReader = new FileReader(configFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    configMap.put(key, value);
                }
            }

            reader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return configMap.get(key);
    }
}
