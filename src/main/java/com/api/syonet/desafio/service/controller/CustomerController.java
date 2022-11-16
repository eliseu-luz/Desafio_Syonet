package com.api.syonet.desafio.service.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.syonet.desafio.model.Customer;
import com.api.syonet.desafio.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping
    public void save( @RequestBody Customer customer ){
       this.customerService.save( customer );
    }
}
