package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Plant;
import com.example.capstone2.Service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/capstone/v1/plants")
public class PlantController {
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    private final PlantService plantService;

    @GetMapping("get")
    public ResponseEntity getAllPlants() {
        return ResponseEntity.status(200).body(plantService.getAllPlants());
    }

    @PostMapping("/add")
    public ResponseEntity addPlant(@RequestBody @Valid Plant plant) {
        plantService.addPlant(plant);
        return ResponseEntity.status(200).body(new ApiResponse("Plant added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePlant(@PathVariable Integer id, @RequestBody @Valid Plant plant) {
        plantService.updatePlant(id, plant);
        return ResponseEntity.status(200).body(new ApiResponse("Plant updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlant(@PathVariable Integer id) {
        plantService.deletePlant(id);
        return ResponseEntity.status(200).body(new ApiResponse("Plant deleted successfully"));
    }

    //18
    @GetMapping("/type/{type}")
    public ResponseEntity filterPlantsByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(plantService.filterPlantsByType(type));
    }

    //19
    @GetMapping("/season/{season}")
    public ResponseEntity suggestPlantsForSeason(@PathVariable String season) {
        return ResponseEntity.status(200).body(plantService.suggestPlantsForSeason(season));
    }

    //20
    @GetMapping("/{id}/check-water/{waterProvided}")
    public ResponseEntity checkWaterRequirement(@PathVariable Integer id, @PathVariable Double waterProvided) {
        return ResponseEntity.status(200).body(plantService.checkWaterRequirement(id, waterProvided));
    }


    //23
    @GetMapping("/{id}")
    public ResponseEntity findPlantById(@PathVariable Integer id) {
        Plant plant = plantService.findPlantById(id);
        return ResponseEntity.status(200).body(plant);
    }

    //24
    @GetMapping("/find/type/{type}")
    public ResponseEntity findPlantsByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(plantService.findPlantsByType(type));
    }
}
