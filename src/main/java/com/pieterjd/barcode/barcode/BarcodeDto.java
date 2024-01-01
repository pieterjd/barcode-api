package com.pieterjd.barcode.barcode;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BarcodeDto {
    
    private Long id;    
    private String barcode;
    @Builder.Default
    private Set<DescriptionDto> descriptions = new HashSet<>();
    
}
