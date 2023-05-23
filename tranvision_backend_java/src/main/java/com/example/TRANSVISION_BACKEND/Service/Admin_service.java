package com.example.TRANSVISION_BACKEND.Service;

import com.example.TRANSVISION_BACKEND.Entities.*;
import com.example.TRANSVISION_BACKEND.Repository.Admin_repository;
import com.example.TRANSVISION_BACKEND.Repository.Books_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Admin_service {
    @Autowired
    Admin_repository Admin_db;
    @Autowired
    Books_service Books_Service;
    public ResponseEntity<ListResponse> get_all(){
        try{
            return new ResponseEntity<>(new ListResponse(true, Response_is_valid.VALID,Admin_db.findAll()), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ListResponse(false,Response_is_valid.INVALID,null),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> createAdmin(Admin admin){
        try{
            Admin_db.save(admin);
            return new ResponseEntity<>(new Response(true,Response_is_valid.VALID,true),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }
    public ResponseEntity<Response> add_books(int id, List<Books> books){
        try{
            Optional<Admin> admin_optn = Admin_db.findById(id+"");
            List<Books> new_books = new ArrayList<>();
            if(admin_optn.isPresent()){
                Admin admin = admin_optn.get();
                for(Books b : books){
                    if(b.getId() != null){
                        b.setAdmin(Admin_db.findById(id+"").get());
                        Books_Service.save_book(b);
                    }else {
                        b.setAdmin(admin);
                        b.setCode(UUID.randomUUID().toString());
                        new_books.add(b);
                    }
                }
                List<Books> current_books = admin.getBooks();
                current_books.addAll(new_books);
                admin.setBooks(current_books);
                Admin_db.save(admin);
                return new ResponseEntity<>(new Response(true,Response_is_valid.VALID,"Books saved successfully"),HttpStatus.OK);
            }else{
                throw new Exception("No admin available with such ID");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> delete_books(Integer adminId, List<Integer> bookIds) {
        try{
            Optional<Admin> admin_optn = Admin_db.findById(adminId+"");
            if(admin_optn.isPresent()){
                // delete all books from books table
                Books_Service.delete_books(bookIds);
                return new ResponseEntity<>(new Response(true,Response_is_valid.VALID,"Books deleted successfully"),HttpStatus.OK);
            }else{
                throw new Exception("No admin available with such ID");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> get_admin_by_name_password(Admin admin) {
        try{
            List<Admin> admins = Admin_db.findByNameAndPassword(admin.getName(), admin.getPassword());
            if(admins.size() == 0){
                throw new Exception("Invalid credentials");
            }
            return new ResponseEntity<>(new Response(true,Response_is_valid.VALID, admins.get(0).getId()),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<ListResponse> get_all_books_by_admin_id(Integer adminId) {
        try{
            ResponseEntity<ListResponse> books = Books_Service.get_books_by_admin_id(adminId);
            return books;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ListResponse(false,Response_is_valid.INVALID,new ArrayList<String>(Collections.singleton(e.getMessage()))),HttpStatus.OK);
        }
    }
}
