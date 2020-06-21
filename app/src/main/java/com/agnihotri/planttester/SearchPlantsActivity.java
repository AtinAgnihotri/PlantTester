package com.agnihotri.planttester;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.agnihotri.planttester.dto.PlantDTO;
import com.agnihotri.planttester.service.IPlantService;
import com.agnihotri.planttester.service.PlantService;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class SearchPlantsActivity extends AppCompatActivity {

    IPlantService plantService;
    AutoCompleteTextView actPlantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
        plantService = new PlantService();
        actPlantName = findViewById(R.id.actPlantName);
    }

    public void searchPlants(View v) throws IOException, JSONException {
        try {
            // TODO Populate list view with plants
            List<PlantDTO> plants = plantService.fetchPlants(actPlantName.getText().toString());

            for (PlantDTO plant : plants) {
                String showText = plant.toString();
                Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
