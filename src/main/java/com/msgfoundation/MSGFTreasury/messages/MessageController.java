package com.msgfoundation.MSGFTreasury.messages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class MessageController {

    @GetMapping("/message")
    public String paymentMessage(){
        System.out.println("MESSAGE: The payment was made.");
        return "MESSAGE: The payment was made";
    }

}
