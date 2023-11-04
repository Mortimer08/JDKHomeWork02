package org.example.massenger.program;


public class User {
    private final String defaultName = "John Smith";
    private final String defaultPassword = "password";
    private String name;
    private String password;

    public User() {
        name = defaultName;
        password = defaultPassword;
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
