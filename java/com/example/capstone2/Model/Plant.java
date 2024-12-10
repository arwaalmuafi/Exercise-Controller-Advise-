package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4, message = "name should be longer then 3")
    @Column(columnDefinition = "varchar(20)")
    private String name;

    @NotEmpty(message = "Plant type cannot be blank.")
    @Column(columnDefinition = "varchar(20)")
    private String type;

    @NotEmpty(message = "Plant type cannot be blank.")
    @Column(columnDefinition = "varchar(20)")
    private String season;

    @NotNull(message = "Water requirement cannot be null.")
    @DecimalMin(value = "0.1", message = "Water requirement must be at least 0.1 liters/day.")
    @Column(nullable = false)
    private Double waterRequirement;

    @NotNull(message = "size connote be null.")
    @Pattern(regexp="^(small|medium|large)$",message = "size only small medium or large")
    @Column(nullable = false)
    private String size;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Plant(){

    }

    public @NotNull(message = "Water requirement cannot be null.") @Pattern(regexp = "^(small|medium|large)$", message = "size only small medium or large") String getSize() {
        return size;
    }

    public void setSize(@NotNull(message = "Water requirement cannot be null.") @Pattern(regexp = "^(small|medium|large)$", message = "size only small medium or large") String size) {
        this.size = size;
    }

    public Plant(Integer id, String name, String type, String season, Double waterRequirement, String size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.season = season;
        this.waterRequirement = waterRequirement;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "name should not be empty") @Size(min = 4, message = "name should be longer then 3") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "name should not be empty") @Size(min = 4, message = "name should be longer then 3") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Plant type cannot be blank.") String getType() {
        return type;
    }

    public void setType(@NotEmpty(message = "Plant type cannot be blank.") String type) {
        this.type = type;
    }

    public @NotEmpty(message = "Plant type cannot be blank.") String getSeason() {
        return season;
    }

    public void setSeason(@NotEmpty(message = "Plant type cannot be blank.") String season) {
        this.season = season;
    }

    public @NotNull(message = "Water requirement cannot be null.") @DecimalMin(value = "0.1", message = "Water requirement must be at least 0.1 liters/day.") Double getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(@NotNull(message = "Water requirement cannot be null.") @DecimalMin(value = "0.1", message = "Water requirement must be at least 0.1 liters/day.") Double waterRequirement) {
        this.waterRequirement = waterRequirement;
    }





}
