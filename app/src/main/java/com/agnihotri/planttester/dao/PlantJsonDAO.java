package com.agnihotri.planttester.dao;

import com.agnihotri.planttester.dto.PlantDTO;

import java.util.List;

/**
 *
 */
public class PlantJsonDAO implements IPlantDAO {
    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        return null;
    }

    @Override
    public NetworkDAO getNetworkDAO() {
        return null;
    }

    @Override
    public void setNetworkDAO(NetworkDAO networkDAO) {

    }
}
