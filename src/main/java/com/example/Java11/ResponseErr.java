package com.example.Java11;

public class ResponseErr {
    private String message;

    public ResponseErr(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
