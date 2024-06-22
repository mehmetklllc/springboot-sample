package com.imdb.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImdbResponseCode implements Serializable {

    public static final ImdbResponseCode SUCCESS = new ImdbResponseCode("0", "general.result.info.success");
    public static final ImdbResponseCode SYSTEM_ERROR = new ImdbResponseCode("GNL-0001", "general.system.error");
    public static final ImdbResponseCode BAD_REQUEST = new ImdbResponseCode("GNL-0002", "general.error.bad.request");
    private final String code;
    private final String message;

    public ImdbResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
