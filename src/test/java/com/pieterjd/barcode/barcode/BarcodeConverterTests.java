package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BarcodeConverterTests {

    private DescriptionConverter descriptionConverter = new DescriptionConverter();
    private BarcodeConverter barcodeConverter = new BarcodeConverter(descriptionConverter);
    @Test
    void testConvertBarcodeWithout() {
        Barcode b = Barcode.builder()
        .barcode("123")
        .descriptions(new HashSet<>())
        .build();
        BarcodeDto expected = BarcodeDto.builder()
        .barcode("123")
        .descriptions(new HashSet<>())
        .build();
        BarcodeDto actual = barcodeConverter.convert(b);
        assertTrue(actual.equals(expected));
    }
}
