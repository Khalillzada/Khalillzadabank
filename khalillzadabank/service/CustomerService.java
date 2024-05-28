package com.example.khalillzadabank.service;

import com.example.khalillzadabank.dto.response.RespCustomer;
import com.example.khalillzadabank.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> customerList();

    Response<RespCustomer> customerById(Long id);

    Response deleteCustomer(Long id);
}
