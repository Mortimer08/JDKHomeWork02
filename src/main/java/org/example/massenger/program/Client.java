package org.example.massenger.program;

import org.example.massenger.gui.ClientWindow;
import org.example.massenger.ui.ClientView;

public class Client {
    private Server server;
    private User user;
    private Socket socket;

    private ClientView clientView;
    boolean connected;

    Client(Server server) {
        this.server = server;
        user = new User();
        socket = new Socket();
        clientView = new ClientWindow(this);
        connected = false;
    }

    void login() {
        server.connectClient(this);
    }

    public void sendMessage(String message) {
        message = getUserName() + ": " + message;
        server.sendMessage(message);
    }

    void receiveMessage(String message) {
        clientView.printMessage(message);
    }

    public void connect() {
        server.connectClient(this);
//        clientView.takeConnectedView();
        connected = true;
    }

    public void disconnect() {
        clientView.takeDisconnectedView();
        connected = false;
    }

    public String getUserName() {
        return user.getName();
    }

    public void setUserName(String name) {
        user.setName(name);

    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        user.setPassword(password);
    }

    public String getSocketIP() {
        return socket.getIp();
    }

    public void setSocketIP(String ip) {
        socket.setIp(ip);
    }
    public void setSocketPort(String port){
        socket.setPort(port);
    }

    public String getSocketPort() {
        return socket.getPort();
    }
    public boolean isConnected(){
        return connected;
    }
}
