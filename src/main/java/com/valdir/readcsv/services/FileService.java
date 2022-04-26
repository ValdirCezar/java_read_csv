package com.valdir.readcsv.services;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
public class FileService {

    public Set<Map<String, String>> getListFromCSV(MultipartFile file) {
        try {
            File newFile = convertMultiPartToFile(file);

            CSVReader csvReader = new CSVReader(new FileReader(newFile));

            Set<Map<String, String>> linhas = new HashSet<>();

            String[] headers = csvReader.readNext();
            String[] columns;

            while (nonNull((columns = csvReader.readNext()))) {
                Map<String, String> campos = new LinkedHashMap<>();
                for (int i = 0; i < columns.length; i++) {
                    campos.put(headers[i], columns[i]);
                }
                linhas.add(campos);
            }
            return linhas;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File(requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
