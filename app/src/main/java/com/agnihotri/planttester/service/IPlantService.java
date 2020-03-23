package com.agnihotri.planttester.service;

import com.agnihotri.planttester.dto.PlantDTO;

import java.util.List;

/**
 * Business logic for fetching and managing plants
 */
public interface IPlantService {

    /**
     * Accept filter text and return collection of plants that contaion that filter text.
     * @param filter text to use as a filter.
     * @return List of Plants that contain filter text in genus, species, cultivar or common name.
     */
    public List<PlantDTO> fetchPlants(String filter);
}
