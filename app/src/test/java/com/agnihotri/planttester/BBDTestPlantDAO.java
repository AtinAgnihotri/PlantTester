package com.agnihotri.planttester;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BBDTestPlantDAO {

    IPlantDAO plantDAO;
    List<PlantDTO> plants;

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

    private void thenVerifyAllGenusAreQuercus() {
        for (PlantDTO plant : plants){
            assertEquals("quercus", plant.getGenus().toLowerCase());
        }
    }

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

    private void givenPlantDAOIsInitialised() {
        plantDAO = new PlantDAO();
    }

    private void whenSearchForFilter(String filter) throws IOException, JSONException {

        plants = plantDAO.fetchPlants(filter);
    }

    private void thenVerifyAtLeastOneGenusSpecies(String testGenus, String testSpecies) {
        boolean genusSpeciesFound = false;

        for (PlantDTO plant : plants) {
            if (plant.getGenus().toLowerCase().contains(testGenus) && plant.getSpecies().toLowerCase().contains(testSpecies)){
                genusSpeciesFound = true;
            }
        }

        assertTrue(genusSpeciesFound);

    }

    private void thenVerifyZeroResults() {
        int size = plants.size();

        assertEquals(0, size);

    }

}
