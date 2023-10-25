package org.example.massenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ServerWindow extends JFrame {


    ClientWindow clientWindow;

    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private static String fileName;

    public ServerWindow() {
        createClientWindow();
        fileName = "logfile";
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


  /*  private void startServer() {
        if (!serverRunning) {
            serverRunning = true;
            textArea.append(SERVER_STARTED_MESSAGE + "\n");
            clientWindow.setVisible(true);
            try {
                clientWindow.putMessagesInMessageField(readFromFile(fileName));
            } catch (IOException e) {
                System.out.println(e.getMessage());

            }

        } else {
            textArea.append(SERVER_IS_RUNNING + "\n");
        }
    }

    private void stopServer() throws IOException {
        if (serverRunning) {
            clientWindow.closeConnection();
            clientWindow.setVisible(false);
            bufferedWriter.close();
            serverRunning = false;
            textArea.append(SERVER_STOPPED_MESSAGE + "\n");
            clientWindow.setVisible(false);
        } else {
            textArea.append(SERVER_IS_STOPPED + "\n");
        }
    }*/

    private void createClientWindow() {
        clientWindow = new ClientWindow();
        clientWindow.setServer(this);
        clientWindow.setVisible(false);
    }

    protected void saveMessage(String message) {
        writeToFile(bufferedWriter, message);
    }

    private void writeToFile(BufferedWriter bufferedWriter, String message) {
        try {
            bufferedWriter.append(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        StringBuilder messages = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            messages.append(line + "\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return messages.toString();
    }
}

