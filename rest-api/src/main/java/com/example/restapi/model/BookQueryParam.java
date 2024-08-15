package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookQueryParam {

    String category;
    Long issuedYear;
    Long issuedMonth;
    Long issuedDay;

}
