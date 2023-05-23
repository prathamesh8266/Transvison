package com.example.TRANSVISION_BACKEND.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books_table")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String code;
    private Integer edition;
    private Integer pages;
    @ManyToOne
    @JsonBackReference
    private Admin admin;

    @Override
    public String toString(){
        return "id="+id+" "+"name="+name+" "+"code="+code+" "+"edition="+edition+" "+"pages="+pages+" "+"admin="+admin;
    }
}
