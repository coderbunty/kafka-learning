package com.learning.schema.service.impl;

import com.learning.schema.avro.Properties;
import com.learning.schema.avro.ViewEvent;
import com.learning.schema.data.RawViewEvent;
import com.learning.schema.producers.ViewEventProducer;
import com.learning.schema.service.ViewEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewEventServiceImpl implements ViewEventService {

  @Autowired private ViewEventProducer producer;

  @Override
  public void processEvent(RawViewEvent rawViewEvent) {
    producer.send(mapToViewEvent(rawViewEvent));
  }

  @Override
  public void processEvents(List<RawViewEvent> rawViewEvents) {
    List<ViewEvent> viewEventList =
        rawViewEvents.stream().map(this::mapToViewEvent).collect(Collectors.toList());
    producer.sendBatch(viewEventList);
  }

  private ViewEvent mapToViewEvent(RawViewEvent rawViewEvent) {
    Properties properties =
        Properties.newBuilder()
            .setEntityId(rawViewEvent.getProperties().getEntityId())
            .setGroupId(rawViewEvent.getProperties().getGroupId())
            .setGroupPosition(rawViewEvent.getProperties().getGroupPosition())
            .setGroupTitle(rawViewEvent.getProperties().getGroupTitle())
            .setPosition(rawViewEvent.getProperties().getPosition())
            .setEntityType(rawViewEvent.getProperties().getEntityType())
            .setScreen(rawViewEvent.getProperties().getScreen())
            .setScreenId(rawViewEvent.getProperties().getScreenId())
            .setOrigin(rawViewEvent.getProperties().getOrigin())
            .setSourceScreen(rawViewEvent.getProperties().getSourceScreen())
            .setOriginMetadata(rawViewEvent.getProperties().getOriginMetadata())
            .setPrimaryRealEstate(rawViewEvent.getProperties().getPrimaryRealEstate())
            .setAppVersionCode(rawViewEvent.getProperties().getAppVersionCode())
            .build();
    return ViewEvent.newBuilder()
        .setEventId(rawViewEvent.getEventId())
        .setEventName(rawViewEvent.getEventName())
        .setEventTimestamp(rawViewEvent.getEventTimestamp())
        .setSessionId(rawViewEvent.getSessionId())
        .setUserId(rawViewEvent.getUserId())
        .setProperties(properties)
        .build();
  }
}
