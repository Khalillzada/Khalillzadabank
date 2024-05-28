package com.example.khalillzadabank.exception;

import com.example.khalillzadabank.dto.response.RespStatus;

public class BankException extends RuntimeException{

private Integer code;
    public BankException(Integer code, String message){
        super(message);
        this.code=code;
    }
    public Integer getCode(){
        return code;
    }
}
