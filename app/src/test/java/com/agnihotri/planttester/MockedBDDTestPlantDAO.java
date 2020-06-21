package com.agnihotri.planttester;

import com.agnihotri.planttester.Utils.MockWebServerUtils;
import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MockedBDDTestPlantDAO {

    MockWebServer server;
    HttpUrl baseUrl;
    IPlantDAO plantDAO;
    List<PlantDTO> plants;

    @Before
    public void setupMockedTests() throws IOException {
        server = MockWebServerUtils.startNewServer();
        baseUrl = server.url("api/plants");
    }

    @Test
    public void testMockWebServerIsWorking() throws IOException, JSONException, InterruptedException {
        IPlantDAO plantDAO = new PlantDAO(baseUrl.toString());
        List<PlantDTO> plants = plantDAO.fetchPlants("Redbud");
        assertNotEquals(plants.size(), 0);
    }

    @Test
    public void testPlantDAO_fetchShouldReturnResultsForRedbud() throws IOException, JSONException, InterruptedException {
        fetchForFilterShouldReturnAtLeastOneGenusSpecies("Redbud", "cercis", "canadensis");

    }

    @Test
    public void testPlantDAO_fetchShouldReturnAtLeastTwoResultsForHybridOak() throws IOException, JSONException{
        fetchForFilterShouldReturnAtLeastOneGenusSpecies("hybrid oak", "quercus", "×beckyae");
        fetchForFilterShouldReturnAtLeastOneGenusSpecies("hybrid oak", "quercus", "×benderi");
    }

    @Test
    public void testPlantDAO_fetchShouldReturnZeroResultsForGarbageValue() throws IOException, JSONException{
        fetchForFilterShouldReturnZeroResults("asdhagljkdfhbjgnn");
    }

    private void fetchForFilterShouldReturnAtLeastOneGenusSpecies(String filter, String genus, String species) throws IOException, JSONException {//, InterruptedException {
        givenPlantDAOIsInitialised();
        whenSearchForFilter(filter);
        thenVerifyAtLeastOneGenusSpecies(genus, species);
    }

    private void fetchForFilterShouldReturnZeroResults(String filter) throws IOException, JSONException {
        givenPlantDAOIsInitialised();
        whenSearchForFilter(filter);
        thenVerifyZeroResults();
    }

    private void givenPlantDAOIsInitialised() {
        plantDAO = new PlantDAO(baseUrl.toString());
    }

    private void whenSearchForFilter(String filter) throws IOException, JSONException {

        plants = plantDAO.fetchPlants(filter);
    }

    private void thenVerifyAtLeastOneGenusSpecies(String testGenus, String testSpecies) {
        boolean genusSpeciesFound = false;
        for (PlantDTO plant : plants) {
            System.out.println(plant.getGenus().toLowerCase() + " : " + plant.getSpecies().toLowerCase());
            if (plant.getGenus().toLowerCase().contains(testGenus) && plant.getSpecies().toLowerCase().contains(testSpecies)){
                genusSpeciesFound = true;
            }
        }

        assertTrue(genusSpeciesFound);

    }

    private void thenVerifyZeroResults() {
        int size = plants.size();

        assertEquals(size, 0);

    }


    @After
    public void tearDownMockedTests() throws IOException {
        server.shutdown();
    }

}
