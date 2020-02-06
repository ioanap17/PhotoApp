package com.spring.microservices.ui.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    
    private Date timestamp;
    private String message;

    public ErrorMessage(Date date, String localizedMessage) {
        this.timestamp = date;
        this.message = localizedMessage;
    }
}
