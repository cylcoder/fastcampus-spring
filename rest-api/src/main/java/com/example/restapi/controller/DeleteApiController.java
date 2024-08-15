package com.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class DeleteApiController {

    @DeleteMapping(path = {"/delete/{userName}", "/user/{userName}"})
    public void delete(@PathVariable String userName) {
        log.info("userName={}", userName);
    }

}
