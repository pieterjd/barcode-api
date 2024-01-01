package com.pieterjd.barcode.converter;

import java.util.Locale;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class LocaleWritingConverter implements Converter<Locale, String>{

    @Override
    public String convert(Locale locale) {
        return locale.toLanguageTag();
    }
    
}
