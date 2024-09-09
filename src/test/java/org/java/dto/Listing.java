package org.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Listing(
    @JsonProperty("name")
    String name,
    @JsonProperty("price")
    Integer price,
    @JsonProperty("sellerId")
    Integer sellerId,
    @JsonProperty("statistics")
    Statistics statistics
) {
    public record Statistics(
        @JsonProperty("contacts")
        Integer contacts,
        @JsonProperty("likes")
        Integer likes,
        @JsonProperty("viewCount")
        Integer viewCount
    ) {
    }
}
