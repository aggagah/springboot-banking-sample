package com.aggagah.banking.dto;

public class ErrorResponse {
    private String status;
    private int status_code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(String status, int status_code, String message) {
        this.status = status;
        this.status_code = status_code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
