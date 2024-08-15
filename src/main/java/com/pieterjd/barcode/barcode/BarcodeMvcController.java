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

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/barcodes")
public class BarcodeMvcController {
    private BarcodeService barcodeService;

    public BarcodeMvcController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }


    @GetMapping
    public String barcodesHome(Model model){
        model.addAttribute("barcodes", barcodeService.findAll(Pageable.unpaged()).getContent());
        return "barcodes";
    }

    @GetMapping("/add")
    public String barcodesSubmit(Model model){
        model.addAttribute("submission", new AddBarcodeDescriptionSubmission()) ;
        return "barcodesAdd";
    }


    @PostMapping("/add")
    public HtmxResponse postMethodName(@Valid @ModelAttribute("submission") AddBarcodeDescriptionSubmission submission,BindingResult result,Model model) {
        // implemented as described at https://htmx.org/examples/update-other-content/#expand
        if (result.hasErrors()) {
            model.addAttribute("barcodes", barcodeService.findAll());
            model.addAttribute("submission", submission);
            return HtmxResponse.builder()
                    .view("barcodes :: barcodesTable")
                    .view("fragments/addBarcodeDescriptionForm :: editBarcodeForm")
                    .trigger("barcodeSubmissionNotValidated")
                    .build();

        }
        Description description = Description.builder()
                .locale(Locale.forLanguageTag(submission.getLocale()))
                .text(submission.getDescription())
                .build();
        

        Barcode barcode = barcodeService.addDescription(submission.getBarcode(), description);

        
        model.addAttribute("barcodes", barcodeService.findAll());
        model.addAttribute("barcode", barcode);

        return HtmxResponse.builder()
                .view("barcodes :: barcodesTable")
                .view("fragments/addBarcodeDescriptionForm :: addBarcodeForm")
                .trigger("barcodeSubmissionValidated")
                .build();
    }
    
    
}
