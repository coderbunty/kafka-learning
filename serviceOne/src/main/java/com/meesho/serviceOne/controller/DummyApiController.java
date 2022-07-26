package com.meesho.serviceOne.controller;

import com.meesho.serviceOne.service.DummyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DummyApiController {

    @Autowired
    private DummyService service;

    @GetMapping(path = "/v1/test/data")
    public ResponseEntity<String> getTestData() {
        log.info("Starting execution of serviceOne api");
        String response = service.getData();
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
