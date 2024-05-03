package dev.nikhil.projectservice.clients.fakestoreapi;

import dev.nikhil.projectservice.dto.fakeStoreDTOs.FakeStoreProductResponseDTO;
import dev.nikhil.projectservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    public List<FakeStoreProductResponseDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductResponseDTO[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductResponseDTO[].class
        );

        return Arrays.asList(l.getBody());
    }

    public FakeStoreProductResponseDTO getProductById(int productId)  {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> response =  restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreProductResponseDTO.class, productId);
        FakeStoreProductResponseDTO productDto = response.getBody();
        return productDto;
    }

   public Product addNewProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Product> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                Product.class
        );
        return response.getBody();
    }

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    public Product updateProduct(Product updatedProduct, Long productId) {
        ResponseEntity<Product> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                updatedProduct,
                Product.class,
                productId
        );

        return fakeStoreProductDtoResponseEntity.getBody();
    }

    public boolean deleteProduct(long productId) {
       restTemplateBuilder.build().delete("https://fakestoreapi.com/products/{productId}", productId);
       return true;
    }

    public List<String> getAllCategories(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> categories = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);
        return Arrays.asList(categories.getBody());
    }

}
