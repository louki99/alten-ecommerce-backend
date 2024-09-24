package com.alten.ecommerce.initializer;

import com.alten.ecommerce.model.InventoryStatus;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final Faker faker;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            generateAndSaveProducts();
        }
    }

    private void generateAndSaveProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Product product = new Product();
            product.setCode(faker.code().ean13());
            product.setName(truncateString(faker.commerce().productName(), 255));
            product.setDescription(truncateString(faker.lorem().paragraph(), 255));
            product.setImage("https://chawkbazar.redq.io/_next/image?url=https%3A%2F%2Fchawkbazarlaravel.s3.ap-southeast-1.amazonaws.com%2F292%2F10.png&w=1920&q=75");
            product.setCategory(truncateString(faker.commerce().department(), 255));
            product.setPrice(Double.valueOf(faker.commerce().price(10.0, 100.0)));
            product.setQuantity(faker.random().nextInt(1, 100));
            product.setInternalReference(truncateString(faker.company().buzzword(), 255));
            product.setShellId(faker.random().nextInt(1, 1000));
            product.setInventoryStatus(getRandomInventoryStatus());
            product.setRating(faker.random().nextInt(1, 5));
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    private InventoryStatus getRandomInventoryStatus() {
        InventoryStatus[] statuses = InventoryStatus.values();
        return statuses[faker.random().nextInt(0, statuses.length - 1)];
    }

    private String truncateString(String input, int maxLength) {
        return input.length() > maxLength ? input.substring(0, maxLength) : input;
    }

}