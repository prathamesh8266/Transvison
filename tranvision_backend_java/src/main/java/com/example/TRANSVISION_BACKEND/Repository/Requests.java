package com.example.TRANSVISION_BACKEND.Repository;

import com.example.TRANSVISION_BACKEND.Entities.BooksRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Requests extends JpaRepository<BooksRequests,Integer> {
}
