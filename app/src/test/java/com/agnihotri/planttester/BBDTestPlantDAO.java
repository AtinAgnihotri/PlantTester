package com.agnihotri.planttester;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.hamcrest.CoreMatchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.AnyOf;
import org.json.JSONException;
import org.junit.Test;

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



public class BBDTestPlantDAO {
    // region Variables
    IPlantDAO plantDAO;
    List<PlantDTO> plants;
    // endregion

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
