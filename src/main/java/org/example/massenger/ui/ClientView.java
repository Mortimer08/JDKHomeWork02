package org.example.massenger.ui;

public interface ClientView {
    void login();
    void sendMessage();
    void printMessage(String message);
    void takeConnectedView();
    void takeDisconnectedView();
}
