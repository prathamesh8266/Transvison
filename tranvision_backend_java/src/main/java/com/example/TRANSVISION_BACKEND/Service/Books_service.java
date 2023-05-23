package com.example.TRANSVISION_BACKEND.Service;

import com.example.TRANSVISION_BACKEND.Entities.Books;
import com.example.TRANSVISION_BACKEND.Entities.ListResponse;
import com.example.TRANSVISION_BACKEND.Entities.Response;
import com.example.TRANSVISION_BACKEND.Entities.Response_is_valid;
import com.example.TRANSVISION_BACKEND.Repository.Books_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Books_service {

    @Autowired
    Books_repository Books_Repository;

    public ResponseEntity<ListResponse> get_all_books(){
        try{
            System.out.println(Books_Repository.findAll());
            return new ResponseEntity<>(new ListResponse(true, Response_is_valid.VALID,Books_Repository.findAll()), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ListResponse(false,Response_is_valid.INVALID,null),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> save_book(Books book){
        try{
            return new ResponseEntity<>(new Response(true,Response_is_valid.VALID,Books_Repository.save(book)),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,null),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> get_books_by_id(Integer bookId){
        try{
            Optional<Books> book_optn = Books_Repository.findById(bookId);
            if(book_optn.isPresent()){
                Books book = book_optn.get();
                return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,book), HttpStatus.OK);
            }else {
                throw new Exception("No book found with this id");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<ListResponse> get_books_by_admin_id(Integer adminId){
        try{
            List<Books> books = Books_Repository.findByAdminId(adminId);
            return new ResponseEntity<>(new ListResponse(true, Response_is_valid.VALID,books), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ListResponse(false,Response_is_valid.INVALID,new ArrayList<>(Collections.singleton(e.getMessage()))),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> delete_books(List<Integer> ids){
        try{
            for(Integer id : ids){
                Books_Repository.deleteById(id);
            }
            return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,"Books deleted successfully"), HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

    public ResponseEntity<Response> update_book(Integer book_id,Books book_reference){
        try{
            Optional<Books> book_optn = Books_Repository.findById(book_id);
            if(book_optn.isPresent()){
                Books book = book_optn.get();
                book.setCode(UUID.randomUUID().toString());
                book.setName(book_reference.getName());
                book.setPages(book_reference.getPages());
                book.setEdition(book_reference.getEdition());
                System.out.println(book.toString());
                Books_Repository.save(book);
                return new ResponseEntity<>(new Response(true, Response_is_valid.VALID,"Books updated successfully"), HttpStatus.OK);
            }else{
                throw new Exception("Book with this id not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new Response(false,Response_is_valid.INVALID,e.getMessage()),HttpStatus.OK);
        }
    }

}
