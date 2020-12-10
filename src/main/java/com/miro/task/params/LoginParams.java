package com.miro.task.params;



public class LoginParams {

    private String workEmail;
    private String password;


    public LoginParams(String workEmail, String password) {

        this.workEmail = workEmail;
        this.password = password;
    }


    public String getWorkEmail() {
        return workEmail;
    }

    public String getPassword(){ return password;}
}
