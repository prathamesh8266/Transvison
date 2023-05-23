package com.example.TRANSVISION_BACKEND.Service;

import com.example.TRANSVISION_BACKEND.Entities.Admin;
import com.example.TRANSVISION_BACKEND.Entities.General;
import com.example.TRANSVISION_BACKEND.Entities.Response;
import com.example.TRANSVISION_BACKEND.Entities.Response_is_valid;
import com.example.TRANSVISION_BACKEND.Repository.General_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class General_service {

    @Autowired
    General_repository general_repository;

    public ResponseEntity<Response> get_general_user_by_id(Integer id){
        try{
            return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,general_repository.findById(id).get()),HttpStatus.OK);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> add_general_user(General general_user){
        try{
            return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,general_repository.save(general_user)),HttpStatus.OK);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> get_general_user_by_name_password(General general_user) {
        try{
            List<General> general_user_from_db = general_repository.findByNameAndPassword(general_user.getName(), general_user.getPassword());
            if(general_user_from_db.size() == 0){
                throw new Exception("Invalid credentials");
            }
            return new ResponseEntity<>(new Response(true,Response_is_valid.VALID, general_user_from_db.get(0).getId()),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> create_general_user(General generalUser) {
        try{
            general_repository.save(generalUser);
            return new ResponseEntity<>(new Response(true,Response_is_valid.VALID,true),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }
}
