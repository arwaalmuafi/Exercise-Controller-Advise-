package com.example.capstone2.Repository;

import com.example.capstone2.Model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Integer> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
   Plant findPleaseById(Integer id);

    Plant findPlantById(Integer id);

    List<Plant> findByType(String type);

    List<Plant> findPlantBySize(String size);





}
