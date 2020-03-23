package com.agnihotri.planttester.dao;

import com.agnihotri.planttester.dto.FlowerDTO;
import com.agnihotri.planttester.dto.PlantDTO;
import com.agnihotri.planttester.dto.TreeDTO;

import java.util.ArrayList;
import java.util.List;

public class PlantDAOStub implements IPlantDAO {
    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        List<PlantDTO> allPlants = new ArrayList<PlantDTO>();

        TreeDTO plant = new TreeDTO();
        FlowerDTO flower = new FlowerDTO();

        plant.setGuid(0);
        plant.setSize(30);
        plant.setGenus("Cercis");
        plant.setSpecies("canadensis");
        plant.setCommonName("Eastern Redbud");
        plant.setFallColor("Yellowish");

        allPlants.add(plant);

        flower.setGuid(1);
        flower.setGenus("Tropoleum");
        flower.setSpecies("spp");
        flower.setCommonName("Nasturtium");

        allPlants.add(flower);

        return allPlants;

    }
}
