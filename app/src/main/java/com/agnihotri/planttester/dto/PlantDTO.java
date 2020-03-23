package com.agnihotri.planttester.dto;

/**
 *
 */
public class PlantDTO {
    /**
     *
     */
    private int guid;
    private String genus;
    private String species;
    private String cultivar;
    private String commonName;

    public PlantDTO(int guid, String genus, String species, String cultivar, String commonName){
        this.guid = guid;
        this.genus = genus;
        this.species = species;
        this.cultivar = cultivar;
        this.commonName = commonName;
    }

    public PlantDTO(){
        guid = 0;
        genus = species = cultivar = commonName ="";
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int id) {
        this.guid = guid;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer("Plant id : ").append(guid);
        stringBuffer.append(", genus : ").append(genus);
        stringBuffer.append(", species : ").append(species);
        stringBuffer.append(", cultivar : ").append(cultivar);
        stringBuffer.append(", common name : ").append(commonName);

        return stringBuffer.toString();
    }


}
