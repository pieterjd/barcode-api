package com.pieterjd.barcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pieterjd.barcode.barcode.BarcodeRepository;
import com.pieterjd.barcode.cart.CartRepository;

@SpringBootApplication
public class BarcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarcodeApplication.class, args);
	}

	//@Bean
	CommandLineRunner repo(BarcodeRepository br, CartRepository cr, JsonDataLoader dataLoader){
		return args ->{
			dataLoader.readData();
		};
	}

}
