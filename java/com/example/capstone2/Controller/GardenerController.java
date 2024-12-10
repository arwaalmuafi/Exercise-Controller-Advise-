package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Gardener;
import com.example.capstone2.Service.GardenerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capstone/v1/gardener")
public class GardenerController {
    public GardenerController(GardenerService gardenerService) {
        this.gardenerService = gardenerService;
    }

    private final GardenerService gardenerService;

    // Get all gardeners
    @GetMapping("/get")
    public ResponseEntity getAllGardeners() {
        return ResponseEntity.status(200).body(gardenerService.getAllGardeners());
    }

    // Add a new gardener
    @PostMapping("/add")
    public ResponseEntity addGardener(@RequestBody @Valid Gardener gardener) {
        gardenerService.addGardener(gardener);
        return ResponseEntity.status(200).body(new ApiResponse("Gardener added successfully"));
    }

    // Update an existing gardener
    @PutMapping("/update/{id}")
    public ResponseEntity updateGardener(@PathVariable Integer id, @RequestBody @Valid Gardener gardener) {
        gardenerService.updateGardener(id, gardener);
        return ResponseEntity.status(200).body(new ApiResponse("Gardener updated successfully"));
    }

    // Delete a gardener by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGardener(@PathVariable Integer id) {
        gardenerService.deleteGardener(id);
        return ResponseEntity.status(200).body(new ApiResponse("Gardener deleted successfully"));
    }

    //10
    // Get available gardeners
    @GetMapping("/available")
    public ResponseEntity getAvailableGardeners() {
        return ResponseEntity.status(200).body(gardenerService.getAvailableGardeners());
    }

    //11
    // Calculate gardener workload
    @GetMapping("/workload")
    public ResponseEntity calculateGardenerWorkload() {
        return ResponseEntity.status(200).body(gardenerService.calculateGardenerWorkload());
    }

    //12
    // Assign a gardener to a request
    @PostMapping("/{gardenerId}/assign-request/{requestId}")
    public ResponseEntity<String> assignRequestToGardener(@PathVariable Integer gardenerId, @PathVariable Integer requestId) {
            return ResponseEntity.status(200).body(gardenerService.assignRequestToGardener(gardenerId, requestId));

    }

    //14
    // Find gardeners by specialization
    @GetMapping("/specialization/{specialization}")
    public ResponseEntity findGardenersBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.status(200).body(gardenerService.findGardenersBySpecialization(specialization));
    }
}
