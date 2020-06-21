package com.agnihotri.planttester;

import android.content.res.Resources;

import com.agnihotri.planttester.dao.NetworkDAO;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestNetworkDAO {

    NetworkDAO networkDAO;

    @Before
    public void setup(){
        networkDAO = new NetworkDAO();
    }

    @Test
    public void testNetworkDAO_fetchShouldSucceedWhenGivenValidURI() throws IOException {
//        String testURI = "http://localhost:8089/";
//        Resources resources = getResources();
        String plantToken = "RedBud";
        String apiToken = "YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09";
        String testURI = "https://trefle.io/api/plants?q=" + plantToken + "&token=" + apiToken;
        String result = networkDAO.fetch(testURI);
        System.out.println("Fetch Result : " + result);
        String expectedJsonString = "[{\"common_name\":\"eastern redbud\",\"complete_data\":true,\"id\":118744,\"link\":\"http://trefle.io/api/plants/118744\",\"scientific_name\":\"Cercis canadensis\",\"slug\":\"cercis-canadensis\"},{\"common_name\":\"Chinese redbud\",\"complete_data\":false,\"id\":645929,\"link\":\"http://trefle.io/api/plants/645929\",\"scientific_name\":\"Cercis chinensis\",\"slug\":\"cercis-chinensis\"}]";
        assertEquals(expectedJsonString, result);

    }

}
