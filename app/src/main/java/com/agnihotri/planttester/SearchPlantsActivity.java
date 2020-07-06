package com.agnihotri.planttester;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;
import com.agnihotri.planttester.service.IPlantService;
import com.agnihotri.planttester.service.PlantService;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class SearchPlantsActivity extends AppCompatActivity {

    IPlantService plantService;
    AutoCompleteTextView actPlantName;
    ListView lstPlants;
    List<PlantDTO> plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plants);
        plantService = new PlantService();
        actPlantName = (AutoCompleteTextView) findViewById(R.id.actPlantName);
        lstPlants = (ListView) findViewById(R.id.lstPlants);

    }

    public IPlantService getPlantService(){
        return plantService;
    }

    public void setPlantService(IPlantService plantService){
        this.plantService = plantService;
    }

    public void searchPlants(View v) throws IOException, JSONException{
        String search = actPlantName.getText().toString();
//        System.out.println("#==================================#");
//        System.out.println("#=========  search :" + search + " =======#");
//        System.out.println("#==================================#");
        if (search != "") {
            PlantSearchTask pst = new PlantSearchTask();
            pst.execute(search);
        } else {
            Toast.makeText(this, "Please Enter a plant to search", Toast.LENGTH_LONG).show();
        }
    }

//    public void setBaseUrl(){
//        String baseUrl =
//    }

    public List<PlantDTO> getPlants(){
        return plants;
    }

    public void setPlants(List<PlantDTO> plants){
        this.plants = plants;
    }

    class PlantSearchTask extends AsyncTask<String, Integer, List<PlantDTO>> {

        @Override
        protected void onPostExecute(List<PlantDTO> plantDTOS) {
            ArrayAdapter<PlantDTO> plantDTOArrayAdapter =
                    new ArrayAdapter<PlantDTO>(SearchPlantsActivity.this, android.R.layout.simple_list_item_1, plants);
            lstPlants.setAdapter(plantDTOArrayAdapter);
            setPlants(plants);
        }

        @Override
        protected List<PlantDTO> doInBackground(String... params) {
//            System.out.println("#==================================#");
//            System.out.println("#=========  param :" + params[0].toString() + " =======#");
//            System.out.println("#==================================#");
//            List<PlantDTO> plants = null;
            plants = null;
            try {

                plants = plantService.fetchPlants(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (plants == null){
                System.out.println("#==================================#");
                System.out.println("#=========  Plants is Null  =======#");
                System.out.println("#==================================#");


            } else {
                for (PlantDTO plant : plants){
                    System.out.println("#==================================#");
                    System.out.println("#=========  " + plant.toString() +  "  =======#");
                    System.out.println("#==================================#");
                }
            }

            return plants;
        }
    }

//    public void searchPlants(View v) throws IOException, JSONException {
//        try {
//            // TODO Populate list view with plants
//            List<PlantDTO> plants = plantService.fetchPlants(actPlantName.getText().toString());
//
//            for (PlantDTO plant : plants) {
//                String showText = plant.toString();
//                Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//        }
//    }
}
