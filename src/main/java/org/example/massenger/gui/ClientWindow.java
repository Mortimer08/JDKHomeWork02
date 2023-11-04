package org.example.massenger.gui;

import org.example.massenger.program.Client;
import org.example.massenger.ui.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame implements ClientView {
    private Client client;

    public static final String WINDOW_CAPTION = "Chat client";
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private JPanel topPanel;
    private JPanel addressPanel;
    private JPanel userPanel;
    private JTextField ipArea;

    private JTextField portArea;
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
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(WINDOW_CAPTION);
        setResizable(false);

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
        ipArea = new JTextField(client.getSocketIP());
        portArea = new JTextField(client.getSocketPort());
        addressPanel.add(ipArea);
        addressPanel.add(portArea);
        return addressPanel;
    }

    Component createUserPanel() {
        userPanel = new JPanel(new GridLayout(1, 3));
        nameArea = new JTextField(client.getUserName());
        passwordArea = new JPasswordField(client.getUserPassword());

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
        JScrollPane scroll = new JScrollPane(messagesField);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topPanel.add(scroll);
        return scroll;
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
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        return sendButton;
    }

    @Override
    public void login() {
        client.setUserName(nameArea.getText());
        client.setUserPassword(passwordArea.getText());
        client.setSocketIP(ipArea.getText());
        client.setSocketPort(portArea.getText());
        client.connect();
        takeConnectedView();
    }

    @Override
    public void sendMessage() {
        if (client.isConnected()) {
            client.sendMessage(inputField.getText());
            inputField.setText("");
        }
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

    }
}
