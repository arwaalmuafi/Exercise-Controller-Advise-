package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Gardener;
import com.example.capstone2.Model.Request;
import com.example.capstone2.Repository.GardenerRepository;
import com.example.capstone2.Repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GardenerService {
    public GardenerService(GardenerRepository gardenerRepository, RequestRepository requestRepository) {
        this.gardenerRepository = gardenerRepository;
        this.requestRepository = requestRepository;
    }

    private final GardenerRepository gardenerRepository;
    private final RequestRepository requestRepository;

    public List<Gardener> getAllGardeners() {
        return gardenerRepository.findAll();
    }
    public void addGardener(Gardener gardener) {
        gardenerRepository.save(gardener);
    }
    public void updateGardener(Integer id, Gardener gardener) {
        Gardener existingGardener = gardenerRepository.findPById(id);

        if (existingGardener == null) {
            throw new ApiException("Gardener not found");
        }

        existingGardener.setName(gardener.getName());
        existingGardener.setPhone(gardener.getPhone());
        existingGardener.setSpecialization(gardener.getSpecialization());

        gardenerRepository.save(existingGardener);
    }
    public void deleteGardener(Integer id) {
        Gardener gardener = gardenerRepository.findPById(id);

        if (gardener == null) {
            throw new ApiException("Gardener not found");
        }

        gardenerRepository.delete(gardener);
    }
    public List<Gardener> getAvailableGardeners() {
        List<Gardener> availableGardeners = new ArrayList<>();

        for (Gardener gardener : gardenerRepository.findAll()) {
            boolean isAvailable = true;
            for (Request request : requestRepository.findAll()) {
                if (request.getDetails().contains(gardener.getName()) &&
                        (request.getStatus().equals("Processing") || request.getStatus().equals("Not Started"))) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableGardeners.add(gardener);
            }
        }
        return availableGardeners;
    }
    public Map<String, Integer> calculateGardenerWorkload() {
        Map<String, Integer> workload = new HashMap<>();

        for (Gardener gardener : gardenerRepository.findAll()) {
            workload.put(gardener.getName(), 0);
        }

        for (Request request : requestRepository.findAll()) {
            if (request.getGardenerId() != null) {
                Gardener assignedGardener = gardenerRepository.findById(request.getGardenerId()).orElse(null);
                if (assignedGardener != null) {
                    String gardenerName = assignedGardener.getName();
                    workload.put(gardenerName, workload.get(gardenerName) + 2); // Add 2 hours for each request
                }
            }
        }

        return workload;
    }
    public String assignRequestToGardener(Integer gardenerId, Integer requestId) {
        Gardener gardener = gardenerRepository.findById(gardenerId).orElse(null);
        if (gardener == null) {
            return "Error: Gardener with ID " + gardenerId + " not found.";
        }

        Request request = requestRepository.findById(requestId).orElse(null);
        if (request == null) {
            return "Error: Request with ID " + requestId + " not found.";
        }

        request.setGardenerId(gardener.getId());

        requestRepository.save(request);

        return "Request with ID " + requestId + " has been assigned to Gardener: " + gardener.getName();
    }
    public List<Gardener> findGardenersBySpecialization(String specialization) {
        List<Gardener> gardener = gardenerRepository.findBySpecialization(specialization);
        if (gardener == null) {
            throw new ApiException("gradener not foun");
        }
        return gardener;
    }


}

