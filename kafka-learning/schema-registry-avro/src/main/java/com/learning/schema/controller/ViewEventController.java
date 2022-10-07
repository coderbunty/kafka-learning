package com.learning.schema.controller;

import com.learning.schema.data.RawViewEvent;
import com.learning.schema.service.ViewEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class ViewEventController {

  @Autowired private ViewEventService viewEventService;

  @PostMapping(path = "/v1/event/view")
  public ResponseEntity<Void> getLocationCoordinates(@RequestBody List<RawViewEvent> viewEvents) {
    log.info("Reading view event data");
    viewEventService.processEvents(viewEvents);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
