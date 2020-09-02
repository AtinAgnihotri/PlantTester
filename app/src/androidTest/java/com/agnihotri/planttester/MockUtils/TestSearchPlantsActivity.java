package com.agnihotri.planttester.MockUtils;

import android.os.Bundle;

import com.agnihotri.planttester.SearchPlantsActivity;
import com.agnihotri.planttester.dao.BaseUrl;
import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.service.IPlantService;
import com.agnihotri.planttester.service.PlantService;

public class TestSearchPlantsActivity extends SearchPlantsActivity {
    Object sInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IPlantDAO plantDAO = new PlantDAO("http://127.0.0.1:8080/api/plants/search");
        PlantService newPlantService = new PlantService();
        newPlantService.setPlantDAO(plantDAO);
        setPlantService(newPlantService);
        sInstance = this;
    }


}
