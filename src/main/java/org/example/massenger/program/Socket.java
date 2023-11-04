package org.example.massenger.program;

public class Socket {
    private String ip;
    private Integer port;
    private String defaultIP = "127.0.0.1";
    private Integer defaultPort = 8080;

    public Socket() {
        ip = defaultIP;
        port = defaultPort;
    }

    public String getIp() {
        return ip;

    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {

        return String.valueOf(port);
    }

    public void setPort(String port) {
        try {
            this.port = Integer.valueOf(port);
        } catch (Exception e) {
            System.out.println("Wrong value");
        }

    }
}
