package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(30)")
    @Pattern(regexp = "^(Garden Maintenance|Landscaping|Pest Control|Advisory Services)$", message = "Service type must be one of the following: Garden Maintenance, Landscaping, Pest Control, or Advisory Services.")
    private String serviceType;

    @NotNull(message = "userId should not be null")
    @Column(columnDefinition = "int")
    private Integer userId;

    @NotEmpty(message = "Details should not be empty.")
    @Size(min = 10, message = "Details should be longer than 10 characters.")
    @Column(columnDefinition = "varchar(100)")
    private String details;

    @Column
    @NotNull(message = "Requested date cannot be null.")
    private LocalDateTime requestedDate= LocalDateTime.now();

    @Column
    @NotNull(message = "scheduled date cannot be null.")
    private LocalDate scheduledDate;


    @NotEmpty(message = "Status cannot be null.")
    @Pattern(regexp = "^(Complete|Processing|Not Started)$", message = "Status must be one of the following: Complete, Processing, or Not Started.")
    @Column(columnDefinition ="varchar(20)" )
    private String status;

   private Integer gardenerId;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Request(){

    }

    public Integer getGardenerId() {
        return gardenerId;
    }

    public void setGardenerId(Integer gardenerId) {
        this.gardenerId = gardenerId;
    }

    public Request(Integer id, String serviceType, Integer userId, String details, LocalDateTime requestedDate, LocalDate scheduledDate, String status, Integer gardenerId) {
        this.id = id;
        this.serviceType = serviceType;
        this.userId = userId;
        this.details = details;
        this.requestedDate = requestedDate;
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.gardenerId = gardenerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "userId should not be null") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "userId should not be null") Integer userId) {
        this.userId = userId;
    }

    public @NotEmpty @Pattern(regexp = "^(Garden Maintenance|Landscaping|Pest Control|Advisory Services)$", message = "Service type must be one of the following: Garden Maintenance, Landscaping, Pest Control, or Advisory Services.") String getServiceType() {
        return serviceType;
    }

    public void setServiceType(@NotEmpty @Pattern(regexp = "^(Garden Maintenance|Landscaping|Pest Control|Advisory Services)$", message = "Service type must be one of the following: Garden Maintenance, Landscaping, Pest Control, or Advisory Services.") String serviceType) {
        this.serviceType = serviceType;
    }

    public @NotEmpty(message = "Details should not be empty.") @Size(min = 10, message = "Details should be longer than 10 characters.") String getDetails() {
        return details;
    }

    public void setDetails(@NotEmpty(message = "Details should not be empty.") @Size(min = 10, message = "Details should be longer than 10 characters.") String details) {
        this.details = details;
    }

    public void setRequestedDate(@NotNull(message = "Requested date cannot be null.") LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public @NotNull(message = "scheduled date cannot be null.") LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(@NotNull(message = "scheduled date cannot be null.") LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public @NotEmpty(message = "Status cannot be null.") @Pattern(regexp = "^(Complete|Processing|Not Started)$", message = "Status must be one of the following: Complete, Processing, or Not Started.") String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty(message = "Status cannot be null.") @Pattern(regexp = "^(Complete|Processing|Not Started)$", message = "Status must be one of the following: Complete, Processing, or Not Started.") String status) {
        this.status = status;
    }

}
