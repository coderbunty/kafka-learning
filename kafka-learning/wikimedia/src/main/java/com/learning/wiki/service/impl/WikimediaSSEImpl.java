package com.learning.wiki.service.impl;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.learning.wiki.service.ServerSentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
public class WikimediaSSEImpl implements ServerSentEvent {

  @Autowired private EventHandler eventHandler;

  @Value("${WIKIMEDIA_SSE_URL}")
  private String wikimediaSseUrl;

  @Override
  public void read() {
    EventSource.Builder builder =
        new EventSource.Builder(eventHandler, URI.create(wikimediaSseUrl));
    EventSource eventSource = builder.build();
    eventSource.start();
  }
}
