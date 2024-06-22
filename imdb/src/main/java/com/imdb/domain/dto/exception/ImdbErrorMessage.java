package com.imdb.domain.dto.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ImdbErrorMessage {
    private Date timestamp;
    private String message;
    private Object data;

    public ImdbErrorMessage(Date timestamp, String message,Object data) {
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }
}
