package dev.nikhil.projectservice.controllers;

import dev.nikhil.projectservice.dtos.ProductDto;
import dev.nikhil.projectservice.exceptions.NotFoundException;
import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.models.Product;
import dev.nikhil.projectservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return response;
    }


    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {

        Product newProduct = productService.addNewProduct(
                product
        );

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.deleteProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                HttpStatus.OK
        );

        return response;
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> naman(Exception exception) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
}
