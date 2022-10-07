package com.meesh.app.service.impl;

import com.meesh.app.model.Coordinate;
import com.meesh.app.producers.LocationCoordinateEventProducer;
import com.meesh.app.service.LocationCoordinateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationCoordinateServiceImpl implements LocationCoordinateService {

  @Autowired private LocationCoordinateEventProducer eventProducer;

  @Override
  public void processCoordinates(Coordinate coordinate) {
    eventProducer.publishEvent(coordinate.getLatitude() + "," + coordinate.getLongitude());
    log.info("API execution should finish before listener consumes the event.");
  }
}
