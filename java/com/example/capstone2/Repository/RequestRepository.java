package com.example.capstone2.Repository;
import com.example.capstone2.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
     @Query("select u from Request u where u.id=?1")
     Request findPById(Integer id);

     Request findRequestById(Integer id);

     List<Request> findByStatus(String status);


     List<Request> findByUserId(Integer userId);


}
