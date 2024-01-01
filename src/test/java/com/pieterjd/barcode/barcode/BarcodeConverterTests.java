package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BarcodeConverterTests {

    private DescriptionConverter descriptionConverter = new DescriptionConverter();
    private BarcodeConverter barcodeConverter = new BarcodeConverter(descriptionConverter);
    @Test
    void testConvertBarcodeWithout() {
        Barcode b = Barcode.builder()
        .barcode("123")
        .build();
        BarcodeDto expected = BarcodeDto.builder()
        .barcode("123")
        .build();
        BarcodeDto actual = barcodeConverter.convert(b);
        assertTrue(actual.equals(expected));
    }
}
