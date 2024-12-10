package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Request;
import com.example.capstone2.Service.RequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/capstone/v1/request")
public class RequestController {
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    private final RequestService requestService;

    @GetMapping("/get")
   public ResponseEntity getAllRequest(){
       return ResponseEntity.status(200).body(requestService.getAllRequest());
   }

    @PostMapping("/add")
    public ResponseEntity addRequest(@RequestBody @Valid Request request) {
        requestService.addRequest(request);
        return ResponseEntity.status(200).body(new ApiResponse("Request added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateRequest(@PathVariable Integer id, @RequestBody @Valid Request request) {
        requestService.updateRequest(id, request);
        return ResponseEntity.status(200).body(new ApiResponse("Request updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRequest(@PathVariable Integer id) {
        requestService.deleteRequest(id);
        return ResponseEntity.status(200).body(new ApiResponse("Request deleted successfully"));
    }

    //3
    // Get requests scheduled for today or overdue
    @GetMapping("/scheduled-today-or-overdue")
    public ResponseEntity getRequestsScheduledForTodayOrOverdue() {
        return ResponseEntity.status(200).body(requestService.getRequestsScheduledForToday());
    }

    //4
    // Get available services based on garden size
    @GetMapping("/available-services/{gardenSize}")
    public ResponseEntity getAvailableServices(@PathVariable String gardenSize) {
        return ResponseEntity.status(200).body(requestService.getAvailableServices(gardenSize));
    }

    //5
    // Reschedule a request
    @PutMapping("/reschedule/{requestId}/{newScheduledDate}")
    public ResponseEntity rescheduleRequest(@PathVariable Integer requestId, @PathVariable LocalDate newScheduledDate) {
        Request updatedRequest = requestService.rescheduleRequest(requestId, newScheduledDate);
        return ResponseEntity.status(200).body(new ApiResponse("Request rescheduled successfully"));
    }

    //6
    // Update service status
    @PutMapping("/update-status/{requestId}/{newStatus}")
    public ResponseEntity updateServiceStatus(@PathVariable Integer requestId, @PathVariable String newStatus) {
        String result = requestService.updateServiceStatus(requestId, newStatus);
        if (result.equals("Request not found")) {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
        return ResponseEntity.status(200).body(new ApiResponse(result));
    }

    //7
    // Find a request by ID
    @GetMapping("/find/{id}")
    public ResponseEntity findRequestById(@PathVariable Integer id) {
        Request request = requestService.findRequestById(id);
        return ResponseEntity.status(200).body(request);
    }

    //8
    // Find requests by status
    @GetMapping("/find-by-status/{status}")
    public ResponseEntity findRequestsByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(requestService.findRequestsByStatus(status));
    }

    //9
    // Find requests by user ID
    @GetMapping("/find-by-user/{userId}")
    public ResponseEntity findRequestsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(requestService.findRequestsByUserId(userId));
    }
}
