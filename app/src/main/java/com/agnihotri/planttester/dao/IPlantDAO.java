package com.agnihotri.planttester.dao;

import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Atin Agnihotri 23/03/2020
 * A collections of methods to access plant data.
 */
public interface IPlantDAO {

    /**
     * Accept filter text and return collection of plants that contaion that filter text.
     * @param filter text to use as a filter.
     * @return List of Plants that contain filter text in genus, species, cultivar or common name.
     */
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException;

    NetworkDAO getNetworkDAO();

    void setNetworkDAO(NetworkDAO networkDAO);

}
