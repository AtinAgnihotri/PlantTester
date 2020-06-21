package com.agnihotri.planttester.dao;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import com.agnihotri.planttester.dto.PlantDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class PlantDAO extends Activity implements IPlantDAO  {

    private NetworkDAO networkDAO;
    private String baseUrl = "https://trefle.io/api/plants";
    String apiToken = "YmFCYmNWU0k4WG14TFZBVGpRTFJLdz09";

    public PlantDAO() {
        setNetworkDAO(new NetworkDAO());
    }

    public PlantDAO(String url) {
        this();
        baseUrl = url;
    }

    @Override
    public List<PlantDTO> fetchPlants(String filter) throws IOException, JSONException {
        filter = filter.replace(" ", "%20");
        ArrayList<PlantDTO> allPlants = new ArrayList<PlantDTO>();


        String requestURI = baseUrl + "?q=" + filter + "&token=" + apiToken;

        String requestResponse = getNetworkDAO().fetch(requestURI);

        // Entire JSON Array
        JSONArray plants = new JSONArray(requestResponse);

        for (int i = 0; i < plants.length(); i++) {
            // this guy right here represents an individual plant.
            try {
                JSONObject jsonObject = plants.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String scientificName = jsonObject.getString("scientific_name");
                String link = jsonObject.getString("link");
                String slug = jsonObject.getString("slug");
                String[] slugTokens = slug.split("-");
                String genus = slugTokens[0];
                String species = slugTokens[1];
                //            String cultivar = jsonObject.getString("cultivar");

                Object commonNameObj = jsonObject.get("common_name");
                String commonName = commonNameObj.toString();

                // now, let's put them into a PlantDTO.
                PlantDTO plantDTO = new PlantDTO();
                plantDTO.setGuid(id);
                plantDTO.setGenus(genus);
                plantDTO.setSpecies(species);
                //            plantDTO.setCultivar(cultivar);
                plantDTO.setCommonName(commonName);
                plantDTO.setLink(link);
                plantDTO.setSlug(slug);
                plantDTO.setScientificName(scientificName);

                // add this plant to the collection.
                allPlants.add(plantDTO);
//                System.out.println("Common Name : " + commonName );
            } catch (Exception e) {
                continue;
            }
        }
        return allPlants;
    }

    public PlantDTO getUniquePlant(int plantID) throws IOException, JSONException {
        String requestURI = baseUrl + "/" + plantID + "?token=" + apiToken;

        String requestResponse = getNetworkDAO().fetch(requestURI);

        // Entire JSON
        JSONObject plantJson = new JSONObject(requestResponse);

        String scientificName = plantJson.getString("scientific_name");
        int id = plantJson.getInt("id");
        String link = plantJson.getString("link");
        String slug = plantJson.getString("slug");
        String[] slugTokens = slug.split("-");
        String genus = slugTokens[0];
        String species = slugTokens[1];
        //            String cultivar = jsonObject.getString("cultivar");

        Object commonNameObj = plantJson.get("common_name");
        String commonName = commonNameObj.toString();

        // now, let's put them into a PlantDTO.
        PlantDTO plantDTO = new PlantDTO();
        plantDTO.setGuid(id);
        plantDTO.setGenus(genus);
        plantDTO.setSpecies(species);
        //            plantDTO.setCultivar(cultivar);
        plantDTO.setCommonName(commonName);
        plantDTO.setLink(link);
        plantDTO.setSlug(slug);
        plantDTO.setScientificName(scientificName);

        return plantDTO;
    }

    @Override
    public NetworkDAO getNetworkDAO() {
        return networkDAO;
    }

    @Override
    public void setNetworkDAO(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }
}
