package dev.nikhil.projectservice.service;

import dev.nikhil.projectservice.exceptions.CategoryNotFoundException;
import dev.nikhil.projectservice.models.Category;
import dev.nikhil.projectservice.models.Product;
import dev.nikhil.projectservice.repositories.CategoryRepository;
import dev.nikhil.projectservice.services.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest {


    @Mock // @Mock for all dependencies
    private CategoryRepository categoryRepository;

//    @Mock
//    private FakeStoreClient fakeStoreClient;

    @InjectMocks // @InjectMocks for the actual class we are testing
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); // not required nowadays. It initialises and adds all the required mocks
    }

    // this method will return the total cost for all products under a category
    @Test
    public void testGetTotalPriceForMultipleProductsUnderCategory(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 300.00;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(actualTotalCost, expectedTotalCost);
    }

    // test methods are always public void
    @Test
    public void testGetTotalPriceForZeroProductsUnderCategory(){
        //ARRANGE
        UUID categoryId = UUID.randomUUID();
       // ArrayList<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS = new ArrayList<>();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        //Mockito.when(fakeStoreClient.getAllProducts()).thenReturn(fakeStoreProductResponseDTOS);
        double expectedTotalCost = 0.0;

        //ACT
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //ASSERT -> all the checks
        Assertions.assertEquals(actualTotalCost, expectedTotalCost);
        Mockito.verify(categoryRepository).findById(categoryId);
    }

    @Test
    public void testCategoryNotFoundExceptionThrown(){
        //arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //act and assert
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getTotalPriceForCategory(categoryId));
    }

    private Optional<Category> getCategoryMockDataWithZeroProducts(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        List<Product> products = new ArrayList<>();

        category.setProducts(products);
        return Optional.of(category);
    }

    private Optional<Category> getCategoryMockData(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("Product1");
        product1.setPrice(100.00);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setTitle("Product2");
        product2.setPrice(200.00);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        category.setProducts(products);
        return Optional.of(category);
    }

}
