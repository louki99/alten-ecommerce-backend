package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO mapToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity(),
                product.getInternalReference(),
                product.getShellId(),
                product.getInventoryStatus(),
                product.getRating(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product mapToProductEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.id());
        product.setCode(dto.product_code());
        product.setName(dto.product_name());
        product.setDescription(dto.product_description());
        product.setImage(dto.product_image());
        product.setCategory(dto.product_category());
        product.setPrice(dto.product_price());
        product.setQuantity(dto.product_quantity());
        product.setInternalReference(dto.internal_reference());
        product.setShellId(dto.shell_id());
        product.setInventoryStatus(dto.inventory_status());
        product.setRating(dto.product_rating());
        product.setCreatedAt(dto.created_at());
        product.setUpdatedAt(dto.updated_at());
        return product;
    }
}
