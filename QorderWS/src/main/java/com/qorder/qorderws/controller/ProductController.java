package com.qorder.qorderws.controller;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.service.IProductService;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/category/{categoryID}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> storeProducts(@PathVariable Long categoryID, @RequestBody DetailedProductDTO productDTO) throws ResourceNotFoundException, IOException {
		LOGGER.info("Request to store products save with business id equals :" + categoryID);
		
		long productID = productService.storeProduct(categoryID, productDTO);
		
		URI location = URI.create(ReferenceProvider.INSTANCE.getLocationFor(EEntity.PRODUCT) + productID);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(headers,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{productID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedProductDTO> getProductById(@PathVariable Long productID) throws ResourceNotFoundException {
		LOGGER.info("Request for product with id parameter equal " + productID.toString(), productID);
		
		return new ResponseEntity<>(productService.fetchProductById(productID), HttpStatus.OK); 
	}
	
	
	@ExceptionHandler({ IOException.class })
	ResponseEntity<String> sendIOException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
