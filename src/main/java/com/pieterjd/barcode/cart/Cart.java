package com.pieterjd.barcode.cart;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.pieterjd.barcode.barcode.Barcode;
import com.pieterjd.barcode.barcode.BarcodeRef;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

//@Table("CART")
public class Cart {
    @Id
    private Long id;
    
    private Set<BarcodeRef> barcodes = new HashSet<>();
    private LocalDateTime createdAt = LocalDateTime.now();


    public void addBarcode(Barcode b){
        BarcodeRef ref = BarcodeRef.builder().barcodeId(b.getId()).build();
        barcodes.add(ref);
    }
}
