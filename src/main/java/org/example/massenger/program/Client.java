package org.example.massenger.program;

import org.example.massenger.gui.ClientWindow;
import org.example.massenger.ui.ClientView;

public class Client {
    private Server server;
    private ClientView clientView;
    boolean connected;
    Client(Server server){
        this.server = server;
        clientView = new ClientWindow(this);
        connected = false;

    }
    void login(){
        server.connectClient(this);
    }
    void sendMessage(String  message){
        clientView.printMessage(message);
    }
    void receiveMessage(String  message){
        clientView.sendMessage();
    }
    public void connect(){
        server.connectClient(this);
//        clientView.takeConnectedView();
        connected = true;
    }
    public void disconnect(){
        clientView.takeDisconnectedView();
        connected = false;
    }
}
