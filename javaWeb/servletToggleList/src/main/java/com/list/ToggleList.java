package com.list;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToggleList {
    private final Map<String, List<String>> lists;

    public ToggleList(String filePath) {
        this.lists = new HashMap<>();
        loadListsFromFile(filePath);
    }

    public  Map<String, List<String>>getAllItems() {
        return this.lists;
    }

    private void loadListsFromFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String currentList = null;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                int indentLevel = getIndentLevel(line);

                if (indentLevel == 0) {
                    currentList = line.trim();
                    lists.put(currentList, new ArrayList<>());
                } else if (currentList != null) {
                    lists.get(currentList).add(line.trim());
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIndentLevel(String line) {
        int level = 0;
        while (level < line.length() && line.charAt(level) == ' ') {
            level++;
        }
        return level / 4;
    }

    public Map<String, List<String>> getLists() {
        return lists;
    }
}
