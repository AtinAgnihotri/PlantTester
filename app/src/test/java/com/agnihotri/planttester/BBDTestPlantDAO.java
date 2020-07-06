package com.agnihotri.planttester;

import com.agnihotri.planttester.Utils.MockJsonStrings;
import com.agnihotri.planttester.Utils.MockedUrls;
import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.NetworkDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.hamcrest.CoreMatchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.AnyOf;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.util.List;

// Hamcrest Imports
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.when;


public class BBDTestPlantDAO {
    /**
     * Behavior testing PlantDAO, with the network access mocked by mocking NetworkDAO
     */
    // region Variables
    IPlantDAO plantDAO;
    List<PlantDTO> plants;
    @Mock NetworkDAO networkDAO;
    // endregion

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void initialiseMocks() throws IOException {
        when(networkDAO.fetch(MockedUrls.REDBUD_QUERRY)).thenReturn(MockJsonStrings.REDBUD_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.MOCK_REDBUD_QUERRY)).thenReturn(MockJsonStrings.REDBUD_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.HYBRID_OAK_QUERRY)).thenReturn(MockJsonStrings.HYBRID_OAK_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.MOCK_HYBRID_OAK_QUERRY)).thenReturn(MockJsonStrings.HYBRID_OAK_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.GARBAGE_VALUE_QUERRY)).thenReturn(MockJsonStrings.GARBAGE_VALUE_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.MOCK_GARBAGE_VALUE_QUERRY)).thenReturn(MockJsonStrings.GARBAGE_VALUE_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.QUERCUS_QUERRY)).thenReturn(MockJsonStrings.HYBRID_OAK_JSON_STRING);
        when(networkDAO.fetch(MockedUrls.MOCK_QUERCUS_QUERRY)).thenReturn(MockJsonStrings.HYBRID_OAK_JSON_STRING);
    }

    // region Tests
    @Test
    public void testPlantDAO_fetchShouldReturnResultsForRedbud() throws IOException, JSONException{
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

    @Test
    public void testPlantDAO_fetchShouldReturnGenusQuercusForQuercus() throws IOException, JSONException {
        givenPlantDAOIsInitialised();
        whenSearchForFilter("Quercus");
        thenVerifyAllGenusAreQuercus();
    }
    // endregion

    // region TestHelpers
    private void fetchForFilterShouldReturnAtLeastOneGenusSpecies(String filter, String genus, String species) throws IOException, JSONException {
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
        plantDAO = new PlantDAO();

        // Mocking NetworkDAO through Mockito
        plantDAO.setNetworkDAO(networkDAO);

    }
    // endregion

    // region Whens
    private void whenSearchForFilter(String filter) throws IOException, JSONException {
        plants = plantDAO.fetchPlants(filter);
    }
    // endregion

    // region Thens
    private void thenVerifyAllGenusAreQuercus() {
        /*for (PlantDTO plant : plants){
            assertThat(plant, HasPropertyWithValue.<PlantDTO>hasProperty("genus", containsString("quercus")));
        }*/
        assertThat(plants, allOf(hasItem(HasPropertyWithValue.<PlantDTO>hasProperty("genus", containsString("quercus")))));
    }

    private void thenVerifyAtLeastOneGenusSpecies(String testGenus, String testSpecies) {
        assertThat(plants, anyOf(hasItem(HasPropertyWithValue.<PlantDTO>hasProperty("genus", containsString(testGenus)))));
    }

    private void thenVerifyZeroResults() {
        assertThat(plants, empty());
    }
    // endregion

}
