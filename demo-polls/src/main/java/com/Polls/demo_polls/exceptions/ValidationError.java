package com.Polls.demo_polls.exceptions;

public class ValidationError {
    private String code;
    private String message;

    public ValidationError(String code, String defaultMessage) {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
