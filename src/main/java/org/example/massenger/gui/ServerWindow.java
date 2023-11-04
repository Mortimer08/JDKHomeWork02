package org.example.massenger.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.massenger.program.Server;
import org.example.massenger.ui.ServerView;

public class ServerWindow extends JFrame implements ServerView {
    public static final String WINDOW_CAPTION = "Chat server";
    private static final int WINDOW_HEIGHT = 235;
    private static final int WINDOW_WIDTH = 350;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextArea textArea;
    private JButton btnStart;
    private JButton btnStop;
    private static final String BTN_START_CAPTION = "Start";
    private static final String BTN_STOP_CAPTION = "Stop";
    Server server;

    public ServerWindow(Server server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 500);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(WINDOW_CAPTION);
        setResizable(false);
        add(createTopPanel(), BorderLayout.NORTH);
        add(createBottomPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    Component createTopPanel() {
        topPanel = new JPanel(new GridLayout(1, 1));
        textArea = new JTextArea(12, 25);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        topPanel.add(scroll);
        return topPanel;
    }

    Component createBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(createStartBtn());
        bottomPanel.add(createStopBtn());
        return bottomPanel;
    }

    Component createStartBtn() {
        btnStart = new JButton(BTN_START_CAPTION);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startServer();
            }
        });
        return btnStart;
    }

    Component createStopBtn() {
        btnStop = new JButton(BTN_STOP_CAPTION);
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stopServer();
            }
        });
        return btnStop;
    }

    public void startServer() {
        System.out.println("Server started");
        server.start();
    }

    public void stopServer() {
        server.stop();
    }

    @Override
    public void print(String text) {
        textArea.append(text + "\n");
    }

    @Override
    public String getMessages() {
        return textArea.getText();
    }
}
