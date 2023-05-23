package com.example.TRANSVISION_BACKEND.Repository;

import com.example.TRANSVISION_BACKEND.Entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Books_repository extends JpaRepository<Books,Integer> {
    List<Books> findByAdminId(Integer bookId);
}
