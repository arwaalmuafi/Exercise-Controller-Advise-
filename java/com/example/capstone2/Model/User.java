package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 4, message = "name should be longer then 3")
    @Column(columnDefinition = "varchar(20)")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email
    @Column(columnDefinition = "varchar(20) unique ")
    private String email;


////////////////////////////////////////////////////////////////////////////////////////////////
    public User(){

    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public @NotEmpty(message = "email should not be empty") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email should not be empty") @Email String email) {
        this.email = email;
    }

}
