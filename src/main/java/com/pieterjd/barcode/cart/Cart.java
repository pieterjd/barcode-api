package com.pieterjd.barcode.cart;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



import com.pieterjd.barcode.barcode.Barcode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

@Table(name = "CART")
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "CART_BARCODE",
        joinColumns = @JoinColumn(name="CART_ID"),
        inverseJoinColumns = @JoinColumn(name="BARCODE_ID")
    )
    private Set<Barcode> barcodes = new HashSet<>();
    private LocalDateTime createdAt = LocalDateTime.now();


    public void addBarcode(Barcode b){
        barcodes.add(b);
        b.getCarts().add(this);
    }

    public void removeBarcode(Barcode b){
        barcodes.remove(b);
        b.getCarts().remove(this);
    }
}
