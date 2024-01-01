package com.pieterjd.barcode.barcode;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BarcodeDto {
    
    private Long id;  
    @EqualsAndHashCode.Include  
    private String barcode;
    @Builder.Default
    private Set<DescriptionDto> descriptions = new HashSet<>();
    
}
