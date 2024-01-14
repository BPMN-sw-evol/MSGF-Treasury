package com.msgfoundation.MSGFTreasury.controller;

import com.msgfoundation.MSGFTreasury.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("/message")
    public String paymentMessage(){
        System.out.println("MESSAGE: The payment was made.");
        return "MESSAGE: The payment was made";
    }

    @GetMapping("/update/{codRequest}")
    public String paymentSuccessfully(@PathVariable Long codRequest) throws SQLException {
        paymentService.updatePayment(codRequest);
        return "CONTROLLER: The payment was made";
    }

}
