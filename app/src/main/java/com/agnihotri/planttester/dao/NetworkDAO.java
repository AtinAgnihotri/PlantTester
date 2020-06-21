package com.agnihotri.planttester.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkDAO implements INetworkDAO {

    @Override
    public String fetch(String uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // we're reading i/p stuff
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader( new InputStreamReader(in));

            // line we are reading
            String inputLine;

            // keep reading till EOL
            while((inputLine = bin.readLine()) != null){
                stringBuilder.append(inputLine);
            }
        } finally {
            urlConnection.disconnect();
        }

        return stringBuilder.toString();
    }
}
