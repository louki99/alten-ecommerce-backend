package com.alten.ecommerce.dto;

import com.alten.ecommerce.model.InventoryStatus;

import java.time.LocalDateTime;

public record ProductDTO(
        Long id,
        String product_code,
        String product_name,
        String product_description,
        String product_image,
        String product_category,
        Double product_price,
        Integer product_quantity,
        String internal_reference,
        Integer shell_id,
        InventoryStatus inventory_status,
        Integer product_rating,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {}
