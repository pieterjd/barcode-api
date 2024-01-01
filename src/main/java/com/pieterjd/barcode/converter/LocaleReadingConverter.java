package com.pieterjd.barcode.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@ReadingConverter
@Component
public class LocaleReadingConverter implements Converter<String, Locale> {

    @Override
    public Locale convert(String tag) {
        return Locale.forLanguageTag(tag);
    }
    
}