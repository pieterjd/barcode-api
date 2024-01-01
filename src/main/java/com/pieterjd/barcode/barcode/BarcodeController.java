package com.pieterjd.barcode.barcode;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/barcode")
@CrossOrigin
public class BarcodeController {
    private final BarcodeRepository repository;

    private final BarcodeDtoConverter dtoConverter;
    private final BarcodeConverter converter;

    public BarcodeController(BarcodeRepository repository, BarcodeDtoConverter dtoConverter,
            BarcodeConverter converter) {
        this.repository = repository;
        this.dtoConverter = dtoConverter;
        this.converter = converter;
    }

    @GetMapping
    public Page<BarcodeDto> get(Pageable p) {
        return repository.findAll(p)
                .map(barcode -> converter.convert(barcode));
    }

    @GetMapping("/{barcode}")
    public List<BarcodeDto> getOne(@PathVariable String barcode) {
        return repository.findByBarcode(barcode).stream()
        .map(b->converter.convert(b))
        .collect(Collectors.toList());
    }

    @PostMapping
    public BarcodeDto post(@RequestBody BarcodeDto dto) {
        Barcode barcode = dtoConverter.convert(dto);
        barcode = repository.save(barcode);
        return converter.convert(barcode);
    }

    @GetMapping("/export")
    public Iterable<Barcode> export(){
        return repository.findAll();
    }

}
