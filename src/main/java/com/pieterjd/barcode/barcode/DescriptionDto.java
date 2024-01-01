package com.pieterjd.barcode.barcode;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DescriptionDto {
    
    private Long id;
    private String locale;
    private String text;
    
}
