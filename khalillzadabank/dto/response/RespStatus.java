package com.example.khalillzadabank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RespStatus {
    private Integer code;
    private String message;

    public static RespStatus getsuccessMessage() {

        return null;
    }
}