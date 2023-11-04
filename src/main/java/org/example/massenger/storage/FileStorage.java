package org.example.massenger.storage;

import java.io.*;

public class FileStorage implements Storage {
    private final String fileName = "logfile.txt";
    BufferedWriter writer;
    BufferedReader reader;
    StringBuilder messages;

    @Override
    public void save(String text) {

        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(text);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String load() {
        messages = new StringBuilder();
        String line = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();
            while (line != null) {
                messages.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return messages.toString();
    }
}
