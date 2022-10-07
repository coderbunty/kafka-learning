package com.learning.schema.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RawViewEvent {

  @JsonProperty("eventId")
  private String eventId;

  @JsonProperty("eventName")
  private String eventName;

  @JsonProperty("eventTimestamp")
  private Long eventTimestamp;

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("sessionId")
  private String sessionId;

  @JsonProperty("properties")
  private Properties properties;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Properties {

    @JsonProperty("entityId")
    private String entityId;

    @JsonProperty("groupId")
    private String groupId;

    @JsonProperty("groupPosition")
    private String groupPosition;

    @JsonProperty("groupTitle")
    private String groupTitle;

    @JsonProperty("position")
    private String position;

    @JsonProperty("entityType")
    private String entityType;

    @JsonProperty("screen")
    private String screen;

    @JsonProperty("screenId")
    private String screenId;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("sourceScreen")
    private String sourceScreen;

    @JsonProperty("originMetadata")
    private String originMetadata;

    @JsonProperty("productIds")
    private List<String> productIds;

    @JsonProperty("catalogIds")
    private List<String> catalogIds;

    @JsonProperty("primaryRealEstate")
    private String primaryRealEstate;

    @JsonProperty("appVersionCode")
    private String appVersionCode;
  }
}
