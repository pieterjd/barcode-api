package com.pieterjd.barcode.barcode;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {
    private BarcodeRepository barcodeRepository;

    public BarcodeService(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    public Barcode addDescription(String barcode, Description description){
        Barcode b = barcodeRepository.findByBarcode(barcode)
        .orElse(Barcode.builder().barcode(barcode).build());
        b.addDescription(description);
        
        return barcodeRepository.save(b);
    }

    public Page<Barcode> findAll(Pageable pageable) {
        return barcodeRepository.findAll(pageable);
    }

    public Iterable<Barcode> findAll() {
        return barcodeRepository.findAll();
    }

    public List<Barcode> findByBarcodeStartingWith(String prefix) {
        return barcodeRepository.findByBarcodeStartingWith(prefix);
    }

    

    
    
}
