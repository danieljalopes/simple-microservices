package com.bueno.account.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;

@RestController
@Slf4j
@RequestMapping("/api/v1/hello")
public class HelloController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGreetingFromHost() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        String greetingMessage = "Hello from " + hostname;
        log.info(greetingMessage);
        return ResponseEntity.ok(Collections.singletonMap("message", greetingMessage));
    }
}
