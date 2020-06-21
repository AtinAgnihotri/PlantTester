package com.agnihotri.planttester;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAOStub;
import com.agnihotri.planttester.dto.PlantDTO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestPlantDAO {

    // Declare a variable for the DAO we are testing
    IPlantDAO plantDAO;

    @BeforeClass
    public static void setupAllTests(){
        System.out.println("BeforeClass : Running INIT for all Tests for Plant DAO");
    }
    
    @Before
    public void setup(){
        System.out.println("Before : Running INIT before each Tests for Plant DAO");
        plantDAO = new PlantDAOStub();
    }

    @Test
    public void testPlantDAO_searchForRedbudShouldReturnAtLeastOneResult () {

        // Assuming we don't have a match
        boolean redbudFound = false;

        List<PlantDTO> plants = plantDAO.fetchPlants("Redbud");

        for (PlantDTO plant : plants){
            if (plant.getCommonName().contains("Redbud")){
                redbudFound = true;
            }
            System.out.println(plant.toString());
        }
        // Did we find a redbud?
        assertTrue(redbudFound);
        System.out.println("TEST : Running Redbud Test");
    }

    @Ignore
    public void testPlantDAO_searchForOakShouldReturnAtLeastOneWhiteOak () {

        // Assuming we don't have a match
        boolean whiteOakFound = false;

        List<PlantDTO> plants = plantDAO.fetchPlants("Oak");

        for (PlantDTO plant : plants){
            if (plant.getCommonName().contains("WhiteOak")){
                whiteOakFound = true;
            }
            System.out.println(plant.toString());
        }

        assertTrue(whiteOakFound);
        System.out.println("TEST : Running WhiteOak Test");
    }

    @Ignore
    public void testPlantDAO_searchForEShouldReturnAtLeastTwoResults(){
        List<PlantDTO> plants = plantDAO.fetchPlants("e");
        int size = plants.size();
        boolean atLeastTwo = size >= 2;
        assertTrue(atLeastTwo);
        System.out.println("TEST : This should not run as this is not annotated");

    }

    @After
    public void teardown(){
        System.out.println("After : Running Teardowns after each Tests for Plant DAO");
    }

    @AfterClass
    public static void teardownAllTests(){
        System.out.println("AfterClass : Running Teardowns for all Tests for Plant DAO");
    }

}
