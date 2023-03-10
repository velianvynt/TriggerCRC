package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookInfo {
    private String id;
    private boolean valid;

    public long getID() {
        return Long.parseLong(id);
    }

    public boolean isValid() {
        return valid;
    }
}
