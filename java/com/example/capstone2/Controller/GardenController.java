package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Garden;
import com.example.capstone2.Service.GardenService;
import com.example.capstone2.Service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/capstone/v1/garden")
public class GardenController {
    public GardenController(GardenService gardenService, PlantService plantService) {

        this.gardenService = gardenService;
        this.plantService = plantService;
    }

    private final GardenService gardenService;
    private final PlantService plantService;

    // Get all gardens
    @GetMapping("/get")
    public ResponseEntity getAllGardens() {
        return ResponseEntity.status(200).body(gardenService.getAllGardens());
    }

    // Add a new garden for a user
    @PostMapping("/add/{userId}")
    public ResponseEntity addGarden(@PathVariable Integer userId, @RequestBody @Valid Garden garden) {
        gardenService.addGarden(garden, userId);
        return ResponseEntity.status(200).body(new ApiResponse("Garden added successfully"));
    }

    // Update an existing garden
    @PutMapping("/update/{id}")
    public ResponseEntity updateGarden(@PathVariable Integer id, @RequestBody @Valid Garden garden) {
        gardenService.updateGarden(id, garden);
        return ResponseEntity.status(200).body(new ApiResponse("Garden updated successfully"));
    }

    // Delete a garden by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGarden(@PathVariable Integer id) {
        gardenService.deleteGarden(id);
        return ResponseEntity.status(200).body(new ApiResponse("Garden deleted successfully"));
    }

    //15
    // Assign a gardener to a garden
    @PostMapping("/assign-gardener/{gardenId}/{gardenerId}")
    public ResponseEntity assignGardenerToGarden(@PathVariable Integer gardenId, @PathVariable Integer gardenerId) {
        return ResponseEntity.status(200).body(gardenService.assignGardenerToGarden(gardenerId, gardenId));
    }

    //16
    // Recommend plants for a garden based on size
    @GetMapping("/recommend-plants/{sizeOfTheGarden}")
    public ResponseEntity recommendPlantsForGarden(@PathVariable String sizeOfTheGarden) {
        return ResponseEntity.status(200).body(plantService.findPlantBySize(sizeOfTheGarden));
    }

    //17
    // Find a garden by name
    @GetMapping("/find/{name}")
    public ResponseEntity findGardenByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(gardenService.findGardenByName(name));
    }
}
