package com.meesh.app.controller;

import com.meesh.app.model.Coordinate;
import com.meesh.app.service.LocationCoordinateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

  @Autowired private LocationCoordinateService locationCoordinateService;

  @PostMapping(path = "/v1/location/coordinates")
  public ResponseEntity<Void> getLocationCoordinates(@RequestBody Coordinate coordinate) {
    log.info("Reading location coordinates");
    locationCoordinateService.processCoordinates(coordinate);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
