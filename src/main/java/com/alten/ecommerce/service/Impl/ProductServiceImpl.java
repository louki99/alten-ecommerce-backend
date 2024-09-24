package com.alten.ecommerce.service.Impl;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.exception.ResourceNotFoundException;
import com.alten.ecommerce.mapper.ProductMapper;
import com.alten.ecommerce.model.InventoryStatus;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Page<ProductDTO> listProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Product> productPage = productRepository.findAll(pageRequest);
        return productPage.map(productMapper::mapToProductDTO);
    }

    @Override
    public ProductDTO findProductById(long id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Product not found with ID: {}", id);
                    return new ResourceNotFoundException("Product not found");
                });

        return productMapper.mapToProductDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO createProduct) {
        log.info("Creating product with details: {}", createProduct);
        Product product = productMapper.mapToProductEntity(createProduct);
        product.setCreatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        log.info("Product created with ID: {}", savedProduct.getId());
        return productMapper.mapToProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO updatedProductDTO) {
        log.info("Updating product with ID: {}", id);
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProductDTO.product_name());
            product.setPrice(updatedProductDTO.product_price());
            product.setUpdatedAt(LocalDateTime.now());
            Product updatedProduct = productRepository.save(product);
            log.info("Product updated with ID: {}", updatedProduct.getId());
            return productMapper.mapToProductDTO(updatedProduct);
        }).orElseThrow(() -> {
            log.warn("updateProduct({id}) Product not found with ID: {}", id);
            return new ResourceNotFoundException("Product not found");
        });
    }

    @Override
    public Page<Product> filteredProducts(String name,String category,int page,int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return productRepository.findAllByNameContainingAndCategoryContaining(
                name,
                category,
                pageable
        );
    }


    @Override
    public void deleteProduct(long id) {
        log.info("Attempting to delete product with ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            log.info("Deleted product with ID: {}", id);
        } else {
            log.warn("(deleteProduct({id}) Product not found with ID: {}", id);
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
    }
}
