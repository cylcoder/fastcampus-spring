package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetApiController {

    // http://localhost:8080/api/book1?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping("/book1")
    public String queryParam(
            String category,
            Long issuedYear,
            @RequestParam(name = "issued-month") Long issuedMonth,
            @RequestParam(name = "issued_day") Long issuedDay
    ) {
        System.out.println("category = " + category);
        System.out.println("issuedYear = " + issuedYear);
        System.out.println("issuedMonth = " + issuedMonth);
        System.out.println("issuedDay = " + issuedDay);
        return null;
    }

    // http://localhost:8080/api/book2?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    // DTO를 통해 입력받는 경우 변수명이 DTO의 멤버변수명과 동일해야 함
    @GetMapping("/book2")
    public BookQueryParam queryParam2(BookQueryParam bookQueryParam) {
        System.out.println("bookQueryParam = " + bookQueryParam);
        return bookQueryParam;
    }

}
