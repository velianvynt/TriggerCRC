package com.example.demo.controller;

//import com.abhaybd.twitter4jwebhook.WebhookManager;
import com.example.demo.StatusCode;
import com.example.demo.Token;
import com.example.demo.WebhookInfo;
import com.example.demo.WebhookManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import lombok.RequiredArgsConstructor;
//import twitter4j.auth.RequestToken;
import io.github.redouane59.twitter.dto.others.RequestToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping
public class WebhookController {

    private final String WebhookEndpoint
            = "https://api.twitter.com/1.1/account_activity/all/%s/webhooks.json";
    private final String file_name = "D:\\Docs\\token.xlsx";

//    private final String triggerCRC
//            = "https://api.twitter.com/1.1/account_activity/all/:envName/webhooks/:webhookId.json";

//    private final CloseableHttpClient httpClient;
    private final ObjectMapper mapper;
//    private final String env = "halosmm";

//    public WebhookController(String env) {
//        this.env = Objects.requireNonNull(env);
//        mapper = new ObjectMapper();
//    }

//    @GetMapping(value = "request-token")
//    public String requestToken() {
//        final RequestToken requestToken = twitterClient.getOauth1Token();
//        return requestTokenURL + requestToken.getOauthToken();
//    }

//    @GetMapping(value = "/get_webhook_info", produces = MediaType.TEXT_HTML_VALUE)
//    public long getWebhook() {
////        Twitter twitter = TwitterFactory.getSingleton();
////        WebhookManager manager = new WebhookManager(twitter.getConfiguration(), "deveagle1");
////        WebhookInfo info = manager.getWebhookInfo();
////        System.out.println(info.getID());
////        System.out.println(info.isValid());
////        return info.getID();
//        TwitterClient client = new TwitterClient();
//        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
//                .accessToken("1437364297626124288-V5teBjySgWBzQOE2EjnzNRDZQjl4A2")
//                .accessTokenSecret("9QrBRG3b51unrYx5BdFGG7ckhTr43qUFWUQ7K0uTzbgVl")
//                .apiKey("lBWSZTAun2Z8KNOV9dw5GNV90")
//                .apiSecretKey("UNe27IXOTCgJZIl9I42OnxpPlCOn2FHEqWg58zbY1bOA0VzxxW")
//                .build());
//
//        String url = "https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks.json";
//        final String webhookurl = twitterClient.getRequestHelperV1()
////                .getRequest(url, null, WebhookInfo.class).get();
//
//
//    }

//    @GetMapping("get_value")
//    public WebhookInfo getWebhookInfo() {
//        try {
//            String endpoint = String.format("https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks.json");
//            HttpGet request = new HttpGet(endpoint);
//            request.addHeader("Authorization", "Bearer " + "1437364297626124288-V5teBjySgWBzQOE2EjnzNRDZQjl4A2");
//            CloseableHttpResponse response = httpClient.execute(request);
//            WebhookInfo[] webhooks = mapper.readValue(response.getEntity().getContent(), WebhookInfo[].class);
//            return Arrays.stream(webhooks).filter(WebhookInfo::isValid).findAny().orElse(null);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping(value="/getWebhookId")
    public WebhookInfo getValue(@RequestParam("env") String env) {

//        Token token = getToken(env);
//        Token token = new Token();

//        token.getAccess_token();
//        token.toString();
//        System.out.println(token.);
//        for (token: Object)
//        String consumer_key = token.getConsumer_key();
//        String consumer_secret = token.getConsumer_secret();
//        String access_token = token.getAccess_token();
//        String access_secret= token.getAccess_secret();

        String consumer_key = "";
        String consumer_secret = "";
        String access_token = "";
        String access_secret= "";

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key,
                consumer_secret);
        consumer.setTokenWithSecret(access_token, access_secret);

        String uri = String.format(WebhookEndpoint, env);

//        String uri = "https://api.twitter.com/1.1/account_activity/all/halosmm/webhooks.json";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(uri);
        try {
            consumer.sign(httpget);
        } catch (OAuthMessageSignerException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthExpectationFailedException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthCommunicationException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            HttpResponse response = httpclient.execute(httpget);
            WebhookInfo[] webhooks = mapper.readValue(response.getEntity().getContent(), WebhookInfo[].class);
            return Arrays.stream(webhooks).filter(WebhookInfo::isValid).findAny().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value="/getWebhookId2")
    public WebhookInfo getWebhookInfo(@RequestParam String env) {
//        Token token = getToken(env);
        String consumer_key = "token.getConsumer_key();";
//        System.out.println(consumer_key);
        String consumer_secret = "LerET2V4PFzKqohU6KUQJWZ0PfOiFo9sv1HnKihU2HKqLy4O0d";
        String access_token = "620977821-3G2GKEIqLxD4eKF2b4pLUnFKg0s1ygMewq5U1LL4";
        String access_secret= "PxCtCpG7ZArXVFb6ZsOH2fvQIK6fN5UjH2xwKslqVdyFo";

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key,
                consumer_secret);
        consumer.setTokenWithSecret(access_token, access_secret);

        String uri = "https://api.twitter.com/1.1/account_activity/all/halosmm/webhooks.json";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(uri);
        try {
            consumer.sign(httpget);
        } catch (OAuthMessageSignerException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthExpectationFailedException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthCommunicationException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            HttpResponse response = httpclient.execute(httpget);
            WebhookInfo[] webhooks = mapper.readValue(response.getEntity().getContent(), WebhookInfo[].class);
            return Arrays.stream(webhooks).filter(WebhookInfo::isValid).findAny().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/triggercrc")
    public StatusCode triggerCRC() {
        String consumer_key = "lBWSZTAun2Z8KNOV9dw5GNV90";
        String consumer_secret = "UNe27IXOTCgJZIl9I42OnxpPlCOn2FHEqWg58zbY1bOA0VzxxW";
        String access_token = "1437364297626124288-V5teBjySgWBzQOE2EjnzNRDZQjl4A2";
        String access_secret= "9QrBRG3b51unrYx5BdFGG7ckhTr43qUFWUQ7K0uTzbgVl";

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key,
                consumer_secret);
        consumer.setTokenWithSecret(access_token, access_secret);

        String uri = "https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks/1620321083147841536.json";

        HttpClient httpclient = new DefaultHttpClient();
        // Send a PUT request to the API endpoint
        HttpPut request = new HttpPut(uri);
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthExpectationFailedException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OAuthCommunicationException ex) {
            Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//            WebhookInfo info = getWebhookInfo();
//            if (info == null) return StatusCode.createError("Error: No webhook for which to trigger CRC");
//            long id = info.getID();

            HttpResponse response = httpclient.execute(request);
            return getStatusCode(response, 204);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StatusCode getStatusCode(HttpResponse response, int expectedStatus) {
        int status = response.getStatusLine().getStatusCode();
        String phrase = response.getStatusLine().getReasonPhrase();
        if (status == expectedStatus) {
            return StatusCode.createSuccess(status, phrase);
        } else {
            return StatusCode.createError(status, phrase);
        }
    }

    @GetMapping("get_token")
    public Map<String, String> getToken(String env) {
        Map<String, String> tempList = new HashMap<String, String>();
        Token tempList2 = new Token();
//        Token tempList2 = null;
        try {
            FileInputStream excelFile = new FileInputStream(new File(file_name));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//                tempList2 = new Token();
                Row row = worksheet.getRow(i);
                tempList2.setEnv_name(row.getCell(0).getStringCellValue());
//                tempList.setEnv_name(row.getCell(0).getStringCellValue());
//                System.out.println(typeof("dev"));
//                System.out.println(tempList.getEnv_name().equals(env));
//                System.out.println("deveagle1" === "deveagle1");
                if (tempList2.getEnv_name().equals(env)) {
//                    Row rowd = worksheet.getRow(i);
//                    tempList2.setConsumer_key(row.getCell(1).getStringCellValue());
//                    tempList2.setConsumer_secret(row.getCell(2).getStringCellValue());
//                    tempList2.setAccess_token(row.getCell(3).getStringCellValue());
//                    tempList2.setAccess_secret(row.getCell(4).getStringCellValue());
                    tempList.put("consumer_key", tempList2.getConsumer_key());
                tempList.put("consumer_secret", tempList2.getConsumer_secret());
                tempList.put("access_token", tempList2.getAccess_token());
                tempList.put("access_secret", tempList2.getAccess_secret());
//                tempList.add(tempList2);
                    return tempList;
                }


//                tempList.setConsumer_key(row.getCell(1).getStringCellValue());
//                tempList.setConsumer_secret(row.getCell(2).getStringCellValue());
//                tempList.setAccess_token(row.getCell(3).getStringCellValue());
//                tempList.setAccess_secret(row.getCell(4).getStringCellValue());

//                System.out.println(tempList.getEnv_name());
//
//                tempList.put("consumer_key", tempToken.getConsumer_key());
//                tempList.put("consumer_secret", tempToken.getConsumer_secret());
//                tempList.put("access_token", tempToken.getAccess_token());
//                tempList.put("access_secret", tempToken.getAccess_secret());
//                tempList.add(tempToken);

//                String consumer_key = tempList.get(0).getConsumer_key();
//                String consumer_secret = tempList.get(0).getConsumer_secret();
//                String access_token = tempList.get(0).getAccess_token();
//                String access_secret = tempList.get(0).getAccess_secret();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempList;
    }


}
