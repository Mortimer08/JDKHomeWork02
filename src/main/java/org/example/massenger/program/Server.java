package org.example.massenger.program;

import org.example.massenger.gui.ServerWindow;
import org.example.massenger.storage.FileStorage;
import org.example.massenger.storage.Storage;
import org.example.massenger.ui.ServerView;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Client> clients;
    private final ServerView serverView;
    private final Storage storage;
    private static final String SERVER_STARTED_MESSAGE = "Server started";
    private static final String SERVER_IS_RUNNING = "Server is already running";
    private static final String SERVER_STOPPED_MESSAGE = "Server stopped";
    private static final String SERVER_IS_STOPPED = "Server is not running";
    private boolean serverRunning;

    public Server() {
        this.serverView = new ServerWindow(this);
        this.storage = new FileStorage();
        serverRunning = false;
        clients = new ArrayList<>();
    }

    public void start() {
        if (!serverRunning) {
            serverRunning = true;
            serverView.print(SERVER_STARTED_MESSAGE);
//            serverView.print(storage.load());
            addClient();
            addClient();
        } else {
            serverView.print(SERVER_IS_RUNNING);
        }
    }

    public void stop() {
        if (serverRunning) {
            serverRunning = false;
            serverView.print(SERVER_STOPPED_MESSAGE);
            storage.save(serverView.getMessages());
        } else {
            serverView.print(SERVER_IS_STOPPED);
        }
    }

    public void addClient() {

        clients.add(new Client(this));

    }

    public void connectClient(Client client) {
        client.sendMessage(storage.load());
    }
    void sendMessage(String message){
        for (Client client: clients) {
            client.sendMessage(message);
        }
    }
}
