package com.valdir.readcsv.services;

import com.valdir.readcsv.model.User;
import com.valdir.readcsv.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final FileService fileService;


    public void createFromCSV(MultipartFile file) {
        Set<Map<String, String>> list = fileService.getListFromCSV(file);
        var listUser = new ArrayList<User>();
        list.forEach(obj -> {
            listUser.add(new User(null, obj.get("name"), obj.get("description")));
        });
        repository.saveAll(listUser);
    }

}
