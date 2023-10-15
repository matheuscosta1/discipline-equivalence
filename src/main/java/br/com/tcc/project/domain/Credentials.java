package br.com.tcc.project.domain;

import java.io.Serializable;

public class Credentials implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public Credentials(){

    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
