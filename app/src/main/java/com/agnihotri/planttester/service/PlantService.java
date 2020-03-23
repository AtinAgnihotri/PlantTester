package com.agnihotri.planttester.service;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAOStub;
import com.agnihotri.planttester.dao.PlantJsonDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import java.util.List;

public class PlantService implements IPlantService {

    IPlantDAO plantDAO;

    public PlantService(){
//        plantDAO = new PlantJsonDAO();
        plantDAO = new PlantDAOStub();
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) {
        return plantDAO.fetchPlants(filter);
    }
}
