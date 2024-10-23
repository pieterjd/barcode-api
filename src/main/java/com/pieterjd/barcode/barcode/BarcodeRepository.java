package com.pieterjd.barcode.barcode;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BarcodeRepository extends CrudRepository<Barcode, Long>,
        PagingAndSortingRepository<Barcode, Long> {
                @Query("""
                        select b 
                        from Barcode b
                        left join fetch b.descriptions
                        left join fetch b.carts
                        where b.barcode = :barcode
                        """)
                Optional<Barcode> findByBarcode(String barcode);

}
