package dev.nikhil.projectservice.controllers;

import dev.nikhil.projectservice.clients.authenticationclient.AuthenticationClient;
import dev.nikhil.projectservice.clients.authenticationclient.dto.Role;
import dev.nikhil.projectservice.clients.authenticationclient.dto.SessionStatus;
import dev.nikhil.projectservice.clients.authenticationclient.dto.ValidateTokenResponseDto;
import dev.nikhil.projectservice.dto.CreateProductRequestDTO;
import dev.nikhil.projectservice.dto.ProductResponseDTO;
import dev.nikhil.projectservice.dto.ValidateTokenRequestDto;
import dev.nikhil.projectservice.dto.fakeStoreDTOs.FakeStoreProductResponseDTO;
import dev.nikhil.projectservice.exceptions.InvalidInputException;
import dev.nikhil.projectservice.exceptions.RandomException;
import dev.nikhil.projectservice.models.Product;
import dev.nikhil.projectservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService; // field injection
    @Autowired
    private AuthenticationClient authenticationClient;

    //Make only admins able to access all products
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                                   @Nullable @RequestHeader("USER_ID") UUID userId){
        // check if token exists
        if (token == null || userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ValidateTokenResponseDto response = authenticationClient.validate(token, userId);

        // check if token is valid
        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // check if user has permissions
        boolean isUserAdmin = false;
        for (Role role: response.getUser().getRoles()) {
            if (role.getRole().equals("ADMIN")) {
                isUserAdmin = true;
            }
        }

        if (!isUserAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<ProductResponseDTO> products = productService.getAllProducts();

//        products.get(0).setPrice(100); /// Bug induced in my code
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") UUID id){
        if(id == null){
            throw new InvalidInputException("Input is not correct");
        }
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.createProduct(productRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(productService.updateProduct(productRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.ok(
                productService.deleteProduct(id)
        );
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity<ProductResponseDTO> getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(
                productService.getProduct(productName)
        );
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity getProductByPriceRange(@PathVariable("min") double minPrice, @PathVariable("max") double maxPrice){
        return ResponseEntity.ok(
                productService.getProducts(minPrice, maxPrice)
        );
    }


    //used for demo of controller advice
    /*
    @GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Exception from product");
    }
     */
}
