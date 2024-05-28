package com.example.khalillzadabank.controller;

import com.example.khalillzadabank.dto.response.RespCustomer;
import com.example.khalillzadabank.dto.response.Response;
import com.example.khalillzadabank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/list")
    public Response<List<RespCustomer>> customerList() {return customerService.customerList();
    }

    @GetMapping("byId/{id}")
    public Response<RespCustomer> customerById(@PathVariable Long id) {return customerService.customerById(id);
    }

    @PostMapping("/create")
    public Response createCustomer(@RequestBody ReqCustomer reqCustomer) {
        retrun customerService.createCustomer(reqCustomer);
    }

    @PutMapping("/update")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        retrun customerService.updateCustomer(reqCustomer);
    }
    @PutMapping("/delete")
    public Response deleteCustomer(@RequestParam Long id) {
        retrun customerService.deleteCustomer(id);
    }
}