package com.agnihotri.planttester;

import com.agnihotri.planttester.Utils.MockWebServerUtils;
import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.hamcrest.beans.HasPropertyWithValue;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

//  Hamcrest Imports
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class MockedServerBDDTestPlantDAO {
    /**
     * Where we run the same BDD Unit test, but the responses are mocked using MockWebserver
     */


    // region Variables
    MockWebServer server;
    HttpUrl baseUrl;
    IPlantDAO plantDAO;
    List<PlantDTO> plants;
    // endregion

    // region Setup
    @Before
    public void setupMockedTests() throws IOException {
        server = MockWebServerUtils.startNewServer();
        baseUrl = server.url("api/plants");
    }
    // endregion

    // region Tests
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
    // endregion

    // region TestHelpers
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
    // endregion

    // region Givens
    private void givenPlantDAOIsInitialised() {
        plantDAO = new PlantDAO(baseUrl.toString());
    }
    // endregion

    // region Whens
    private void whenSearchForFilter(String filter) throws IOException, JSONException {

        plants = plantDAO.fetchPlants(filter);
    }
    // endregion

    // region Thens
    private void thenVerifyAtLeastOneGenusSpecies(String testGenus, String testSpecies) {
        assertThat(plants, anyOf(hasItem(HasPropertyWithValue.<PlantDTO>hasProperty("genus", containsString(testGenus)))));
    }

    private void thenVerifyZeroResults() {
        assertThat(plants, empty());
    }
    // endregion ma

    // region Teardown
    @After
    public void tearDownMockedTests() throws IOException {
        server.shutdown();
    }
    // endregion

}
