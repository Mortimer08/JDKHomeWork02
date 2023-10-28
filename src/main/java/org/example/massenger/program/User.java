package org.example.massenger.program;

import javax.lang.model.element.Name;

public class User {
    private String defaultName = "John Smith";
    private String defaultPassword = "password";
    private String name;
    private String password;
    public User(){
        name = defaultName;
        password = defaultPassword;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
}
