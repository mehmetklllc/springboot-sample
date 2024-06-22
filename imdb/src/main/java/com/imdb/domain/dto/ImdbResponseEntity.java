package com.imdb.domain.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ImdbResponseEntity {

    public static ResponseEntity<Object> response(String code, String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("responseCode", code);
        map.put("responseMessage", message);
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map, status);
    }
}
