package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BarcodeServiceTests {
    
    @Autowired
    private BarcodeRepository barcodeRepository;

    @Autowired
    private BarcodeService barcodeService;
    @Test
    void testAddDescriptionWithNewBarcode() {
        Barcode updatedBarcode = barcodeService.addDescription("1234567890", Description.builder().locale(Locale.ENGLISH).text("English description").build());
        assertEquals(1, updatedBarcode.getDescriptions().size());
        updatedBarcode = barcodeService.addDescription("1234567890", Description.builder().locale(Locale.FRENCH).text("French description").build());
        assertEquals(2, updatedBarcode.getDescriptions().size());
    }

    @Test
    void testAddDescriptionWithExistingBarcode() {
        Barcode existingBarcode = new Barcode(null, "1234567890", new HashSet<>());
        assertNull(existingBarcode.getId());
        existingBarcode = barcodeRepository.save(existingBarcode);
        assertNotNull(existingBarcode.getId());
        assertEquals(0, existingBarcode.getDescriptions().size());
        Barcode updatedBarcode = barcodeService.addDescription("1234567890", Description.builder().locale(Locale.FRENCH).text("French description").build());
        assertEquals(1, updatedBarcode.getDescriptions().size());
    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindAll2() {

    }
}
