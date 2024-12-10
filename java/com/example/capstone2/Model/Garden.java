package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be blank.")
    @Size(min = 3, message = "Name should be more then 3")
    @Column(columnDefinition = "varchar(20)")
    private String name; // Name of the garden

    @NotEmpty(message = "Location cannot be blank.")
    @Size(min = 10, message = "Location cannot exceed 200 characters.")
    @Column(columnDefinition = "varchar(50)")
    private String location;

    @NotEmpty(message = "sizeOfTheGarden should not be empty")
    @Size(min = 2, message = "sizeOfTheGarden should be more 2 ")
    @Column(columnDefinition = "varchar(10)")
    @Pattern(regexp = "^(Big|Medium|Small)$", message = "Size of the garden must be one of the following: Big, Medium, or Small.")
    private String sizeOfTheGarden;
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Garden(){

    }
    public Garden(Integer id, String name, String location, String sizeOfTheGarden) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.sizeOfTheGarden = sizeOfTheGarden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name cannot be blank.") @Size(min = 3, message = "Name should be more then 3") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name cannot be blank.") @Size(min = 3, message = "Name should be more then 3") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Location cannot be blank.") @Size(min = 10, message = "Location cannot exceed 200 characters.") String getLocation() {
        return location;
    }

    public void setLocation(@NotEmpty(message = "Location cannot be blank.") @Size(min = 10, message = "Location cannot exceed 200 characters.") String location) {
        this.location = location;
    }

    public @NotEmpty(message = "sizeOfTheGarden should not be empty") @Size(min = 2, message = "sizeOfTheGarden should be more 2 ") @Pattern(regexp = "^(Big|Medium|Small)$", message = "Size of the garden must be one of the following: Big, Medium, or Small.") String getSizeOfTheGarden() {
        return sizeOfTheGarden;
    }

    public void setSizeOfTheGarden(@NotEmpty(message = "sizeOfTheGarden should not be empty") @Size(min = 2, message = "sizeOfTheGarden should be more 2 ") @Pattern(regexp = "^(Big|Medium|Small)$", message = "Size of the garden must be one of the following: Big, Medium, or Small.") String sizeOfTheGarden) {
        this.sizeOfTheGarden = sizeOfTheGarden;
    }
}
