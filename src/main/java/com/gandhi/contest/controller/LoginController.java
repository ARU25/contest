package com.gandhi.contest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam("email")String email,
                                @RequestParam("password") String password,
                                HttpSession session){

        if (email.equalsIgnoreCase("admin@gmail.com") && password.equals("admin")) {
            return ResponseEntity.accepted().build();
        }

        // TODO change to 'forbidden' using authenticator later
        return ResponseEntity.badRequest().build();

    }
}
