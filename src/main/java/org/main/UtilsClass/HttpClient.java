package org.main.UtilsClass;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpClient {


    public static String[] usingHTTPClientGET(String endpoint) throws IOException {
        String[] paths = new String[4];

        okhttp3.Response response = null;
        OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).build();
        Request request = new Request.Builder().url(endpoint).get().build();
        try {
            response = httpClient.newCall(request).execute();
            String string = response.body().string();
            response.body().close();
            System.out.println("response : " + string);
        } catch (IOException e) {
            System.err.println("Failed scraping");
            e.printStackTrace();
        }










        return paths;



    }


    public static void main(String[] args) throws IOException {


        HttpClient client = new HttpClient();

        usingHTTPClientGET("https://sheets.googleapis.com/v4/spreadsheets/1C2tp5m_HaJWRkk1Fc6cGJgr7lFeHHD_zj4_nCGn07yY/values/Sheet1!A1:D5?key=AIzaSyCm0iM9CZjxUGy7TUbz_JsK2F89dKRy8fA");





    }







}
