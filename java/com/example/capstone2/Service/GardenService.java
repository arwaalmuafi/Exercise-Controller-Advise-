package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Garden;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.GardenRepository;
import com.example.capstone2.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GardenService {
    public GardenService(GardenRepository gardenRepository, UserRepository userRepository) {
        this.gardenRepository = gardenRepository;
        this.userRepository = userRepository;
    }

    private final GardenRepository gardenRepository;
    private final UserRepository userRepository;
    private final List<Garden> gardenStore = new ArrayList<>();

    public List<Garden> getAllGardens() {
        return gardenRepository.findAll();
    }
    public void addGarden(Garden garden, Integer userId) {
        User user = userRepository.findPleaseById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        gardenRepository.save(garden);
    }
    public void updateGarden(Integer id, Garden garden) {
        Garden existingGarden = gardenRepository.findGardenById(id);

        if (existingGarden == null) {
            throw new ApiException("Garden not found");
        }

        existingGarden.setName(garden.getName());
        existingGarden.setLocation(garden.getLocation());
        existingGarden.setSizeOfTheGarden(garden.getSizeOfTheGarden());

        gardenRepository.save(existingGarden);
    }
    public void deleteGarden(Integer id) {
        Garden garden = gardenRepository.findGardenById(id);

        if (garden == null) {
            throw new ApiException("Garden not found");
        }
        gardenRepository.delete(garden);
    }
    public String assignGardenerToGarden(Integer gardenerId, Integer gardenId) {
        for (Garden garden : gardenStore) {
            if (garden.getId().equals(gardenId)) {
                return "Gardener " + gardenerId + " assigned to garden: " + garden.getName();
            }
        }
        return "Garden not found.";
    }
    public Garden findGardenByName(String gardenName) {
        Garden garden=gardenRepository.findByName(gardenName);
        if (garden == null) {
           throw new ApiException("not found");
        }
        return garden;
    }

}
