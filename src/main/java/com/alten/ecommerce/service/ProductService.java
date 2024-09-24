package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> listProducts();

    ProductDTO findProductById(long id);

    ProductDTO createProduct(ProductDTO createProduct);

    ProductDTO updateProduct(long id,ProductDTO updatedProduct);

    List<ProductDTO> filterProducts(String name, Double minPrice, Double maxPrice, String category);

    void deleteProduct(long id);

}
