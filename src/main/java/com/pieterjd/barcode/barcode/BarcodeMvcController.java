package com.pieterjd.barcode.barcode;

import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



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

    @GetMapping("/add")
    public String barcodesSubmit(Model model){
        model.addAttribute("submission", new AddBarcodeSubmission()) ;
        return "barcodesAdd";
    }

    @PostMapping("/add")
    public String postMethodName(@Valid @ModelAttribute("submission") AddBarcodeSubmission submission,BindingResult result,Model model) {
        if(result.hasErrors()){
            return "barcodesAdd";
        }
        Description description = Description.builder()
            .locale(Locale.forLanguageTag(submission.getLocale()))
            .text(submission.getDescription())
            .build();
        Barcode barcode = Barcode.builder()
        .barcode(submission.getBarcode())
        .build();
        barcode.addDescription(description);

        barcodeRepository.save(barcode);
        return "redirect:/barcodes/add";
    }
    
    
}
