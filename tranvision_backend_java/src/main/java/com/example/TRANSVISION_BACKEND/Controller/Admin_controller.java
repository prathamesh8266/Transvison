package com.example.TRANSVISION_BACKEND.Controller;

import com.example.TRANSVISION_BACKEND.Entities.*;
import com.example.TRANSVISION_BACKEND.Service.Admin_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class Admin_controller {

    @Autowired
    Admin_service Admin_Service;

    @GetMapping("/getAll")
    public ResponseEntity<ListResponse> get_all_admin(){
        return Admin_Service.get_all();
    }

    @GetMapping("/getAll/{adminId}")
    public ResponseEntity<ListResponse> get_all_books_by_admin_id(@PathVariable Integer adminId){
        return Admin_Service.get_all_books_by_admin_id(adminId);
    }

    @PostMapping("/getAdmin")
    public ResponseEntity<Response> get_admin_by_name_password(@RequestBody Admin admin){
        System.out.println(admin);
        return Admin_Service.get_admin_by_name_password(admin);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create_admin(@RequestBody Admin admin){
        return Admin_Service.createAdmin(admin);
    }

    @PostMapping("/addBook/{admin_id}")
    public ResponseEntity<Response> add_books(@PathVariable Integer admin_id, @RequestBody List<Books> books){
        return Admin_Service.add_books(admin_id,books);
    }

    @PostMapping("/deleteBooks/{admin_id}")
    public ResponseEntity<Response> delete_books(@PathVariable Integer admin_id, @RequestBody List<Integer> book_ids){
        System.out.println(book_ids);
//        return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,""),HttpStatus.OK);
        return Admin_Service.delete_books(admin_id,book_ids);
    }

}
