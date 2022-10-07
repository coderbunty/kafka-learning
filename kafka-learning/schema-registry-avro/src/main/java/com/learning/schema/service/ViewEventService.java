package com.learning.schema.service;

import com.learning.schema.data.RawViewEvent;

import java.util.List;

public interface ViewEventService {

  void processEvent(RawViewEvent rawViewEvent);

  void processEvents(List<RawViewEvent> rawViewEvent);
}
