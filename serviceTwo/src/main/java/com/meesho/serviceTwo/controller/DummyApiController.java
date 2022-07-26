package com.meesho.serviceTwo.controller;

import com.meesho.serviceTwo.service.DummyService;
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

    @GetMapping(path = "/v1/test/level-two-data")
    public ResponseEntity<String> getTestData() {
        log.info("Starting execution of serviceTwo api.");
        String result = service.getData();
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
