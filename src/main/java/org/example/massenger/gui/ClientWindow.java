package org.example.massenger.gui;

import org.example.massenger.program.Client;
import org.example.massenger.program.Server;
import org.example.massenger.program.Socket;
import org.example.massenger.program.User;
import org.example.massenger.ui.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame implements ClientView {
    private Client client;
    private User user;
    private Socket socket;
    public static final String WINDOW_CAPTION = "Chat client";
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private JPanel topPanel;
    private JPanel addressPanel;
    private JPanel userPanel;
    private JTextField ipArea;

    private JTextField portArea;
    private Integer defaultPort = 8080;
    private JTextField nameArea;


    private JPasswordField passwordArea;

    private JPanel middlePanel;
    private JTextArea messagesField;
    private JPanel bottomPanel;
    private JTextField inputField;
    private JButton sendButton;
    private final String sendButtonCaption = "send";
    private JButton btnLogin;
    public static final String BTN_LOGIN_CAPTION = "login";

    public ClientWindow(Client client) {
        this.client = client;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(WINDOW_CAPTION);
        setResizable(false);
        user = new User();
        socket = new Socket();
        add(createTopPanel(), BorderLayout.NORTH);
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    Component createTopPanel() {
        topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(createAddressPanel());
        topPanel.add(createUserPanel());

        return topPanel;
    }

    Component createAddressPanel() {
        addressPanel = new JPanel(new GridLayout(1, 3));
        ipArea = new JTextField(socket.getIp());
        portArea = new JTextField(socket.getPort());
        addressPanel.add(ipArea);
        addressPanel.add(portArea);
        return addressPanel;
    }

    Component createUserPanel() {
        userPanel = new JPanel(new GridLayout(1, 3));
        nameArea = new JTextField(user.getName());
        passwordArea = new JPasswordField(user.getPassword());

        userPanel.add(nameArea);
        userPanel.add(passwordArea);
        userPanel.add(createButtonLogin());
        return userPanel;
    }

    Component createButtonLogin() {
        btnLogin = new JButton(BTN_LOGIN_CAPTION);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login();
            }
        });
        return btnLogin;

    }

    Component createMiddlePanel() {
        middlePanel = new JPanel(new GridLayout(1, 1));
        middlePanel.add(createMessagesField());
        return middlePanel;
    }

    Component createMessagesField() {
        messagesField = new JTextArea();
        messagesField.setEditable(false);
        return messagesField;
    }

    Component createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(createInputField());
        bottomPanel.add(createSendButton());
        return bottomPanel;
    }

    Component createInputField() {
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        return inputField;
    }

    Component createSendButton() {
        sendButton = new JButton(sendButtonCaption);
        return sendButton;
    }

    @Override
    public void login() {
        client.connect();
        takeConnectedView();
    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void printMessage(String message) {
        messagesField.append(message);
        messagesField.append("\n");
    }

    @Override
    public void takeConnectedView() {
        topPanel.setVisible(false);

    }

    @Override
    public void takeDisconnectedView() {
        topPanel.setVisible(true);

        System.out.println("Disconnected view");
    }
}
