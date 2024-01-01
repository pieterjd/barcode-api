package com.pieterjd.barcode;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.pieterjd.barcode.converter.LocaleReadingConverter;
import com.pieterjd.barcode.converter.LocaleWritingConverter;

@Configuration
@EnableJdbcRepositories
public class DatabaseConfig extends AbstractJdbcConfiguration {
    private LocaleReadingConverter localeReadingConverter;
    private LocaleWritingConverter localeWritingConverter;

    
    public DatabaseConfig(LocaleReadingConverter localeReadingConverter,
            com.pieterjd.barcode.converter.LocaleWritingConverter localeWritingConverter) {
        this.localeReadingConverter = localeReadingConverter;
        this.localeWritingConverter = localeWritingConverter;
    }



    @Override
    public JdbcCustomConversions jdbcCustomConversions() {

        return new JdbcCustomConversions(List.of(localeReadingConverter, localeWritingConverter));
    }    

}
