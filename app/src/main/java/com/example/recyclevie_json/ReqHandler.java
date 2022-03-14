package com.example.recyclevie_json;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ReqHandler {

    private static final String TAG = ReqHandler.class.getSimpleName();


    public  String makeServiceCall(String requesturl) {
        String response = null;

        try {
            URL url = new URL(requesturl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = converStreamToString(in);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String converStreamToString(InputStream in) {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = br.readLine()) != null){
                sb.append(line).append('\n');

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
