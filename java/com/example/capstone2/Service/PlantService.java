package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Plant;
import com.example.capstone2.Repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantService {
    public PlantService(PlantRepository plantRepository, ArrayList<Plant> plantList) {
        this.plantRepository = plantRepository;
        this.plantList = plantList;
    }

    private final PlantRepository plantRepository;
    private  List<Plant> plantStore = new ArrayList<>();

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }
    public void addPlant(Plant plant) {
        plantRepository.save(plant);
    }
    public void updatePlant(Integer id, Plant plant) {
        Plant existingPlant = plantRepository.findPleaseById(id);

        if (existingPlant == null) {
            throw new ApiException("Plant not found");
        }

        existingPlant.setName(plant.getName());
        existingPlant.setType(plant.getType());
        existingPlant.setWaterRequirement(plant.getWaterRequirement());

        plantRepository.save(existingPlant);
    }
    public void deletePlant(Integer id) {
        Plant plant = plantRepository.findPleaseById(id);

        if (plant == null) {
            throw new ApiException("Plant not found");
        }

        plantRepository.delete(plant);
    }
    public List<Plant> filterPlantsByType(String type) {
        List<Plant> allPlants = plantRepository.findAll();
        List<Plant> filteredPlants = new ArrayList<>();
        for (Plant plant : allPlants) {
            if (plant.getType().equalsIgnoreCase(type)) {
                filteredPlants.add(plant);
            }
        }
        return filteredPlants;
    }
    public List<Plant> suggestPlantsForSeason(String season) {
        List<Plant> allPlants = plantRepository.findAll();
        List<Plant> suggestedPlants = new ArrayList<>();
        for (Plant plant : allPlants) {
            if (plant.getSeason().equalsIgnoreCase(season)) {
                suggestedPlants.add(plant);
            }
        }

        return suggestedPlants;
    }
    public String checkWaterRequirement(Integer id, Double waterProvided) {
        Plant plant = plantRepository.findById(id).orElse(null);
        if (plant == null) {
            throw new ApiException("Plant not found.");
        }

        if (waterProvided >= plant.getWaterRequirement()) {
            return "Water provided is sufficient.";
        } else {
            return "Water provided is not sufficient. The required amount is " + plant.getWaterRequirement() + " liters.";
        }
    }
    ArrayList<Plant>plantList=new ArrayList<>();
    public List<Plant> findPlantBySize(String size){
        List<Plant> plants=plantRepository.findPlantBySize(size);
        if(plants==null){
            throw new ApiException("not found");
        }
        return plants;
    }
    public List<Plant> searchPlantsByWaterRequirement(double minWater, double maxWater) {
        List<Plant> matchingPlants = new ArrayList<>();
        for (Plant plant : plantList) {
            if (plant.getWaterRequirement() >= minWater && plant.getWaterRequirement() <= maxWater) {
                matchingPlants.add(plant);
            }
        }
        return matchingPlants;
    }
    public Plant findPlantById(Integer plantId) {
        Plant plant=plantRepository.findPlantById(plantId);
        if(plant==null){
            throw new ApiException("not found");
        }
        return plantRepository.findPlantById(plantId);
    }
    public List<Plant> findPlantsByType(String type) {
        return plantRepository.findByType(type);
    }

}

