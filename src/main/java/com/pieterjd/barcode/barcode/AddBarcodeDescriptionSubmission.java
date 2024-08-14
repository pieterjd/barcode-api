package com.pieterjd.barcode.barcode;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBarcodeDescriptionSubmission {
    @NotEmpty
    @Size(min = 10)
    private String barcode="123456789";
    @NotEmpty
    @Size(min = 10)
    private String description="submission";
    @NotEmpty
    private String locale="fr-BE";    
}
