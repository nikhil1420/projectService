package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.clients.fakestoreapi.FakeStoreClient;
import dev.nikhil.projectservice.clients.fakestoreapi.FakeStoreProductDto;
import dev.nikhil.projectservice.dtos.ProductDto;
import dev.nikhil.projectservice.exceptions.NotFoundException;
import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService {
   @Autowired
    private FakeStoreClient fakeStoreClient;

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();

        for (FakeStoreProductDto productDto: fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }

        return answer;
    }

    /*
    Return a Product object with all the details of the fetched product.
    The ID of the category will be null but the name of the category shall be
    correct.
     */
    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
        Optional<FakeStoreProductDto> productDto =fakeStoreClient.getSingleProduct(productId);

        if (productDto == null) {
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto product) {

        FakeStoreProductDto productDto = fakeStoreClient.addNewProduct(product);

        return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, convertProductToFakeStoreProductDto(product));
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, convertProductToFakeStoreProductDto(product));
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException {
        Optional<FakeStoreProductDto> fakeStoreProductDtoOptional =fakeStoreClient.getSingleProduct(productId);
        if (fakeStoreProductDtoOptional == null) {
            return Optional.empty();
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.updateProduct(productId, fakeStoreProductDtoOptional.get());

        return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
    }
}
