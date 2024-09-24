package com.alten.ecommerce.controller;

import com.alten.ecommerce.dto.ProductDTO;
import com.alten.ecommerce.model.InventoryStatus;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Validated
@Tag(name = "Product Operations", description = "Endpoints for managing products in the alten e-commerce application.")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    @Operation(summary = "Retrieve All Products",
            description = "Fetches a list of all products available in the system.")
    @ApiResponse(
            responseCode = "200",
            description = "OK. Successfully retrieved the list of products.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)
            )
    )
    public ResponseEntity<Page<ProductDTO>> getProductsWithPagination(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {
        Page<ProductDTO> productPage = productService.listProducts(page,size);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product by ID",
            description = "Retrieves detailed information about a specific product using its unique identifier.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Product found successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found. No product exists with the provided ID.",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    public ResponseEntity<ProductDTO> getProductById(@PathVariable @Min(1) long id) {
        ProductDTO product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }


    @Operation(
            summary = "Create New Product",
            description = "Adds a new product to the inventory. The product details must be provided in the request body."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created. The product has been successfully created.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request. Invalid input data provided."
            )
    })
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO createProduct) {
        ProductDTO savedProduct = productService.createProduct(createProduct);
        return ResponseEntity.status(201).body(savedProduct);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Product",
            description = "Updates the details of an existing product identified by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Product updated successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found. No product exists with the provided ID.",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable @Min(1) long id,
            @RequestBody @Valid ProductDTO updatedProductDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, updatedProductDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product",
            description = "Removes a product from the inventory using its unique identifier.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content. Product deleted successfully."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found. No product exists with the provided ID."
            )
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable @Min(1) long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter Products",
            description = "Retrieves a list of products that match the specified criteria, such as name, price range, and category.")
    @ApiResponse(
            responseCode = "200",
            description = "OK. Successfully retrieved the filtered list of products.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)
            )
    )
    public ResponseEntity<Page<Product>> getFilteredProducts(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size
    ) {
        Page<Product> products = productService.filteredProducts(
                name,
                category,
                page,
                size
        );
        return ResponseEntity.ok(products);
    }

}
