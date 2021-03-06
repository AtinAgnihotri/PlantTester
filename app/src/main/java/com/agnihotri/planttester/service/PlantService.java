package com.agnihotri.planttester.service;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dao.PlantDAOStub;
import com.agnihotri.planttester.dao.PlantJsonDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PlantService implements IPlantService {

    IPlantDAO plantDAO;

    public PlantService(){
//        plantDAO = new PlantJsonDAO();
//        plantDAO = new PlantDAOStub();
        plantDAO = new PlantDAO();
    }

    public IPlantDAO getPlantDAO(){
        return plantDAO;
    }

    public void setPlantDAO(IPlantDAO plantDAO){
        this.plantDAO = plantDAO;
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException {
        return plantDAO.fetchPlants(filter);
    }
}
