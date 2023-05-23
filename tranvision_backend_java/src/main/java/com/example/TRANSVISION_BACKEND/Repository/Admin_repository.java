package com.example.TRANSVISION_BACKEND.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.TRANSVISION_BACKEND.Entities.Admin;

import java.util.List;

@Repository
public interface Admin_repository extends JpaRepository<Admin,String> {
    public List<Admin> findByNameAndPassword(String name, String password);
}
