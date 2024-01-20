package com.pieterjd.barcode.barcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Table("BARCODE_CART")
public class BarcodeRef {
    //@Column("BARCODE")
    private long barcodeId;
}
