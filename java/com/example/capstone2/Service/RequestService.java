package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Request;
import com.example.capstone2.Repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


@Service
public class RequestService {
    public RequestService(RequestRepository requestRepository, ArrayList<Request> requestList) {
        this.requestRepository = requestRepository;
        this.requestList = requestList;
    }

    private final RequestRepository requestRepository;

    ArrayList<Request> requestList = new ArrayList<>();

    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }
    public void addRequest(Request request) {
        requestRepository.save(request);
    }
    public void updateRequest(Integer id, Request request) {
        Request existingRequest = requestRepository.findPById(id);

        if (existingRequest == null) {
            throw new ApiException("Request not found");
        }

        existingRequest.setServiceType(request.getServiceType());
        existingRequest.setDetails(request.getDetails());
        existingRequest.setRequestedDate(request.getRequestedDate());
        existingRequest.setScheduledDate(request.getScheduledDate());
        existingRequest.setStatus(request.getStatus());

        requestRepository.save(existingRequest);
    }
    public void deleteRequest(Integer id) {
        Request request = requestRepository.findPById(id);

        if (request == null) {
            throw new ApiException("Request not found");
        }

        requestRepository.delete(request);
    }
    public List<Request> getRequestsScheduledForToday() {
        List<Request> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Request request : requestRepository.findAll()) {
            if (!request.getStatus().equals("Complete") &&
                    (request.getScheduledDate().isBefore(today) || request.getScheduledDate().isEqual(today))) {
                result.add(request);
            }
        }
        return result;
    }
    public List<String> getAvailableServices(String gardenSize) {
        List<String> services = new ArrayList<>();
        if ("Big".equalsIgnoreCase(gardenSize)) {
            services.add("Landscaping");
            services.add("Garden Maintenance");
        } else if ("Medium".equalsIgnoreCase(gardenSize)) {
            services.add("Garden Maintenance");
        } else {
            services.add("Advisory Services");
        }
        return services;
    }
    public Request rescheduleRequest(int requestId, LocalDate newScheduledDate) {
        if (requestList == null || requestList.isEmpty()) {
            requestList = (ArrayList<Request>) requestRepository.findAll();
        }

        for (Request request : requestList) {
            if (request.getId().equals(requestId)) {
                request.setScheduledDate(newScheduledDate);
                return requestRepository.save(request);
            }
        }
        throw new ApiException("Request with ID " + requestId + " not found.");
    }
    public String updateServiceStatus(int requestId, String newStatus) {
        for (Request request : requestList) {
            if (request.getId() == requestId) {
                request.setStatus(newStatus);
                return "Status updated to " + newStatus;
            }
        }
        return "Request not found";
    }
    public Request findRequestById(int requestId) {
        return requestRepository.findPById(requestId);
    }
    public List<Request> findRequestsByStatus(String status) {
        return requestRepository.findByStatus(status);
    }
    public List<Request> findRequestsByUserId(int userId) {
        return requestRepository.findByUserId(userId);
    }


}
