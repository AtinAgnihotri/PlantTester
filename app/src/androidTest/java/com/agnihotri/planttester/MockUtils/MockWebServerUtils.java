package com.agnihotri.planttester.MockUtils;

import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockWebServerUtils {
    public static final Dispatcher DISPATCHER = new Dispatcher() {

        @Override
        public MockResponse dispatch (RecordedRequest request) throws InterruptedException {
            System.out.println("# Request Path : "+ request.getPath() + " #");
            switch (request.getPath()) {
                case "/api/v1/plants?q=Redbud&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getRedBudJsonString()).setResponseCode(200);
                case "/api/v1/plants?q=hybrid%20oak&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getHybridOakJsonString()).setResponseCode(200);
//                case "api/v1/plants?q=asdhagljkdfhbjgnn&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
//                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
            }
            return new MockResponse().setBody(getGarbageValueJsonString()).setResponseCode(200);
        }
    };

    public static void processPlantRequests(MockWebServer server) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        String requestPath = request.getPath();
        System.out.println(requestPath);

    }

    private static String getRedBudJsonString(){
        return  MockJsonStrings.REDBUD_JSON_STRING;
    }

    private static String getHybridOakJsonString(){
        return  MockJsonStrings.HYBRID_OAK_JSON_STRING;
    }

    private static String getGarbageValueJsonString(){
        return MockJsonStrings.GARBAGE_VALUE_JSON_STRING;
    }

    public static MockWebServer startNewServer() throws IOException, GeneralSecurityException {
        MockWebServer server = new MockWebServer();
        server.start(8080);
//        server.useHttps(new MockSslContextBuilder("localhost").build().getSocketFactory(), false);
        server.setDispatcher(DISPATCHER);
        return server;
    }


}