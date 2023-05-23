package com.example.TRANSVISION_BACKEND.Controller;

import com.example.TRANSVISION_BACKEND.Entities.BooksRequests;
import com.example.TRANSVISION_BACKEND.Entities.Response;
import com.example.TRANSVISION_BACKEND.Entities.Response_is_valid;
import com.example.TRANSVISION_BACKEND.Repository.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class Book_request_controller {

    @Autowired
    Requests book_request_db;

    @PostMapping("/add")
    public ResponseEntity<Response> add_request(@RequestBody BooksRequests request){
        System.out.println(request.toString());
        try{
            return new ResponseEntity<>((new Response(true, Response_is_valid.VALID,book_request_db.save(request))), HttpStatus.OK);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }
}
