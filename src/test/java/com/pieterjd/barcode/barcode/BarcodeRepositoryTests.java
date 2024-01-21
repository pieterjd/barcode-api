package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BarcodeRepositoryTests {
    @Autowired
    private BarcodeRepository repository;

    @Test
    void findByBarcode() {
        List<Barcode> results = repository.findByBarcode("123");
        assertTrue(results.isEmpty());
        Barcode b1 = repository.save(Barcode.builder()
                .barcode("123")
                .build());
        repository.save(Barcode.builder()
                .barcode("1234")
                .build());
        Barcode b3 = repository.save(Barcode.builder()
                .barcode("123")
                .build());

        results = repository.findByBarcode("123");
        assertEquals(2, results.size());
        Set<Long> expectedIds = List.of(b1, b3).stream()
                .map(b -> b.getId())
                .collect(Collectors.toSet());
        Set<Long> actualIds = results.stream()
                .map(b -> b.getId())
                .collect(Collectors.toSet());
        assertEquals(expectedIds, actualIds);
        results.forEach(b -> assertEquals("123", b.getBarcode()));

    }
}
