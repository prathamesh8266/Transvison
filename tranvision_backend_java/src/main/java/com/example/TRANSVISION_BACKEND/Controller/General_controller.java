package com.example.TRANSVISION_BACKEND.Controller;

import com.example.TRANSVISION_BACKEND.Entities.General;
import com.example.TRANSVISION_BACKEND.Entities.Response;
import com.example.TRANSVISION_BACKEND.Service.General_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/general")
@CrossOrigin(origins = "http://localhost:5173")
public class General_controller {

    @Autowired
    General_service general_service;

    @PostMapping("/getGeneralUser")
    public ResponseEntity<Response> get_admin_by_name_password(@RequestBody General general_user){
        System.out.println(general_user);
        return general_service.get_general_user_by_name_password(general_user);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> get_general_user_by_id(@PathVariable Integer userId){
        return general_service.get_general_user_by_id(userId);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create_admin(@RequestBody General general_user){
        return general_service.create_general_user(general_user);
    }

}
