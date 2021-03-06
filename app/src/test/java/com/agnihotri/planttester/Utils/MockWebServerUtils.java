package com.agnihotri.planttester.Utils;

import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

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

            switch (request.getPath()) {
                case "/api/plants/search?q=Redbud&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getRedBudJsonString()).setResponseCode(200);
                case "/api/plants/search?q=hybrid%20oak&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getHybridOakJsonString()).setResponseCode(200);
//                case "api/plants?q=asdhagljkdfhbjgnn&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
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

    public static MockWebServer startNewServer() throws IOException {
        MockWebServer server = new MockWebServer();
        server.start();
        server.setDispatcher(DISPATCHER);
        return server;
    }


}
