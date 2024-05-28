package com.example.khalillzadabank.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class Response <T>  {
    @JsonProperty("response")
    private T t;
   private RespStatus status;


