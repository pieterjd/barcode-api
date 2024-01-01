package com.pieterjd.barcode.barcode;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BarcodeRepository extends CrudRepository<Barcode, Long>,
        PagingAndSortingRepository<Barcode, Long> {
                List<Barcode> findByBarcode(String barcode);

}
