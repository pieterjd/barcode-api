package com.pieterjd.barcode.barcode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class BarcodeConverterTests {

    private DescriptionConverter descriptionConverter = new DescriptionConverter();
    private BarcodeConverter barcodeConverter = new BarcodeConverter(descriptionConverter);

    @Test
    void testConvertBarcodeWithoutDescriptions() {
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

    @Test
    void testConvertBarcodeWithDescriptions() {
        Barcode b = Barcode.builder()
                .barcode("123")
                .descriptions(
                        Set.of(
                                Description.builder().locale(Locale.ENGLISH).text("english").build()))
                .build();
        BarcodeDto expected = BarcodeDto.builder()
                .barcode("123")
                .descriptions(Set.of(
                                DescriptionDto.builder().locale("en").text("english text").build()))
                .build();
        BarcodeDto actual = barcodeConverter.convert(b);
        assertTrue(actual.equals(expected));
    }
}
