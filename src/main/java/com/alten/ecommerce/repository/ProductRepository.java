package com.alten.ecommerce.repository;

import com.alten.ecommerce.model.InventoryStatus;
import com.alten.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    Page<Product> findAllByNameContainingAndCategoryContainingAndInventoryStatusIn(
            String name,
            String category,
            List<InventoryStatus> inventoryStatuses,
            Pageable pageable
    );

    Page<Product> findAllByNameContainingAndCategoryContaining(
            String name,
            String category,
            Pageable pageable
    );
}
