package com.example.capstone2.Repository;
import com.example.capstone2.Model.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenerRepository extends JpaRepository<Gardener,Integer> {
    @Query("select u from Request u where u.id=?1")
    Gardener findPById(Integer id);
    Gardener findGardenerById(Integer id);
    List<Gardener> findBySpecialization(String specialization);

}
