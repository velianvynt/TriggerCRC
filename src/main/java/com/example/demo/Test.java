package com.example.demo;

import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.net.MethodType;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

public class Test {
    private OAuthConsumer oAuthConsumer;

    public Test() {
        String consumerKey = "lBWSZTAun2Z8KNOV9dw5GNV90";
        String consumerSecret = "UNe27IXOTCgJZIl9I42OnxpPlCOn2FHEqWg58zbY1bOA0VzxxW";
        String accessToken = "1437364297626124288-V5teBjySgWBzQOE2EjnzNRDZQjl4A2";
        String accessTokenSecret = "9QrBRG3b51unrYx5BdFGG7ckhTr43qUFWUQ7K0uTzbgVl";

        setupContext(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    public void setupContext(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        oAuthConsumer.setTokenWithSecret(accessToken, accessTokenSecret);
        oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
    }

    public void authorize(HttpRequestBase httpRequest) throws FMSException {
        try {
            oAuthConsumer.sign(httpRequest);
        } catch (OAuthMessageSignerException e) {
            throw new FMSException(e);
        } catch (OAuthExpectationFailedException e) {
            throw new FMSException(e);
        } catch (OAuthCommunicationException e) {
            throw new FMSException(e);
        }
    }

    public void executeGetRequest(String customURIString){
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8");

        HttpRequestBase httpRequest = null;
        URI uri = null;

        try {
            uri = new URI(customURIString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String methodtype = "GET";

        if (methodtype.equals(MethodType.GET.toString())) {
            httpRequest = new HttpGet(uri);
        }

        httpRequest.addHeader("content-type", "application/xml");
        httpRequest.addHeader("Accept","application/xml");

        try {
            authorize(httpRequest);
        } catch (FMSException e) {
            e.printStackTrace();
        }

        HttpResponse httpResponse = null;
        try {
            HttpHost target = new HttpHost(uri.getHost(), -1, uri.getScheme());
            httpResponse = client.execute(target, httpRequest);
            System.out.println("Connection status : " + httpResponse.getStatusLine());

            InputStream inputStream = httpResponse.getEntity().getContent();

            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            String output = writer.toString();
//            JSONObject myObject = new JSONObject(output);
//            JSONObject body = myObject.getJSONObject("body");
//            JSONArray array = body.getJSONArray("array");
//            JSONObject value = array.getJSONObject(0);
//            String id = value.getString("id");
//
//            System.out.println(id);
            System.out.println(writer);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Test tst = new Test();
        tst.executeGetRequest("https://api.twitter.com/1.1/account_activity/all/deveagle1/webhooks.json");
    }
}
