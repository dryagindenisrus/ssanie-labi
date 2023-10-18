package com.servletbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notebook {
    private final Map<String, ArrayList<String>> notes;

    public Notebook() {
        notes = new HashMap<>();
    }

    public synchronized void addOrUpdateNote(String title, String content) {
        if (!notes.containsKey(title)) {
            notes.put(title, new ArrayList<>());
        }
        notes.get(title).add(content);
    }

    public synchronized List<String> getNotes(String title) {
        return notes.get(title);
    }

    public synchronized Map<String, ArrayList<String>> getAllNotes() {
        return notes;
    }

    public synchronized List<String> getAllNoteTitles() {
        return new ArrayList<>(notes.keySet());
    }

    public synchronized void deleteNoteByTitle(String title) {
        notes.remove(title);
    }

    public synchronized void deleteAllNotesByTitle(String title) {
        notes.remove(title);
    }

    public synchronized void clearAllNotes() {
        notes.clear();
    }

    public synchronized boolean doesNoteExist(String title) {
        return notes.containsKey(title);
    }

    public synchronized int getNoteCount(String title) {
        if (notes.containsKey(title)) {
            return notes.get(title).size();
        }
        return 0;
    }

    public synchronized List<String> getNamesStrings() {
        return new ArrayList<>(notes.keySet());
    }

    public synchronized void saveToTextFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, ArrayList<String>> entry : notes.entrySet()) {
                String title = entry.getKey();
                List<String> noteContents = entry.getValue();
                StringBuilder content = new StringBuilder();

                content.append("(");
                for (String value : noteContents) {
                    content.append(value).append(", ");
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
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String content = parts[1].trim();

                    if (content.startsWith("(") && content.endsWith(")")) {
                        content = content.substring(1, content.length() - 1);
                        String[] values = content.split(", ");
                        for (String value : values) {
                            addOrUpdateNote(title, value);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Notebook Contents:\n");

        for (Map.Entry<String, ArrayList<String>> entry : notes.entrySet()) {
            sb.append("Title: ").append(entry.getKey()).append("\n");
            List<String> noteContents = entry.getValue();
            for (String content : noteContents) {
                sb.append(" - ").append(content).append("\n");
            }
        }

        return sb.toString();
    }

    public synchronized List<String> getByTitle(String param) {
        List<String> matchingNotes = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : notes.entrySet()) {
            String title = entry.getKey();
            if (title.contains(param)) {
                List<String> noteContents = entry.getValue();
                for (String content : noteContents) {
                    matchingNotes.add(title + ": " + content);
                }
            }
        }

        return matchingNotes;
    }


}
