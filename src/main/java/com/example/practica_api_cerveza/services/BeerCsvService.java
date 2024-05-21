package com.example.practica_api_cerveza.services;

import com.example.practica_api_cerveza.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);

}
