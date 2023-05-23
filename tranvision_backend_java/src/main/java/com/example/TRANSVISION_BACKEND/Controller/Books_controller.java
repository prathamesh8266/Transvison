package com.example.TRANSVISION_BACKEND.Controller;

import com.example.TRANSVISION_BACKEND.Entities.Books;
import com.example.TRANSVISION_BACKEND.Entities.ListResponse;
import com.example.TRANSVISION_BACKEND.Entities.Response;
import com.example.TRANSVISION_BACKEND.Service.Books_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:5173")
public class Books_controller {
    @Autowired
    Books_service Books_Service;

    @GetMapping("/get/{bookId}")
    public ResponseEntity<Response> get_book_by_id(@PathVariable Integer bookId){
        return Books_Service.get_books_by_id(bookId);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ListResponse> get_all_books(){
        return Books_Service.get_all_books();
    }

    @PostMapping("/update/{bookId}")
    public ResponseEntity<Response> update_book(@PathVariable Integer bookId,@RequestBody Books book){
        System.out.println(book);
        return Books_Service.update_book(bookId,book);
    }
}
