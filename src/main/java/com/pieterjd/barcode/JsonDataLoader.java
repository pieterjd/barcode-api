package com.pieterjd.barcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pieterjd.barcode.barcode.Barcode;
import com.pieterjd.barcode.barcode.BarcodeRepository;

import jakarta.annotation.PostConstruct;

@Component
public class JsonDataLoader {
    private final ObjectMapper mapper;
    private final BarcodeRepository repository;
    @Value("classpath:data.json") 
    private Resource resource;

    

    public JsonDataLoader(ObjectMapper mapper, BarcodeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostConstruct
    public void readData() throws StreamReadException, DatabindException, IOException{
        Barcode[] temp = mapper.readValue(resource.getInputStream(), Barcode[].class);
        List<Barcode> barcodes = Arrays.asList(temp);
        barcodes.forEach(bc -> bc.getDescriptions().stream().forEach(desc->desc.setBarcode(bc)));
        repository.saveAll(barcodes);

    }
    
}
