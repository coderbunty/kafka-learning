package com.meesho.serviceTwo.service.impl;

import com.meesho.serviceTwo.service.DummyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DummyServiceImpl implements DummyService {

    @Autowired
    @Qualifier("testRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public String getData() {
        log.info("Execution started for calling downstream service");
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8092/v1/test/level-three-data", HttpMethod.GET, null, String.class);
        return response.getBody();
    }
}