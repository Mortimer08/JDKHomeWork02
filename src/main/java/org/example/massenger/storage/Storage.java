package org.example.massenger.storage;

public interface Storage {
    void save(String text);
    String load();
}
