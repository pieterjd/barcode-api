package com.pieterjd.barcode.barcode;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BarcodeDtoConverter implements Converter<BarcodeDto, Barcode> {
    private final DescriptionDtoConverter descriptionDtoConverter;

    public BarcodeDtoConverter(DescriptionDtoConverter descriptionDtoConverter) {
        this.descriptionDtoConverter = descriptionDtoConverter;
    }

    @Override
    public Barcode convert(BarcodeDto dto) {
        Set<Description> descriptions = dto.getDescriptions().stream()
                .map(description -> descriptionDtoConverter.convert(description))
                .collect(Collectors.toSet());
                
        return Barcode.builder()
                .id(dto.getId())
                .barcode(dto.getBarcode())
                .descriptions(descriptions)
                .build();
    }

}
