package com.board;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertisementManager {
    private Map<String, List<Advertisement>> advertisements;

    public AdvertisementManager() {
        advertisements = new HashMap<>();
    }

    public synchronized void addOrUpdateAdvertisement(String title, Advertisement content) {
        if (!advertisements.containsKey(title)) {
            advertisements.put(title, new ArrayList<>());
        }
        List<Advertisement> advertisementList = advertisements.get(title);
        advertisementList.add(content);
    }

    public synchronized List<Advertisement> getAdvertisements(String title) {
        return advertisements.get(title);
    }

    public synchronized void setAdvertisements(Map<String, List<Advertisement>> data) {
        this.advertisements = data;
    }

    public synchronized Map<String, List<Advertisement>> getAllAdvertisements() {
        return advertisements;
    }

    public synchronized List<String> getAllAdvertisementTitles() {
        return new ArrayList<>(advertisements.keySet());
    }

    public synchronized void deleteAdvertisementByTitle(String title) {
        advertisements.remove(title);
    }

    public synchronized void deleteAllAdvertisementsByTitle(String title) {
        advertisements.remove(title);
    }

    public synchronized void clearAllAdvertisements() {
        advertisements.clear();
    }

    public synchronized boolean doesAdvertisementExist(String title) {
        return advertisements.containsKey(title);
    }

    public synchronized int getAdvertisementCount(String title) {
        if (advertisements.containsKey(title)) {
            return advertisements.get(title).size();
        }
        return 0;
    }

    public synchronized List<String> getAdvertisementTitles() {
        return new ArrayList<>(advertisements.keySet());
    }

    public synchronized void saveToTextFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, List<Advertisement>> entry : advertisements.entrySet()) {
                String title = entry.getKey();
                List<Advertisement> advertisementContents = entry.getValue();
                StringBuilder content = new StringBuilder();

                content.append("(");
                for (Advertisement advertisement : advertisementContents) {
                    content.append("Description: ").append(advertisement.getDescription()).append(", Price: ").append(advertisement.getPrice()).append("; ");
                }
                if (content.length() > 1) {
                    content.delete(content.length() - 2, content.length());
                }
                content.append(")");

                writer.write(title + "; " + content.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void loadFromTextFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            advertisements.clear(); // Очистить существующие данные перед загрузкой

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String content = parts[1].trim();
                    List<Advertisement> advertisementsList = new ArrayList<>();
                    int startIndex = content.indexOf("Description: ");
                    int endIndex;
                    while (startIndex != -1) {
                        endIndex = content.indexOf(", Price: ", startIndex);
                        if (endIndex != -1) {
                            String description = content.substring(startIndex + 13, endIndex);
                            int priceEndIndex = content.indexOf(")", endIndex);
                            if (priceEndIndex != -1) {
                                Double price = Double.parseDouble(content.substring(endIndex + 9, priceEndIndex));
                                advertisementsList.add(new Advertisement(description, price));
                            }
                        }
                        startIndex = content.indexOf("Description: ", endIndex);
                    }
                    advertisements.put(title, advertisementsList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Advertisement Manager Contents:\n");

        for (Map.Entry<String, List<Advertisement>> entry : advertisements.entrySet()) {
            sb.append("Title: ").append(entry.getKey()).append("\n");
            List<Advertisement> advertisementContents = entry.getValue();
            for (Advertisement advertisement : advertisementContents) {
                sb.append(" - Description: ").append(advertisement.getDescription()).append(", Price: ").append(advertisement.getPrice()).append("\n");
            }
        }

        return sb.toString();
    }
}
