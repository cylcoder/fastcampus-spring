package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequest {

    private String name;
    @JsonProperty("ISBN")
    private String ISBN;
    private String category;

}
