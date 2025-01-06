package fr.imt.flinois.projetALEC.interfaces.rest.exceptions;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private String timestamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
