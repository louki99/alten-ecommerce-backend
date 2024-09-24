package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.model.InventoryStatus;
import com.alten.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {

    Page<ProductDTO> listProducts(int page, int size);

    ProductDTO findProductById(long id);

    ProductDTO createProduct(ProductDTO createProduct);

    ProductDTO updateProduct(long id,ProductDTO updatedProduct);

    Page<Product> filteredProducts(String name, String category, int page, int size);

    void deleteProduct(long id);

}
