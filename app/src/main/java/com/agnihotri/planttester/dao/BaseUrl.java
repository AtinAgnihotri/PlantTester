package com.agnihotri.planttester.dao;

import com.agnihotri.planttester.BuildConfig;

public class BaseUrl {
    private static final boolean useMockBaseUrl = BuildConfig.DEBUG && BuildConfig.BUILD_TYPE.equals("debug");
    private static String baseUrl = "https://trefle.io/api/v1/plants/search";
    private static String mockBaseUrl = "http://localhost:8080/api/v1/plants/search";

    public static String getBaseUrl(){
        System.out.println("# Use Mock URL : " + useMockBaseUrl + " #");
        if (useMockBaseUrl)
            return mockBaseUrl;
        else
            return baseUrl;
    }

    public static void setBaseUrl(String url){
        baseUrl = url;
    }
}
