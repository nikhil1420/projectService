package dev.nikhil.projectservice.services;

import dev.nikhil.projectservice.clients.fakestoreapi.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FakeStoreCategoryServiceImpl {
    @Autowired
    private FakeStoreClient fakeStoreClient;

    public List<String> getAllCategories() {
        List<String> categories = fakeStoreClient.getAllCategories();
        return categories;
    }

    public String getProductsInCategory(Long categoryId) {
        return null;
    }
}
