package com.valdir.readcsv.resources;

import com.valdir.readcsv.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService service;

    @PostMapping(value = "/fromCSV")
    public ResponseEntity<?> createFromCSV(@RequestParam("file") MultipartFile file) {
        service.createFromCSV(file);
        return ResponseEntity.ok().build();
    }
}
