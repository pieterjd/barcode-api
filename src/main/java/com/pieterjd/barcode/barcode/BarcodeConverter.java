package com.pieterjd.barcode.barcode;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BarcodeConverter implements Converter<Barcode, BarcodeDto> {
    private final DescriptionConverter descriptionConverter;

    public BarcodeConverter(DescriptionConverter descriptionConverter) {
        this.descriptionConverter = descriptionConverter;
    }

    @Override
    public BarcodeDto convert(Barcode b) {
        Set<DescriptionDto> descriptions = b.getDescriptions().stream()
                .map(desc -> descriptionConverter.convert(desc))
                .collect(Collectors.toSet());
                
        return BarcodeDto.builder()
                .id(b.getId())
                .barcode(b.getBarcode())
                .descriptions(descriptions)
                .build();
    }

}
