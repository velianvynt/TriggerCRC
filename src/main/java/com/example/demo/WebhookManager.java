package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import twitter4j.conf.Configuration;
import twitter4j.HttpParameter;
import twitter4j.HttpRequest;
import twitter4j.RequestMethod;
import twitter4j.auth.OAuthAuthorization;

public class WebhookManager {
    private final String file_name = "D:\\Docs\\token.xlsx";

    public Token getToken(String env) {
        Token tempList = new Token();
        try {
            FileInputStream excelFile = new FileInputStream(new File(file_name));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet worksheet = workbook.getSheetAt(0);

            for (int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
                Row row = worksheet.getRow(i);
                tempList.setEnv_name(row.getCell(0).getStringCellValue());
                if (tempList.getEnv_name().equals(env)) {
                    tempList.setConsumer_key(row.getCell(1).getStringCellValue());
                    tempList.setConsumer_secret(row.getCell(2).getStringCellValue());
                    tempList.setAccess_token(row.getCell(3).getStringCellValue());
                    tempList.setAccess_secret(row.getCell(4).getStringCellValue());
                    return tempList;
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }
}
