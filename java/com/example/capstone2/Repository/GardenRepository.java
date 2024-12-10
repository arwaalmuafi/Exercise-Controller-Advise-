package com.example.capstone2.Repository;

import com.example.capstone2.Model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GardenRepository extends JpaRepository<Garden,Integer> {
  Garden findByName(String name);

  Garden findGardenById(Integer id);


}
