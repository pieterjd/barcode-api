package com.pieterjd.barcode.barcode;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DescriptionConverter implements Converter<Description, DescriptionDto> {

    @Override
    public DescriptionDto convert(Description d) {
        return DescriptionDto.builder()
                .id(d.getId())
                .text(d.getText())
                .locale(d.getLocale().toLanguageTag())
                .build();
    }

}
