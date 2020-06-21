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
                case "/api/plants?q=Redbud&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getRedBudJsonString());
                case "/api/plants?q=hybrid%20oak&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
                    return new MockResponse().setBody(getHybridOakJsonString());
//                case "api/plants?q=asdhagljkdfhbjgnn&token=YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09":
//                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
            }
            return new MockResponse().setBody(getGarbageValueJsonString());
        }
    };

    public static void processPlantRequests(MockWebServer server) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        String requestPath = request.getPath();
        System.out.println(requestPath);

    }

    private static String getRedBudJsonString(){
        return  "[{\"common_name\":\"eastern redbud\",\"complete_data\":true,\"id\":118744,\"link\":\"http://trefle.io/api/plants/118744\",\"scientific_name\":\"Cercis canadensis\",\"slug\":\"cercis-canadensis\"},{\"common_name\":\"Chinese redbud\",\"complete_data\":false,\"id\":645929,\"link\":\"http://trefle.io/api/plants/645929\",\"scientific_name\":\"Cercis chinensis\",\"slug\":\"cercis-chinensis\"}]";
    }

    private static String getHybridOakJsonString(){
        return  "[{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173028,\"link\":\"http://trefle.io/api/plants/173028\",\"scientific_name\":\"Quercus ×beckyae\",\"slug\":\"quercus-×beckyae\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173029,\"link\":\"http://trefle.io/api/plants/173029\",\"scientific_name\":\"Quercus ×benderi\",\"slug\":\"quercus-×benderi\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173030,\"link\":\"http://trefle.io/api/plants/173030\",\"scientific_name\":\"Quercus ×bernardiensis\",\"slug\":\"quercus-×bernardiensis\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173031,\"link\":\"http://trefle.io/api/plants/173031\",\"scientific_name\":\"Quercus ×bimundorum\",\"slug\":\"quercus-×bimundorum\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173032,\"link\":\"http://trefle.io/api/plants/173032\",\"scientific_name\":\"Quercus ×blufftonensis\",\"slug\":\"quercus-×blufftonensis\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173033,\"link\":\"http://trefle.io/api/plants/173033\",\"scientific_name\":\"Quercus ×brittonii\",\"slug\":\"quercus-×brittonii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173036,\"link\":\"http://trefle.io/api/plants/173036\",\"scientific_name\":\"Quercus ×byarsii\",\"slug\":\"quercus-×byarsii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173037,\"link\":\"http://trefle.io/api/plants/173037\",\"scientific_name\":\"Quercus ×caduca\",\"slug\":\"quercus-×caduca\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173038,\"link\":\"http://trefle.io/api/plants/173038\",\"scientific_name\":\"Quercus ×caesariensis\",\"slug\":\"quercus-×caesariensis\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173039,\"link\":\"http://trefle.io/api/plants/173039\",\"scientific_name\":\"Quercus ×capesii\",\"slug\":\"quercus-×capesii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173040,\"link\":\"http://trefle.io/api/plants/173040\",\"scientific_name\":\"Quercus ×cocksii\",\"slug\":\"quercus-×cocksii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173041,\"link\":\"http://trefle.io/api/plants/173041\",\"scientific_name\":\"Quercus ×columnaris\",\"slug\":\"quercus-×columnaris\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173045,\"link\":\"http://trefle.io/api/plants/173045\",\"scientific_name\":\"Quercus ×deamii\",\"slug\":\"quercus-×deamii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173047,\"link\":\"http://trefle.io/api/plants/173047\",\"scientific_name\":\"Quercus ×demareei\",\"slug\":\"quercus-×demareei\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173048,\"link\":\"http://trefle.io/api/plants/173048\",\"scientific_name\":\"Quercus ×discreta\",\"slug\":\"quercus-×discreta\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173049,\"link\":\"http://trefle.io/api/plants/173049\",\"scientific_name\":\"Quercus ×diversiloba\",\"slug\":\"quercus-×diversiloba\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173050,\"link\":\"http://trefle.io/api/plants/173050\",\"scientific_name\":\"Quercus ×egglestonii\",\"slug\":\"quercus-×egglestonii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173052,\"link\":\"http://trefle.io/api/plants/173052\",\"scientific_name\":\"Quercus ×eplingii\",\"slug\":\"quercus-×eplingii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173053,\"link\":\"http://trefle.io/api/plants/173053\",\"scientific_name\":\"Quercus ×exacta\",\"slug\":\"quercus-×exacta\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173054,\"link\":\"http://trefle.io/api/plants/173054\",\"scientific_name\":\"Quercus ×faxonii\",\"slug\":\"quercus-×faxonii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173055,\"link\":\"http://trefle.io/api/plants/173055\",\"scientific_name\":\"Quercus ×fernaldii\",\"slug\":\"quercus-×fernaldii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173058,\"link\":\"http://trefle.io/api/plants/173058\",\"scientific_name\":\"Quercus ×filialis\",\"slug\":\"quercus-×filialis\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173062,\"link\":\"http://trefle.io/api/plants/173062\",\"scientific_name\":\"Quercus ×ganderi\",\"slug\":\"quercus-×ganderi\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173064,\"link\":\"http://trefle.io/api/plants/173064\",\"scientific_name\":\"Quercus ×garlandensis\",\"slug\":\"quercus-×garlandensis\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173065,\"link\":\"http://trefle.io/api/plants/173065\",\"scientific_name\":\"Quercus ×giffordii\",\"slug\":\"quercus-×giffordii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173066,\"link\":\"http://trefle.io/api/plants/173066\",\"scientific_name\":\"Quercus ×grandidentata\",\"slug\":\"quercus-×grandidentata\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173070,\"link\":\"http://trefle.io/api/plants/173070\",\"scientific_name\":\"Quercus ×hawkinsiae\",\"slug\":\"quercus-×hawkinsiae\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173073,\"link\":\"http://trefle.io/api/plants/173073\",\"scientific_name\":\"Quercus ×howellii\",\"slug\":\"quercus-×howellii\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173074,\"link\":\"http://trefle.io/api/plants/173074\",\"scientific_name\":\"Quercus ×humidicola\",\"slug\":\"quercus-×humidicola\"},{\"common_name\":\"hybrid oak\",\"complete_data\":false,\"id\":173075,\"link\":\"http://trefle.io/api/plants/173075\",\"scientific_name\":\"Quercus ×incomita\",\"slug\":\"quercus-×incomita\"}]";
    }

    private static String getGarbageValueJsonString(){
        return "[]";
    }

    public static MockWebServer startNewServer() throws IOException {
        MockWebServer server = new MockWebServer();
        server.start();
        server.setDispatcher(DISPATCHER);
        return server;
    }


}
