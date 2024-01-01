package com.pieterjd.barcode.barcode;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DescriptionDtoConverter implements Converter<DescriptionDto, Description>{

    @Override
    public Description convert(DescriptionDto dto) {
        return Description.builder()
        .id(dto.getId())
        .locale(Locale.forLanguageTag(dto.getLocale()))
        .text(dto.getText())
        .build();
    }
    
}
