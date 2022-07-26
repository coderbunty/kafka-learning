package com.meesho.serviceThree.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DummyApiController {

    @GetMapping(path = "/v1/test/level-three-data")
    public ResponseEntity<String> getTestData() {
        log.info("Starting execution of serviceThree api");
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }
}
