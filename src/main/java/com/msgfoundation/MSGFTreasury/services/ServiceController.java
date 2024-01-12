package com.msgfoundation.MSGFTreasury.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @GetMapping("/controller")
    public String paymentSuccessfully(){
        System.out.println("CONTROLLER: The payment was made");
        return "CONTROLLER: The payment was made";
    }
}
