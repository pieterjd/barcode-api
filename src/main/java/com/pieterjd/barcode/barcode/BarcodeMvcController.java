package com.pieterjd.barcode.barcode;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/barcodes")
public class BarcodeMvcController {
    private BarcodeRepository barcodeRepository;

    public BarcodeMvcController(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }


    @GetMapping
    public String barcodesHome(Model model){
        model.addAttribute("barcodes", barcodeRepository.findAll(Pageable.unpaged()).getContent());
        return "barcodes";
    }
    
}
