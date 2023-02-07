package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuthService;
import io.github.redouane59.twitter.dto.others.BearerToken;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.stereotype.Service;
import twitter4j.conf.Configuration;

@Service
public class WebhookService {
//    private static final String WEBHOOK_ENDPOINT = "https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks.json";
//    private static final String BEARER_TOKEN_ENDPOINT = "https://api.twitter.com/oauth2/token";
//
////    private final String env;
//    private final CloseableHttpClient httpClient;
//    private final ObjectMapper mapper;
//    private String bearerToken = null;
//    private final Configuration configuration;
//
//    public WebhookService(String env, Configuration configuration) {
////        this.env = Objects.requireNonNull(env);
//        httpClient = HttpClients.createDefault();
//        mapper = new ObjectMapper();
//        this.configuration = Objects.requireNonNull(configuration);
//    }
//
//    public WebhookInfo getWebhookInfo() {
//        try {
//            String endpoint = String.format(WEBHOOK_ENDPOINT);
//            HttpGet request = new HttpGet(endpoint);
//
//            request.addHeader("Authorization", "Bearer" + getBearerToken());
//            CloseableHttpResponse response = httpClient.execute(request);
//            WebhookInfo[] webhooks = mapper.readValue(response.getEntity().getContent(), WebhookInfo[].class);
//            return Arrays.stream(webhooks).filter(WebhookInfo::isValid).findAny().orElse(null);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
////        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////        String url = "https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks.json";
////        String authorizationValue = "OAuth ";
////        conn.setRequestProperty("Authorization", authorizationValue);
////        conn.setDoOutput(true);
////        conn.setRequestMethod("GET");
//        OAuthService service = new ServiceBuilder()
//                .apiKey("lBWSZTAun2Z8KNOV9dw5GNV90")
//                .apiSecret("UNe27IXOTCgJZIl9I42OnxpPlCOn2FHEqWg58zbY1bOA0VzxxW")
//                .build();
//    }
//
//    private String getBearerToken() {
//        if (bearerToken == null) {
//            try {
//                HttpPost request = new HttpPost(BEARER_TOKEN_ENDPOINT);
//
////                String key = configuration.getOAuthConsumerKey();
////                String secret = configuration.getOAuthConsumerSecret();
//                String key = "lBWSZTAun2Z8KNOV9dw5GNV90";
//                String secret = "UNe27IXOTCgJZIl9I42OnxpPlCOn2FHEqWg58zbY1bOA0VzxxW";
//                UsernamePasswordCredentials cred = new UsernamePasswordCredentials(key, secret);
//                request.addHeader(new BasicScheme().authenticate(cred, request, null));
//
//                request.setEntity(new StringEntity("grant_type=client_credentials"));
//                request.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
//
//                CloseableHttpResponse response = httpClient.execute(request);
//                BearerToken token = mapper.readValue(response.getEntity().getContent(), BearerToken.class);
//
//                bearerToken = token.getAccessToken();
//            } catch(IOException | AuthenticationException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return bearerToken;
//    }
//



}
