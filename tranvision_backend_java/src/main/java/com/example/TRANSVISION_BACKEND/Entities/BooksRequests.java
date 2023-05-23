package com.example.TRANSVISION_BACKEND.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "books_request_table")
public class BooksRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer bookId;
    private String code;
    private Integer authorId;
    private Integer userId;
}
