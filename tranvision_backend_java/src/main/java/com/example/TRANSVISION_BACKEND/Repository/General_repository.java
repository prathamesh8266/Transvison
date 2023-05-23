package com.example.TRANSVISION_BACKEND.Repository;

import com.example.TRANSVISION_BACKEND.Entities.Admin;
import com.example.TRANSVISION_BACKEND.Entities.General;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface General_repository extends JpaRepository<General,Integer> {
    List<General> findByNameAndPassword(String name, String password);
}
