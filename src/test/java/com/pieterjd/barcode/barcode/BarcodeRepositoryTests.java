package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BarcodeRepositoryTests {
    @Autowired
    private BarcodeRepository repository;

    @Test
    void findByBarcode() {
        Optional<Barcode> barcode = repository.findByBarcode("123");
        assertTrue(barcode.isEmpty());
        Barcode b1 = repository.save(Barcode.builder()
                .barcode("123")
                .build());
        repository.save(Barcode.builder()
                .barcode("1234")
                .build());
        

        barcode = repository.findByBarcode("123");
        assertTrue(barcode.isPresent());
    }
}
