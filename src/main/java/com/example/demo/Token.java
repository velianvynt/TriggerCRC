package com.example.demo;

public class Token {
    private String env_name;
    private String consumer_key;
    private String consumer_secret;
    private String access_token;
    private String access_secret;

    public Token(String env_name, String consumer_key, String consumer_secret, String access_token, String access_secret) {
        this.env_name = env_name;
        this.consumer_key = consumer_key;
        this.consumer_secret = consumer_secret;
        this.access_token = access_token;
        this.access_secret = access_secret;
    }

    public Token() {
    }

    public String getEnv_name() {
        return env_name;
    }

    public void setEnv_name(String env_name) {
        this.env_name = env_name;
    }

    public String getConsumer_key() {
        return consumer_key;
    }

    public void setConsumer_key(String consumer_key) {
        this.consumer_key = consumer_key;
    }

    public String getConsumer_secret() {
        return consumer_secret;
    }

    public void setConsumer_secret(String consumer_secret) {
        this.consumer_secret = consumer_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_secret() {
        return access_secret;
    }

    public void setAccess_secret(String access_secret) {
        this.access_secret = access_secret;
    }
}
