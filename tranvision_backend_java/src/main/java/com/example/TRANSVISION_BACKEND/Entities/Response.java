package com.example.TRANSVISION_BACKEND.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Response<T> {
    private Boolean is_valid;
    private Response_is_valid message;
    private T response;
}
