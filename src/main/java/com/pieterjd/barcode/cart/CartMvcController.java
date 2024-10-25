package com.pieterjd.barcode.cart;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.pieterjd.barcode.barcode.Barcode;
import com.pieterjd.barcode.barcode.BarcodeRepository;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/carts")
public class CartMvcController {
    private final CartRepository cartRepository;
    private final BarcodeRepository barcodeRepository;

    public CartMvcController(CartRepository cartRepository, BarcodeRepository barcodeRepository) {
        this.cartRepository = cartRepository;
        this.barcodeRepository = barcodeRepository;
    }

    @GetMapping
    public String get(Model model) {
        Cart cart = new Cart();
        cart.addBarcode(barcodeRepository.findByBarcode("5414972100791").get());
        cartRepository.save(cart);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/{id}/search")
    public HtmxResponse search(@PathVariable(name = "id") Long cartId,@RequestParam String search, Model model) {
        List<Barcode> byBarcodeStartingWith = StringUtils.hasText(search)
                ? barcodeRepository.findByBarcodeStartingWith(search)
                : List.of();
        Cart cart = cartRepository.findById(cartId).orElseThrow();


        model.addAttribute("barcodes", byBarcodeStartingWith);
        model.addAttribute("cart", cart);
        return HtmxResponse.builder()
                .view("fragments/barcodeSearch :: resultRows")
                .build();
    }

    @PostMapping("/{id}/add/{barcode}")
    public HtmxResponse addToCart(Model model, @PathVariable Long id, @PathVariable String barcode) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        Barcode toAdd = barcodeRepository.findByBarcode(barcode).orElseThrow();
        cart.addBarcode(toAdd);
        cartRepository.save(cart);
        
        model.addAttribute("cart", cart);
        
        return HtmxResponse.builder()
        .view("fragments/cart :: cart")
        .build();
    }
    
    

    
}
